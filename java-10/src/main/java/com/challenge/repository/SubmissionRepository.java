package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {

    @Query("SELECT MAX(s.score) FROM Submission s WHERE s.id.challenge.id = ?1")
    BigDecimal findHigherScoreByChallengeId(Long challengeId);

    @Query("SELECT s FROM Submission s JOIN Acceleration a ON a.challenge.id = s.id.challenge.id AND a.challenge.id = ?1 AND a.id = ?2")
    List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId);
}
