package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name="Product", description = "상품 관련 API")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @Operation(description = "상품 상세 조회")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @GetMapping("/{productId}")
    public CustomResponse<ProductResDTO.ProductPreviewResDTO> getProduct(
            @PathVariable("productId") Long productId
    ) {
        return CustomResponse.onSuccess(productQueryService.getProduct(productId));
    }

    @Operation(description = "상품 목록 조회")
    @GetMapping
    public CustomResponse<ProductResDTO.ProductListResDTO> getProducts(){
        return CustomResponse.onSuccess(productQueryService.getProductList());
    }

    @Operation(description = "상품 생성 (로그인 필요)")
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public CustomResponse<ProductResDTO.ProductCreateResDTO> createProduct(
            @RequestBody @Valid ProductReqDTO.ProductCreateReqDTO productCreateReqDTO,
            @AuthenticationPrincipal UserDetails userdetails
    ) {
        return CustomResponse.onSuccess(productCommandService.createProduct(productCreateReqDTO, userdetails.getUsername()));
    }

    @Operation(description = "상품 삭제 (로그인 필요)")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{productId}")
    public CustomResponse<String> deleteProduct(
            @PathVariable("productId") Long productId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails){
        productCommandService.deleteProduct(productId, customUserDetails.getUsername());
        return CustomResponse.onSuccess("상품 삭제 성공");
    }
}
