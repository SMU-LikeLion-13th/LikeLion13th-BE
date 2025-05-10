package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m ORDER BY m.createdAt DESC")
    Page<Member> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT m FROM Member m WHERE m.id < :id ORDER BY m.id DESC")
    Slice<Member> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.deletedAt IS NULL")
    Optional<Member> findByEmailAndNotDeleted(@Param("email") String email);

    @Query("SELECT m FROM Member m WHERE m.id = :id AND m.deletedAt IS NULL")
    Optional<Member> findByIdAndNotDeleted(@Param("id") Long memberId);

}
