package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("submission")
public class SubmissionController {

    @Autowired
    SubmissionServiceInterface submissionService;

    @Autowired
    SubmissionMapper submissionMapper;

    @GetMapping()
    List<SubmissionDTO> getByChallengeIdAndAccelerationId(
            @RequestParam Long challengeId,
            @RequestParam Long accelerationId) {
        return submissionMapper
                .map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId));
    }
}
