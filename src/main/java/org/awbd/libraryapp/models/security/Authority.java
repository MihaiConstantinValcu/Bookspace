package org.awbd.libraryapp.models.security;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.awbd.libraryapp.models.base.BaseEntity;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Authority extends BaseEntity {
    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<Member> members;
}
