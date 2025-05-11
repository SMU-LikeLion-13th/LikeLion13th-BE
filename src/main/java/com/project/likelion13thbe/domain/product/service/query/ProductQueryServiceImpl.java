package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO.ProductPreviewResDTO getProduct() {
        // DB에서 pk가 1인 Product 조회
        Product product = productRepository.findById(1L).get();

        // 응답 DTO로 변환 후 return
        return ProductConverter.toProductPreviewResponseDTO(product);
    }

    @Override
    public ProductResponseDTO.ProductListResponseDTO getProductList() {
        List<Product> productList = productRepository.findAll();

        return ProductConverter.toProductListResponseDTO(productList);
    }

    @Override
    public ProductResponseDTO.ProductOffsetResponseDTO getProductOffset(Integer offset, Integer size) {
        Pageable pageable = PageRequest.of(offset-1, size);
        // Spring Data JPA의 페이지 번호는 0부터 시작하기 때문dp -1 해주기
        Page<Product> products = productRepository.findAllByOrderByCreatedAtDesc(pageable);

        return ProductConverter.toProductOffsetResponseDTO(products);
    }


}
