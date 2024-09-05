package org.awbd.libraryapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowDto {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean returned;
}
