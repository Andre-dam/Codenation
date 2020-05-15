package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT DISTINCT(c.id.user) FROM Candidate c WHERE c.id.acceleration.name = ?1")
    List<User> findByAccelerationName(String name);

    @Query("SELECT DISTINCT(c.id.user) FROM Candidate c WHERE c.id.company.id = ?1")
    List<User> findByCompanyId(Long id);
}
