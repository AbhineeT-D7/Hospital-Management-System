package com.abhi.gov_hos_app.controller;

import com.abhi.gov_hos_app.dto.ReceiptDto;
import com.abhi.gov_hos_app.exception.NotFoundException;
import com.abhi.gov_hos_app.service.ProblemService;
import com.abhi.gov_hos_app.service.ReceiptService;
import com.abhi.gov_hos_app.util.ApiPaths;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ApiPaths.ReceiptCtrl.CTRL)
public class ReceiptController {

	@Autowired
	ProblemService problemService;
	
	@Autowired
	ReceiptService receiptService;

	@GetMapping("/find-all-by-problemid/{problemId}")
	public ResponseEntity<List<ReceiptDto>> getReceipt(@PathVariable(name = "problemId", required = true) Long problemId) throws Exception {
		return ResponseEntity.ok(receiptService.findAllByProblemId(problemId));
	}

	@PostMapping
	public ResponseEntity<ReceiptDto> saveReceipt(@Valid @RequestBody @NotNull ReceiptDto dto) throws NotFoundException {
		return ResponseEntity.ok(receiptService.save(dto));
	}

//	@PutMapping("/{receiptId}")
//	public ResponseEntity<Boolean> updateReceipt(@PathVariable(name = "receiptId", required = true) Long problemId,
//			@Valid @RequestBody ProblemDtoForPatientSingleDto dto) throws Exception {
//		return ResponseEntity.ok(problemService.update(problemId, dto));
//	}

	@DeleteMapping("/{receiptId}")
	public ResponseEntity<Boolean> deleteReceipt(@PathVariable Long receiptId) {
		return ResponseEntity.ok(receiptService.delete(receiptId));
	}

}
