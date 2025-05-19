package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.email = :Email AND m.deletedAt IS NULL")
    Optional<Member> findByEmail(@Param("Email") String email);

    @Query("SELECT m FROM Member m WHERE m.deletedAt is not null AND m.deletedAt < :OneMonthAgo")
    List<Member> findDeletedMembersBefore(@Param("OneMonthAgo") LocalDateTime oneMonthAgo);

}
