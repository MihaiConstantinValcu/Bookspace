package org.awbd.libraryapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.awbd.libraryapp.models.base.BaseEntity;
import org.awbd.libraryapp.models.security.Member;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Borrow extends BaseEntity {

    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean returned = false;

    @OneToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}
