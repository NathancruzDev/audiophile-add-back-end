package com.example.back_end.service;

import com.example.back_end.model.dto.ProductPurchasedDto;
import com.example.back_end.model.entity.PurchasedEntity;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.PurchasedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedService {
    ProductService productService;
    ProductRepository productRepository;
    PurchasedEntity purchasedEntity;
    PurchasedRepository purchasedRepository;
    OrderHashCodeService orderHashCodeService;

    //test
    AprovedPurchaseImprovedService paymentTest;

    public PurchasedService(ProductService productService, ProductRepository productRepository, PurchasedEntity purchasedEntity, PurchasedRepository purchasedRepository, OrderHashCodeService orderHashCodeService, AprovedPurchaseImprovedService paymentTest) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.purchasedEntity = purchasedEntity;
        this.purchasedRepository = purchasedRepository;
        this.orderHashCodeService = orderHashCodeService;
        this.paymentTest = paymentTest;
    }

    //Metodos de comprar -> vai receber tudo que tem no carrinho, criar a cada elemente(da para usar lambda) um novo elemento de tabela comprada, implementar o hashcode de compra

    //esse metodo tem que verificar se o produto existe, se a quantidade e valida, se o estoque e suficiente , calcular os valores totais pegar o preco da tabela Product entity,
    //fazer seus metodos matematicos e atualizar a tabela se a compra for aprovada.
    public void checkoutQuantity(List<ProductPurchasedDto> listProductsCart){

    }

}
