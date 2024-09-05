package org.awbd.libraryapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.awbd.libraryapp.models.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Book extends BaseEntity {

    private String title;

    @Column(nullable = false)
    @SequenceGenerator(name = "book_number_sequence", sequenceName = "book_number_sequence", allocationSize = 1)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private Set<Genre> genres = new HashSet<>();

    @OneToOne(mappedBy = "book")
    private Borrow borrow;
}
