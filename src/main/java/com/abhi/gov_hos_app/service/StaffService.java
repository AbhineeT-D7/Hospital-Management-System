package com.abhi.gov_hos_app.service;

import com.abhi.gov_hos_app.dto.StaffDto;
import com.abhi.gov_hos_app.entity.Staff;
import com.abhi.gov_hos_app.exception.NotFoundException;
import com.abhi.gov_hos_app.repository.StaffRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private final ModelMapper modelMapper;
	private final Logger logger;
	private final StaffRepository staffRepository;

	public StaffService(StaffRepository staffRepository, ModelMapper modelMapper, Logger logger) {
		this.staffRepository = staffRepository;
		this.modelMapper = modelMapper;
		this.logger = logger;
	}

	public StaffDto save(Staff staff) throws Exception {
		try {
			staff.setStatus(1);
			staff = staffRepository.save(staff);

			return modelMapper.map(staff, StaffDto.class);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<StaffDto> getAll() throws NotFoundException {
		List<Staff> staffs = staffRepository.findByStatusOrderByStaffIdAsc(1);
		if (!staffs.isEmpty()) {
			StaffDto[] staffDtos = modelMapper.map(staffs, StaffDto[].class);
			return Arrays.asList(staffDtos);
		} else {
			logger.error("--There is never Staff");
			throw new NotFoundException("There is never Staff");
		}
	}

	public List<StaffDto> getAllDeletedStaff() throws NotFoundException {
		List<Staff> staffs = staffRepository.findByStatusOrderByStaffIdAsc(0);
		if (!staffs.isEmpty()) {
			StaffDto[] staffDtos = modelMapper.map(staffs, StaffDto[].class);
			return Arrays.asList(staffDtos);
		} else {
			logger.error("--There is never deleted Staff");
			throw new NotFoundException("There is never deleted Staff");
		}
	}

	public Boolean delete(@Valid Long staffId) throws Exception {
		Optional<Staff> optional = staffRepository.findById(staffId);
		if (optional.isPresent()) {
			optional.get().setStatus(0);
			staffRepository.save(optional.get());
			return true;
		} else {
			logger.error("--Staff does not exist with this id {}", staffId);
			throw new Exception("Staff does not exist with this id " + staffId);
		}
	}

}
