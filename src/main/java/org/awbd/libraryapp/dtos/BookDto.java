package org.awbd.libraryapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookDto {
    private String id;
    private Integer number;
    private String title;
    private AuthorDto author;
    private PublisherDto publisher;
    private Set<GenreDto> genres;
    private BorrowDto borrow;
}
