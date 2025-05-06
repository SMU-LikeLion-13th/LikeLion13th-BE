package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductRequestDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResponseDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandServiceImpl;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryServiceImpl;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductCommandServiceImpl productCommandService;
    private final ProductCommandServiceImpl productCommandServiceImpl;
    private final ProductQueryService productQueryService;
    private final ProductQueryServiceImpl productQueryServiceImpl;

    @Operation(summary = "상품 상세 조회 API", description = "상품 상세 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "상품 상세 조회 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDTO.ProductCreateResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "상품 상세 조회 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameter(name = "productId", description = "상품 아이디", example = "1")
    @GetMapping("/api/v1/products/{productId}")
    public ResponseEntity<ProductResponseDTO.ProductPreviewResDTO> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productQueryServiceImpl.getProduct());
    }

    @Operation(summary = "상품 목록 조회 API", description = "상품 목록 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "상품 목록 조회 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "상품 목록 조회 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/api/v1/products")
    public ResponseEntity<ProductResponseDTO.ProductListResponseDTO> getProducts() {
        return ResponseEntity.ok(productQueryServiceImpl.getProductList());
    }


    @Operation(summary = "상품 생성 API", description = "상품 생성")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "상품 생성 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDTO.ProductCreateResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "리뷰 생성 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResponseDTO.ProductCreateResponseDTO> postProduct(
            @RequestBody ProductRequestDTO.ProductCreateRequestDTO requestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(requestDTO));
    }

    @Operation(summary = "상품 삭제 API", description = "상품 삭제")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "상품 삭제 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDTO.ProductCreateResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "상품 삭제 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    @DeleteMapping("api/v1/products/{productId}")
    public ProductResponseDTO.ProductCreateResponseDTO deleteProduct(@PathVariable Long productId) {
        return null;
    }
}