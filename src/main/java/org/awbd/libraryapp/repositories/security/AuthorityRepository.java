package org.awbd.libraryapp.repositories.security;

import org.awbd.libraryapp.models.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findFirstByRole(String role);
}
