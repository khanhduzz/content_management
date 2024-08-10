package com.fpt.content_management;

import com.fpt.content_management.entity.Member;
import com.fpt.content_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class ContentManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ContentManagementApplication.class, args);
    }

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${application.member.default.email}")
    private String email;

    @Value("${application.member.default.password}")
    private String password;


    @Override
    public void run(String... args) throws Exception {
        Member member = Member.builder()
                .email(email)
                .username("James")
                .password(passwordEncoder.encode(password))
                .build();
        memberRepository.save(member);
    }
}
