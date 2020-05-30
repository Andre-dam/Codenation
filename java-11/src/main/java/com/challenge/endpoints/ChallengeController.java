package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("challenge")
public class ChallengeController {
    @Autowired
    ChallengeServiceInterface challengeService;

    @GetMapping()
    public List<Challenge> getByAccelerationIdAndUserId(
            @RequestParam Long accelerationId,
            @RequestParam Long userId) {
        return challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
    }
}
