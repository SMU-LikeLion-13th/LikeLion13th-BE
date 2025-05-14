package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository; //상품 리뷰 조회를 위한 생성자

    @Override
    public ProductResDTO.ProductPreviewResDTO getProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        Double scoreAvg = reviewRepository.findRatingAvgByProductId(productId);
        Integer reviewCount = reviewRepository.findReviewCountByProductId(productId);


        return ProductConverter.toProductPreviewResDTO(product,
                scoreAvg != null ? scoreAvg : 0.0,
                reviewCount);
    }

    @Override
    public ProductResDTO.ProductCursorResDTO getProductCursor(Long cursor, Integer size) {

        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Pageable pageable = PageRequest.of(0,size);

        Slice<Product> products = productRepository.findAllByIdLessThanOrderByIdDesc(cursor, pageable);

        if (products.isEmpty()) {
            throw new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND);
        }

        return ProductConverter.toProductCursorResDTO(products);

    }

}
