package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public ProductResDTO.ProductPreviewResDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId).get();

        Double ratingAvg = reviewRepository.findRatingAvgByProductId(productId);
        Integer reviewCount = reviewRepository.findReviewCountByProductId(productId);

        return ProductConverter.toProductPreviewResDTO(product,
                ratingAvg != null ? ratingAvg : 0.0,
                reviewCount);
    }

    @Override
    public ProductResDTO.ProductListResDTO getProductList() {
        List<Product> products = productRepository.findAll();

        List<Review> allReviews = reviewRepository.findAllByProductIdIn(
                products.stream().map(Product::getId).collect(Collectors.toList()));
        Map<Long, List<Review>> reviewsByProductId = allReviews.stream()
                .collect(Collectors.groupingBy(review -> review.getProduct().getId()));

        List<ProductResDTO.ProductPreviewResDTO> productPreviewResDTOList = products.stream()
                .map(product -> {
                    List<Review> reviews = reviewsByProductId.getOrDefault(product.getId(), Collections.emptyList());
                    Double ratingAvg = reviews.isEmpty() ? 0.0 : reviews.stream()
                            .mapToDouble(Review::getRate)
                            .average()
                            .orElse(0.0);
                    Integer reviewCount = reviews.size();

                    return ProductConverter.toProductPreviewResDTO(product, ratingAvg, reviewCount);
                })
                .collect(Collectors.toList());

        return ProductConverter.toProductPreviewResponseDTOList(productPreviewResDTOList);
    }

}
