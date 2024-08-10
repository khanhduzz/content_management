package com.fpt.content_management.repository;

import com.fpt.content_management.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername (String username);

    @Query("SELECT m FROM Member m WHERE LOWER(m.email) = LOWER(:email) OR LOWER(m.username) = LOWER(:username)")
    Member findByEmailOrUsernameIgnoreCase(@Param("email") String email, @Param("username") String username);

    Member findByEmailIgnoreCase(String email);

    Member findByUsernameIgnoreCase(String username);
}
