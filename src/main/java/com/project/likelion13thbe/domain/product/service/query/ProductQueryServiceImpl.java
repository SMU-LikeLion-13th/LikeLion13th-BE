package com.project.likelion13thbe.domain.product.service.query;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.ProductDetailDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    @Override
    public ProductResDTO.ProductDetailResDTO getProduct(Long productId) {
        ProductDetailDTO productDetailDTO = productRepository.findProductWithReviewStatsAndNotDeleted(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        return ProductConverter.toProductDetailResDTO(
                productDetailDTO.product(), productDetailDTO.ratingAvg(), productDetailDTO.reviewCount().intValue());
    }

    @Override
    public ProductResDTO.ProductListResDTO getProductList() {
        List<ProductDetailDTO> productDetailDTOList = productRepository.findAllProductsWithReviewStatsAndNotDeleted();

        if (productDetailDTOList.isEmpty()) {
            throw new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND);
        }

        List<ProductResDTO.ProductDetailResDTO> productDetailResDTOList =
                productDetailDTOList.stream()
                        .map(productDetailDTO -> ProductConverter.toProductDetailResDTO(
                                productDetailDTO.product(), productDetailDTO.ratingAvg(), productDetailDTO.reviewCount().intValue()))
                        .toList();

        return ProductConverter.toProductListResDTO(productDetailResDTOList);
    }

    @Override
    public ProductResDTO.ProductCursorResDTO getProductCursor(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        // cursor가 0일 경우(첫페이지) cursor 최대값
        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<ProductDetailDTO> productDetailDTOSlice = productRepository.findAllByProductIdLessThanOrderByProductIdDesc(cursor, pageable);

        return ProductConverter.toProductCursorResDTO(productDetailDTOSlice);
    }

}
