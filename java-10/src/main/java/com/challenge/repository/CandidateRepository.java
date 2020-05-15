package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    Optional<Candidate> findById(CandidateId id);

    @Query("SELECT c FROM Candidate c WHERE c.id.user.id = ?1 AND c.id.acceleration.id = ?2 AND c.id.company.id = ?3")
    Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId);

    List<Candidate> findById_CompanyId(Long companyId);

    List<Candidate> findById_AccelerationId(Long accelerationId);

}
