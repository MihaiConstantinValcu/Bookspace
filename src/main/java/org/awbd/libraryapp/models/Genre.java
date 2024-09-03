package org.awbd.libraryapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.awbd.libraryapp.models.base.BaseEntity;

import java.util.Set;

@Entity
@Getter
@Setter
public class Genre extends BaseEntity {

    private String name;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "genreId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private Set<Book> books;
}
