package org.awbd.libraryapp.repositories.security;

import org.awbd.libraryapp.models.security.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}