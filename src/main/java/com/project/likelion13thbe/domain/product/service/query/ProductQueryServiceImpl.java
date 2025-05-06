package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
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

        Product product = productRepository.findById(productId).get();

        Double socoreAvg = reviewRepository.findRatingAvgByProductId(productId);
        Integer reviewCount = reviewRepository.findReviewCountByProductId(productId);


        return ProductConverter.toProductPreviewResDTO(product,
                socoreAvg != null ? socoreAvg : 0.0,
                reviewCount);
    }

}
