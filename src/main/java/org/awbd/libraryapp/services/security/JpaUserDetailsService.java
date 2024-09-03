package org.awbd.libraryapp.services.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.awbd.libraryapp.models.security.Authority;
import org.awbd.libraryapp.models.security.Member;
import org.awbd.libraryapp.repositories.security.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member;

        Optional<Member> userOpt = memberRepository.findByUsername(username);
        if (userOpt.isPresent())
            member = userOpt.get();
        else
            throw new UsernameNotFoundException("Username: " + username);

        log.info(member.toString());

        return new org.springframework.security.core.userdetails.User(member.getUsername(),
                member.getPassword(), member.getEnabled(), member.getAccountNonExpired(),
                member.getCredentialsNonExpired(), member.getAccountNonLocked(),
                getAuthorities(member.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Authority> authorities) {
        if (authorities == null) {
            return new HashSet<>();
        } else if (authorities.isEmpty()) {
            return new HashSet<>();
        } else {
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
    }
}
