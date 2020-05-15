package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    @Query("SELECT DISTINCT(c.id.company) FROM Candidate c WHERE c.id.acceleration.id = ?1")
    List<Company> findByAccelerationId(Long accelerationId);

    @Query("SELECT DISTINCT(c.id.company) FROM Candidate c WHERE c.id.user.id = ?1")
    List<Company> findByUserId(Long userId);
}
