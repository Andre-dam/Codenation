package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
}
