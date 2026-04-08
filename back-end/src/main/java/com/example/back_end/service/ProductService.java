package com.example.back_end.service;

import com.example.back_end.model.dto.ProductDto;
import com.example.back_end.model.dto.ProductListDto;
import com.example.back_end.model.entity.ProductEntity;
import com.example.back_end.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<@Valid ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        ProductEntity productEntity=new ProductEntity(productDto.name(), productDto.price(),productDto.stock());
        productRepository.save(productEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
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

        if (productDto.price() != null && !Objects.equals(product.getPrice(), productDto.price())) {
            product.setPrice(productDto.price());
        }

        if (productDto.name() != null && !product.getName().equals(productDto.name())) {
            product.setName(productDto.name());
        }

        productRepository.save(product);

        ProductDto updatedProduct = new ProductDto(product.getName(), product.getPrice(),productDto.stock());

        return ResponseEntity.ok(updatedProduct);
    }

}
