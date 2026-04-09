package com.abhi.gov_hos_app.service;

import com.abhi.gov_hos_app.dto.ReceiptDto;
import com.abhi.gov_hos_app.entity.Problem;
import com.abhi.gov_hos_app.entity.Receipt;
import com.abhi.gov_hos_app.exception.NotFoundException;
import com.abhi.gov_hos_app.repository.ProblemRepository;
import com.abhi.gov_hos_app.repository.ReceiptRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ProblemRepository problemRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    public ReceiptService(ReceiptRepository receiptRepository,
                          ProblemRepository problemRepository,
                          ModelMapper modelMapper,
                          Logger logger) {
        this.receiptRepository = receiptRepository;
        this.problemRepository = problemRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    public ReceiptDto save(ReceiptDto dto) {

        Problem problem = problemRepository.findById(dto.getProblemId())
                .orElseThrow(() ->
                        new NotFoundException("Problem not found"));

        Receipt receipt = modelMapper.map(dto, Receipt.class);
        receipt.setProblem(problem);
        receipt.setStatus(1);

        receipt = receiptRepository.save(receipt);

        return modelMapper.map(receipt, ReceiptDto.class);
    }

    public List<ReceiptDto> findAllByProblemId(Long problemId) {

        List<Receipt> list =
                receiptRepository.findByProblemProblemIdAndStatusOrderByReceiptIdAsc(problemId,1);

        if (list.isEmpty())
            throw new NotFoundException("No receipt found");

        return Arrays.asList(
                modelMapper.map(list, ReceiptDto[].class)
        );
    }

    public boolean delete(Long receiptId) {

        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() ->
                        new NotFoundException("Receipt not found with id " + receiptId));

        receipt.setStatus(0); // soft delete
        receiptRepository.save(receipt);

        logger.info("Receipt deleted with id {}", receiptId);

        return true;
    }
}