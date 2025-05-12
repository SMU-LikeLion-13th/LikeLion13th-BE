package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public ProductResDTO.ProductCreateResDTO createProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        // 멤버를 토큰으로 구별하지만 아직 방법을 몰라서 일단 직접 주입
        // Req로 받은 멤버 아이디로 멤버 객체를 찾고
        Member member = memberRepository.findById(productCreateReqDTO.memberId())
                // 이 부분 일단 이렇게만 해놓겠습니다
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // ProductCreateReqDTO + Member => Entity
        Product product = ProductConverter.toProduct(productCreateReqDTO, member);

        productRepository.save(product);

        return ProductConverter.toProductResDTO(product);
    }
}
