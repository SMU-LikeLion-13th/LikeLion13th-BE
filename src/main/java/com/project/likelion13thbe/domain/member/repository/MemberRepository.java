package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
