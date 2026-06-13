package com.abhi.gov_hos_app.service;

import com.abhi.gov_hos_app.client.ProblemClient;
import com.abhi.gov_hos_app.dto.ReceiptDto;
import com.abhi.gov_hos_app.entity.Receipt;
import com.abhi.gov_hos_app.exception.NotFoundException;
import com.abhi.gov_hos_app.repository.ReceiptRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ProblemClient problemClient;
    private final ModelMapper modelMapper;
    private final Logger logger;

    public ReceiptService(ReceiptRepository receiptRepository,
                          ProblemClient problemClient,
                          ModelMapper modelMapper,
                          Logger logger) {
        this.receiptRepository = receiptRepository;
        this.problemClient = problemClient;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    public ReceiptDto save(ReceiptDto dto) {
        
        try {
            problemClient.getProblem(dto.getProblemId());
        } catch (Exception e) {
            throw new NotFoundException("Problem not found");
        }

        Receipt receipt = modelMapper.map(dto, Receipt.class);
        receipt.setProblemId(dto.getProblemId());
        receipt.setStatus(1);

        receipt = receiptRepository.save(receipt);

        return modelMapper.map(receipt, ReceiptDto.class);
    }

    public List<ReceiptDto> findAllByProblemId(Long problemId) {

        List<Receipt> list =
                receiptRepository.findByProblemIdAndStatusOrderByReceiptIdAsc(problemId,1);

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