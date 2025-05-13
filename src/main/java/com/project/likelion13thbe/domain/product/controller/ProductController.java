package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.converter.ProductConverter;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryServiceImpl;
import com.project.likelion13thbe.global.apiPayload.exception.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    @GetMapping("/products/")
    public CustomResponse<ProductResDTO.ProductCursorResDTO> getProductList(@RequestParam(required = false, defaultValue = "0") Long cursor,
                                                            @RequestParam Integer size)
    {
        return CustomResponse.onSuccess(productQueryService.getProductCursor(cursor, size));
    }



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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 삭제 성공")
    })
    public CustomResponse<String> deleteProduct(@PathVariable Long ProductId){
        productCommandService.deleteProduct(ProductId);
        return CustomResponse.onSuccess("상품 삭제 성공");
    }

}
