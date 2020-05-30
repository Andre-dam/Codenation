package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.endpoints.exceptions.ResourceNotFoundException;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @Autowired
    CandidateMapper candidateMapper;

    @GetMapping(value = "/candidate", params = {"userId", "companyId", "accelerationId"})
    ResponseEntity<CandidateDTO> findById(@RequestParam Long userId, @RequestParam Long companyId, @RequestParam Long accelerationId) {
        return new ResponseEntity<>(candidateService.findById(userId, companyId, accelerationId)
                .map(this.candidateMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate")), HttpStatus.OK);
    }

    @GetMapping(value = "/candidate", params = "companyId")
    List<CandidateDTO> findByCompanyId(@RequestParam Long companyId) {
        return this.candidateMapper.map(candidateService.findByCompanyId(companyId));
    }

    @GetMapping(value = "/candidate", params = "accelerationId")
    List<CandidateDTO> findByAccelerationId(@RequestParam Long accelerationId) {
        return this.candidateMapper.map(candidateService.findByAccelerationId(accelerationId));
    }
}
