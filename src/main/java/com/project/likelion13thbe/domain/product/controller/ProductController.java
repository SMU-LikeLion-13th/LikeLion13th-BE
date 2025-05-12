package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.global.apiPayload.exception.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @Operation(summary = "상품 목록 조회")
    @GetMapping("/products")
    public ProductResDTO.ProductResponeseDTO getProductList(@PathVariable Long ProductId)
    {
        return null;
    }// 얘도 list써야하나



    @Operation(summary = "상품 단일 조회")
    @GetMapping("/products/{productId}")
    @Validated //pathVariable 유효성 검사
    public CustomResponse<ProductResDTO.ProductPreviewResDTO> getProduct(
            @PathVariable("productId") @NotNull Long productId
    ) {
        return CustomResponse.onSuccess(productQueryService.getProduct(productId));
    }

    @Operation(summary = "상품 등록")
    @PostMapping("/products")
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> createProduct(
            @RequestBody ProductReqDTO.ProductCreateReqDTO dto
    )
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(dto));
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("products/{productId}")
    public ProductResDTO.ProductResponeseDTO deleteProduct(@PathVariable Long ProductId){
        return null;
    }

}
