package com.project.likelion13thbe.domain.product.controller;

import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/products")
@Tag(name="Product", description = "상품 관련 API")
public class ProductController {


    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @Operation(description = "상품 상세 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "Ok, 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResDTO.ProductCreateResDTO.class)
                    )
            )
    })
    @Parameter(name = "productId", description = "product PK", example = "1")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResDTO.ProductPreviewResDTO> getProduct(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(productQueryService.getProduct(productId));
    }

    @Operation(description = "상품 목록 조회")
    @GetMapping("/api/v1/products")
    public ResponseEntity<ProductResDTO.ProductListDTO> getProducts(){
        return ResponseEntity.ok(null);
    @GetMapping
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
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(null);
    }
}
