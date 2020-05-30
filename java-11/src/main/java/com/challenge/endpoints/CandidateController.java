package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.endpoints.exceptions.ResourceNotFoundException;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("candidate")
public class CandidateController {
    @Autowired
    CandidateServiceInterface candidateService;

    @Autowired
    CandidateMapper candidateMapper;

    @GetMapping(value = "/{userId}/{accelerationId}/{companyId}")
    ResponseEntity<CandidateDTO> findById(
            @PathVariable Long userId,
            @PathVariable Long accelerationId,
            @PathVariable Long companyId) {
        return new ResponseEntity<>(candidateService.findById(userId, companyId, accelerationId)
                .map(this.candidateMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate")), HttpStatus.OK);
    }

    @GetMapping(params = "companyId")
    List<CandidateDTO> findByCompanyId(@RequestParam Long companyId) {
        return this.candidateMapper.map(candidateService.findByCompanyId(companyId));
    }

    @GetMapping(params = "accelerationId")
    List<CandidateDTO> findByAccelerationId(@RequestParam Long accelerationId) {
        return this.candidateMapper.map(candidateService.findByAccelerationId(accelerationId));
    }
}
