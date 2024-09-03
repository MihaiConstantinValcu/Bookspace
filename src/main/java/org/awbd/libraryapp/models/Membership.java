package org.awbd.libraryapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.awbd.libraryapp.models.base.BaseEntity;
import org.awbd.libraryapp.models.security.Member;

@Entity
@Getter
@Setter
public class Membership extends BaseEntity {

    private String startDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}
