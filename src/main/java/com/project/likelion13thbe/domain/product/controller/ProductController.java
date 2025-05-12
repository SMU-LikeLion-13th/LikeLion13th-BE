package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @Operation(summary = "상품 상세 조회")
    @GetMapping("/{productId}")
    public CustomResponse<ProductResDTO.ProductDetailResDTO> getProduct(@PathVariable Long productId) {
        return CustomResponse.onSuccess(productQueryService.getProduct(productId));
    }

    @Operation(summary = "상품 목록 조회")
    @GetMapping
    public CustomResponse<ProductResDTO.ProductListResDTO> getProductList() {
        return CustomResponse.onSuccess(productQueryService.getProductList());
    }

    @Operation(summary = "상품 추가")
    @PostMapping
    public CustomResponse<ProductResDTO.ProductCreateResDTO> addProduct(
            @RequestBody @Valid ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return CustomResponse
                .onSuccess(productCommandService.createProduct(productCreateReqDTO));
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("{productId}")
    public CustomResponse<String> deleteProduct(@PathVariable Long productId) {
        productCommandService.deleteProduct(productId);
        return CustomResponse.onSuccess(HttpStatus.NO_CONTENT, "상품 삭제 완료");
    }

    @Operation(summary = "상품 목록 조회 (커서 방식)")
    @GetMapping("/cursor")
    public CustomResponse<ProductResDTO.ProductCursorResDTO> getProductCursor(
            @RequestParam Long cursor,
            @RequestParam Integer size
    ) {
        return CustomResponse.onSuccess(productQueryService.getProductCursor(cursor, size));
    }
}
