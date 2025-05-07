package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 삭제되지 않은 회원 중 id로 조회
    @Query("SELECT m FROM Member m WHERE m.id = :Id AND m.deletedAt IS NULL")
    Optional<Member> findByIdAndNotDeleted(@Param("Id") Long id);
}
