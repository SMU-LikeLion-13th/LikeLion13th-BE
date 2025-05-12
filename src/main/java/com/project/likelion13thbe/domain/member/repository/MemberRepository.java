package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Slice<Member> findAllByMemberIdLessThanOrderByMemberIdDesc(Long memberId, Pageable pageable);

    // 삭제되지 않은 회원 중 이메일로 조회
    @Query("SELECT m " +
            "FROM Member m " +
            "WHERE m.memberId = :memberId AND m.deletedAt IS NULL")
    Optional<Member> findByMemberIdAndNotDeleted(@Param("memberId") Long memberId);

}
