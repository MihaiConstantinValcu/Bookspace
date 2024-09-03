package org.awbd.libraryapp.bootstrap;

import lombok.AllArgsConstructor;
import org.awbd.libraryapp.enums.Authorities;
import org.awbd.libraryapp.models.security.Authority;
import org.awbd.libraryapp.models.security.Member;
import org.awbd.libraryapp.repositories.security.AuthorityRepository;
import org.awbd.libraryapp.repositories.security.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private AuthorityRepository authorityRepository;
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;


    private void loadUserData() {
        if (memberRepository.count() == 0) {
            Authority adminRole = authorityRepository.save(Authority.builder().role(Authorities.ROLE_ADMIN.name()).build());
            Authority memberRole = authorityRepository.save(Authority.builder().role(Authorities.ROLE_MEMBER.name()).build());

            Member admin = Member.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("12345"))
                    .authority(adminRole)
                    .build();

            Member member = Member.builder()
                    .username("member")
                    .password(passwordEncoder.encode("12345"))
                    .authority(memberRole)
                    .build();

            memberRepository.save(admin);
            memberRepository.save(member);
        }
    }


    @Override
    public void run(String... args) {
        loadUserData();
    }
}