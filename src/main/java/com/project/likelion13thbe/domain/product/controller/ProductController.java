package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Validated
@Tag(name="Product", description = "상품 관련 API")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @Operation(description = "상품 상세 조회")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @GetMapping("/{productId}")
    public CustomResponse<ProductResDTO.ProductPreviewResDTO> getProduct(
            @PathVariable("productId") @NotNull Long productId
    ) {
        return CustomResponse.onSuccess(productQueryService.getProduct(productId));
    }

    @Operation(description = "상품 목록 조회")
    @GetMapping
    public CustomResponse<ProductResDTO.ProductListResDTO> getProducts(){
        return CustomResponse.onSuccess(productQueryService.getProductList());
    }

    @Operation(description = "상품 생성")
    @PostMapping
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> createProduct(
            @RequestBody ProductReqDTO.ProductCreateReqDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(dto));
    }

    @Operation(description = "상품 삭제")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @DeleteMapping("/{productId}")
    public CustomResponse<String> deleteProduct(@PathVariable("productId") @NotNull Long productId) {
        productCommandService.deleteProduct(productId);
        return CustomResponse.onSuccess("상품 삭제 성공");
    }
}
