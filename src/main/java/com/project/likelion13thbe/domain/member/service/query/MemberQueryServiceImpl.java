package com.project.likelion13thbe.domain.member.service.query;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.apiPayload.code.GeneralErrorCode;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberResDTO.MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(GeneralErrorCode.NOT_FOUND_404));

        return MemberConverter.toDTO(member);
    }
}