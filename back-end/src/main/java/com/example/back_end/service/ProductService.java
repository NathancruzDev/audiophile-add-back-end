package com.example.back_end.service;

import com.example.back_end.model.dto.CartDto;
import com.example.back_end.model.dto.product.ProductDto;
import com.example.back_end.model.dto.product.ProductListDto;
import com.example.back_end.model.dto.user.UserDto;
import com.example.back_end.model.entity.CartEntity;
import com.example.back_end.model.entity.ProductEntity;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        ProductEntity productEntity = new ProductEntity(productDto.getName(), productDto.getPrice(), productDto.getStock());
        ProductEntity savedEntity = productRepository.save(productEntity);
        ProductDto responseDto = new ProductDto(savedEntity.getName(), savedEntity.getPrice(), savedEntity.getStock());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @Transactional
    public void removeProduct(Integer id){
        ProductEntity product=productRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found product."));
        productRepository.delete(product);
    }



    @Transactional
    public List<ProductListDto> getAllProducts() {
        return productRepository.findAll().stream().map(productEntity -> new ProductListDto(productEntity.getId(),
                productEntity.getName(), productEntity.getPrice(),productEntity.getStock())).collect(Collectors.toList());
    }


    @Transactional
    public ResponseEntity<ProductDto> updateProduct(Integer id,ProductDto productDto){
        ProductEntity product=productRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found product."));

        if (productDto.getPrice() != null && !Objects.equals(product.getPrice(), productDto.getPrice())) {
            product.setPrice(productDto.getPrice());
        }

        if (productDto.getName() != null && !product.getName().equals(productDto.getName())) {
            product.setName(productDto.getName());
        }

        productRepository.save(product);

        ProductDto updatedProduct = new ProductDto(product.getName(), product.getPrice(),productDto.getStock());

        return ResponseEntity.ok(updatedProduct);
    }

}
