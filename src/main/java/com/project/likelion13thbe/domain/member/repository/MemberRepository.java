package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
public interface MemberRepository extends JpaRepository<Member, Long> {

    Page<Member> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Slice<Member> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
}
