package com.challenge;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.entity.Company;
import com.challenge.entity.User;
import com.challenge.repository.AccelerationRepository;
import com.challenge.repository.CandidateRepository;
import com.challenge.repository.CompanyRepository;
import com.challenge.repository.UserRepository;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringChallengeApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccelerationRepository accelerationRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CandidateRepository candidateRepository;


    @Transactional
    void mockDb() {
        User user1 = new User(1L, "Andre", "alds@cin.ufpe.br", "Andy", "123", LocalDateTime.now(), null, null);
        userRepository.save(user1);

        Acceleration acceleration1 = new Acceleration(1L, "Java", "JavaAccel", null, null, LocalDateTime.now());
        accelerationRepository.save(acceleration1);

        Company company1 = new Company(1L, "inloco", "adsa", null, LocalDateTime.now());
        companyRepository.save(company1);

        candidateRepository.save(new Candidate(new CandidateId(user1, acceleration1, company1), 1, LocalDateTime.now()));

        Company company2 = new Company(2L, "serttel", "adasdasdsa", null, LocalDateTime.now());
        companyRepository.save(company2);

        candidateRepository.save(new Candidate(new CandidateId(user1, acceleration1, company2), 1, LocalDateTime.now()));

        Acceleration acceleration2 = new Acceleration(2L, "Python", "JavaAccel", null, null, LocalDateTime.now());
        accelerationRepository.save(acceleration2);

        candidateRepository.save(new Candidate(new CandidateId(user1, acceleration2, company1), 1, LocalDateTime.now()));

    }

    @Test
    public void UserServiceTest() {
        mockDb();
        // findById
        User user = userRepository.findById(1L).get();
        assertEquals(user.getId().longValue(), 1L);

        // findByAccelerationName
        List<User> users = userRepository.findByAccelerationName("Java");
        assertEquals(users.size(), 1);
        assertEquals(users.iterator().next().getId().longValue(), 1L);

        // findByCompanyId
        List<User> companyUsers = userRepository.findByCompanyId(1L);
        assertEquals(companyUsers.size(), 1);
        assertEquals(companyUsers.iterator().next().getId().longValue(), 1L);
    }

    @Test
    public void CandidateServiceTest() {
        mockDb();

        // findById
        Candidate candidate = candidateRepository.findById(1L, 1L, 1L).get();
        assertEquals(candidate.getId().getUser().getId().longValue(), 1L);
        assertEquals(candidate.getId().getAcceleration().getId().longValue(), 1L);
        assertEquals(candidate.getId().getCompany().getId().longValue(), 1L);

        // findById with CandidateId
        Candidate candidate1 = candidateRepository.findById(
                new CandidateId(
                        userRepository.findById(1L).get(),
                        accelerationRepository.findById(1L).get(),
                        companyRepository.findById(1L).get())
        ).get();

        assertEquals(candidate.getId().getUser().getId().longValue(), 1L);
        assertEquals(candidate.getId().getAcceleration().getId().longValue(), 1L);
        assertEquals(candidate.getId().getCompany().getId().longValue(), 1L);

        // findByCompanyId
        List<Candidate> candidates = candidateRepository.findById_CompanyId(2L);
        assertEquals(candidates.size(), 1);
        assertEquals(candidates.iterator().next().getId().getCompany().getId().longValue(), 2L);

        // findByAccelerationId
        List<Candidate> candidates1 = candidateRepository.findById_AccelerationId(2L);
        assertEquals(candidates1.size(), 1);
        assertEquals(candidates1.iterator().next().getId().getAcceleration().getId().longValue(), 2L);
    }
}
