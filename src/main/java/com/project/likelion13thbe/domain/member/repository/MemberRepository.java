package com.project.likelion13thbe.domain.member.repository;


import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAllByOrderByCreatedAtDesc(Pageable pageable); // 생성일 기준 내림차순
    Slice<Member> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable); // 특정 ID보다 작은 회원들 중에서 ID 기준 내림차순 정렬하여 슬라이스 단위 조회
}
