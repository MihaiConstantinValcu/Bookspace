package org.awbd.libraryapp.services;

import lombok.RequiredArgsConstructor;
import org.awbd.libraryapp.dtos.RegisterDto;
import org.awbd.libraryapp.enums.Authorities;
import org.awbd.libraryapp.exceptions.ResourceAlreadyExistsExceptions;
import org.awbd.libraryapp.models.security.Authority;
import org.awbd.libraryapp.models.security.Member;
import org.awbd.libraryapp.repositories.security.AuthorityRepository;
import org.awbd.libraryapp.repositories.security.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterDto payload) {
        Optional<Member> memberCheck = memberRepository.findByUsername(payload.getUsername());

        if (memberCheck.isPresent()) {
            throw new ResourceAlreadyExistsExceptions("There is already an user with this username");
        }

        Authority memberAuthority = authorityRepository.findFirstByRole(Authorities.ROLE_MEMBER.name());
        Member member = Member.builder()
                .username(payload.getUsername())
                .password(passwordEncoder.encode(payload.getPassword()))
                .authority(memberAuthority)
                .build();

        memberRepository.save(member);
    }
}
