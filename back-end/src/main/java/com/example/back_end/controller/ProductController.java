package com.example.back_end.controller;

import com.example.back_end.model.dto.product.ProductDto;
import com.example.back_end.model.dto.product.ProductListDto;
import com.example.back_end.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        System.out.println("JSON RECEBIDO: " + productDto);
        return productService.createProduct(productDto);
    }
    @DeleteMapping("/removeProduct/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable Integer id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductListDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }
}