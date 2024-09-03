package org.awbd.libraryapp.models.security;

import jakarta.persistence.*;
import lombok.*;
import org.awbd.libraryapp.models.Borrow;
import org.awbd.libraryapp.models.Membership;
import org.awbd.libraryapp.models.base.BaseEntity;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Member extends BaseEntity {

    private String username;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Borrow> borrows;

    @OneToMany(mappedBy = "member")
    private List<Membership> memberships;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "member_authority", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean accountNonExpired = true;
    @Builder.Default
    private Boolean accountNonLocked = true;
    @Builder.Default
    private Boolean credentialsNonExpired = true;
    @Builder.Default
    private Boolean enabled = true;
}
