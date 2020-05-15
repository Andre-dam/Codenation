package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
    @Query("SELECT DISTINCT(a.challenge) FROM Acceleration a JOIN Candidate c ON c.id.acceleration.id = a.id AND a.id = ?1 AND c.id.user.id = ?2")
    List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId);
}
