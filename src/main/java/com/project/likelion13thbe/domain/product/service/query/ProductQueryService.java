package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQueryService {
    private final ProductRepository productRepository;

    public ProductResDTO.ProductPreviewResDTO getProduct() {
        Product product = productRepository.findById(1L).get();

        return ProductConverter.toProductPreviewResDTO(product);
    }

    public ProductResDTO.ProductOffsetResDTO getProductOffset(Integer offset, Integer size) {
        Pageable pageable = PageRequest.of(offset -1, size);

        Page<Product> products = productRepository.findAllByOrderByCreatedAtDesc(pageable);

        return ProductConverter.toProductOffsetResDTO(products);
    }

    public ProductResDTO.ProductCursorResDTO getProductCursor(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Product> products = productRepository.findAllByProductIdLessThanOrderByProductIdDesc(cursor, pageable);

        return ProductConverter.toProductCursorResDTO(products);
    }
}
