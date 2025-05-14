package com.project.likelion13thbe.domain.product.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.product.convert.ProductConvert;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    @Override
    public ProductResDTO.ProductCreateResDTO addProduct(ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        Product product = ProductConvert.toProduct(productCreateReqDTO);

        productRepository.save(product);

        return ProductConvert.toProductResponse(product);

    }
    @Override
    public void deleteProduct(Long id){
        Product product=productRepository.findByIdAndNotDeleted(id)
                .orElseThrow(()-> new ProductException(ProductErrorCode.PRODUCT_ERROR_CODE));
        product.delete();
    }
}
