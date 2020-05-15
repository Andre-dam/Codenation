package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {
    @Query("SELECT c.id.acceleration FROM Candidate c WHERE c.id.company.id = ?1")
    List<Acceleration> findByCompanyId(Long companyId);
}
