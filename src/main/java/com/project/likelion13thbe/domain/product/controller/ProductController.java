package com.project.likelion13thbe.domain.product.controller;


import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import com.project.likelion13thbe.domain.product.dto.response.ProductResDTO;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.product.service.command.ProductCommandService;
import com.project.likelion13thbe.domain.product.service.query.ProductQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품 관련", description = "상품 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;
    private final ProductRepository productRepository;


    //상품 목록 조회
    @Operation(summary = "상품 목록 조회 API", description = "offset 기반 상품 목록 조회 API입니다.")
    @GetMapping("/users/products/offset")
    public ResponseEntity<ProductResDTO.ProductOffsetResDTO> getProductList(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(productQueryService.getProductOffset(offset, size));
    }

    //상품 상세 조회
    @Operation(summary = "상품 상세 조회 API", description = "상품 상세 조회 API입니다.")
    @GetMapping("/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ResponseEntity<ProductResDTO.ProductPreviewResDTO> getProduct(@PathVariable long productId) {
        return ResponseEntity.ok(productQueryService.getProduct());
    }

    //상품 추가
    @Operation(summary = "상품 추가 API", description = "상품 추가 API입니다.")
    @PostMapping("/products")
    public ResponseEntity<ProductResDTO.ProductCreateResDTO> createProduct(
            @RequestBody ProductReqDTO.ProductCreateReqDTO productCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCommandService.createProduct(productCreateReqDTO));
    }

    //상품 삭제
    @Operation(summary = "상품 삭제 API", description = "상품 삭제 API")
    @DeleteMapping("/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 삭제 성공")
    })
    public CustomResponse<String> deleteProduct(@PathVariable long productId) {
        productCommandService.deleteProduct(productId);
        return CustomResponse.onSuccess("상품 삭제 성공");
    }
}
