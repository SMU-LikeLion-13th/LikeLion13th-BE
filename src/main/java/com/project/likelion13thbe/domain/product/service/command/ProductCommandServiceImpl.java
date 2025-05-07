package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
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
        Member member = memberRepository.findById(productCreateReqDTO.memberId())
                .orElseThrow(() -> new RuntimeException("memberId에 해당하는 member가 존재하지 않습니다."));

        Product product = ProductConverter.toProduct(productCreateReqDTO, member);

        productRepository.save(product);

        return ProductConverter.toProductResponseDTO(product);
    }
}
