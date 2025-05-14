package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.request.ProductRequestDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
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
    public ProductResponseDTO.ProductCreateResponseDTO createProduct(ProductRequestDTO.ProductCreateRequestDTO productCreateRequestDTO) {

        // 임시로 member 추가
        Member member = memberRepository.findByIdAndNotDeleted(productCreateRequestDTO.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // DTO -> Product
        Product product = ProductConverter.toProduct(productCreateRequestDTO, member);

        // Product 엔티티 DB에 저장
        productRepository.save(product);

        // 응답 DTO로 변환 후 return
        return ProductConverter.toProductResponseDTO(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findByIdAndNotDeleted(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        product.delete();
    }

}