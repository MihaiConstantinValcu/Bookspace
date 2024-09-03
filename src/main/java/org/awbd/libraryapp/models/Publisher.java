package org.awbd.libraryapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.awbd.libraryapp.models.base.BaseEntity;

import java.util.List;

@Entity
@Getter
@Setter
public class Publisher extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
