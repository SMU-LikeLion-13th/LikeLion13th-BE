package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m ORDER BY m.createdAt DESC")
    Page<Member> findAllByOrderByIdDesc(Pageable pageable);

    @Query("SELECT m FROM Member m WHERE m.id< :id ORDER BY m.id DESC")
    Slice<Member> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
}

