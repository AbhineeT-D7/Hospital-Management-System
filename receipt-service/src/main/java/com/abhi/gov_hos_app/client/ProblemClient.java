package com.abhi.gov_hos_app.client;

import com.abhi.gov_hos_app.dto.ProblemGetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", path = "/api/problem")
public interface ProblemClient {

    @GetMapping("/find-by-problemId/{problemId}")
    ResponseEntity<ProblemGetDto> getProblem(@PathVariable("problemId") Long problemId);
}
