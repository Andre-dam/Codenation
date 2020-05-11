package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Candidate {
    @EmbeddedId
    private CandidateId id;

    @NotNull
    private Integer status;

    @NotNull
    @CreatedDate
    private Date createdAt;

    @Embeddable
    static class CandidateId {
        @ManyToOne
        User user;

        @ManyToOne
        Acceleration acceleration;

        @ManyToOne
        Company company;
    }
}

