package com.challenge;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.entity.Challenge;
import com.challenge.entity.Company;
import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import com.challenge.entity.User;
import com.challenge.repository.AccelerationRepository;
import com.challenge.repository.CandidateRepository;
import com.challenge.repository.ChallengeRepository;
import com.challenge.repository.CompanyRepository;
import com.challenge.repository.SubmissionRepository;
import com.challenge.repository.UserRepository;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    SubmissionRepository submissionRepository;

    @Transactional
    void mockDb() {
        User user1 = new User(1L, "Andre", "alds@gmail.com", "Andy", "123", LocalDateTime.now(), null, null);
        userRepository.save(user1);

        Challenge challenge1 = new Challenge(1L, "challenge1", "challengename", null, null, LocalDateTime.now());
        challengeRepository.save(challenge1);

        Challenge challenge2 = new Challenge(2L, "challenge2", "challengename2", null, null, LocalDateTime.now());
        challengeRepository.save(challenge2);

        Acceleration acceleration1 = new Acceleration(1L, "Java", "JavaAccel", challenge2, null, LocalDateTime.now());
        accelerationRepository.save(acceleration1);

        Company company1 = new Company(1L, "facebook", "adsa", null, LocalDateTime.now());
        companyRepository.save(company1);

        candidateRepository.save(new Candidate(new CandidateId(user1, acceleration1, company1), 1, LocalDateTime.now()));

        Company company2 = new Company(2L, "google", "adasdasdsa", null, LocalDateTime.now());
        companyRepository.save(company2);

        candidateRepository.save(new Candidate(new CandidateId(user1, acceleration1, company2), 1, LocalDateTime.now()));

        Acceleration acceleration2 = new Acceleration(2L, "Python", "JavaAccel", null, null, LocalDateTime.now());
        accelerationRepository.save(acceleration2);

        candidateRepository.save(new Candidate(new CandidateId(user1, acceleration2, company1), 1, LocalDateTime.now()));

        User user2 = new User(2L, "Anderthon", "dasd@gmail.com", "Anddy", "1123", LocalDateTime.now(), null, null);
        userRepository.save(user2);

        Submission submission1 = new Submission(new SubmissionId(challenge1, user1), 56f, LocalDateTime.now());
        submissionRepository.save(submission1);

        Submission submission2 = new Submission(new SubmissionId(challenge2, user2), 51f, LocalDateTime.now());
        submissionRepository.save(submission2);
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

    @Test
    public void SubmissionRepositoryTest() {
        mockDb();

        // findHigherScoreByChallengeId
        BigDecimal res = submissionRepository.findHigherScoreByChallengeId(1L);
        assertEquals(res, BigDecimal.valueOf(56f));

        // findByChallengeIdAndAccelerationId
        List<Submission> submissions = submissionRepository.findByChallengeIdAndAccelerationId(2L, 1L);
        assertEquals(submissions.size(), 1);
        assertEquals(submissions.iterator().next().getId().getChallenge().getId().longValue(), 2L);
    }

    @Test
    public void AccelerationRepositoryTest() {
        mockDb();

        // findById
        Acceleration acceleration = accelerationRepository.findById(1L).get();
        assertEquals(acceleration.getId().longValue(), 1L);

        // findByCompanyId
        List<Acceleration> accelerations = accelerationRepository.findByCompanyId(2L);
        assertEquals(accelerations.size(), 1);
        assertEquals(accelerations.iterator().next().getId().longValue(), 1L);
    }

    @Test
    public void CompanyRepositoryTest(){
        mockDb();
        //findById
        Company company = companyRepository.findById(1L).get();
        assertEquals(company.getId().longValue(), 1L);

        // findByAccelerationId
        List<Company> companies = companyRepository.findByAccelerationId(2L);
        assertEquals(companies.size(), 1);
        assertEquals(companies.iterator().next().getId().longValue(), 1L);

        // findByUserId
        List<Company> companies1 = companyRepository.findByUserId(1L);
        assertEquals(companies1.size(), 2);
    }

    @Test
    public void ChallengeRepositoryTest(){
        mockDb();

        // findByAccelerationIdAndUserId
        List<Challenge> challenges = challengeRepository.findByAccelerationIdAndUserId(1L,1L);
        assertEquals(challenges.size(), 1);
        assertEquals(challenges.iterator().next().getId().longValue(), 2L);
    }
}
