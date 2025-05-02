package com.project.likelion13thbe.domain.member.repository;


import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAllByOrderByCreatedAtDesc(Pageable pageable); // 생성일 기준 내림차순
}
