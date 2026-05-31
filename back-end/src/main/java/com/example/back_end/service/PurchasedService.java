package com.example.back_end.service;

import com.example.back_end.model.dto.OrderPendingDto;
import com.example.back_end.model.dto.product.ProductPurchasedDto;
import com.example.back_end.model.dto.user.GetUsersDto;
import com.example.back_end.model.dto.CheckoutResponseDto;
import com.example.back_end.model.entity.*;
import com.example.back_end.repository.OrderPendingRepository;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.PurchasedRepository;
import com.example.back_end.repository.UserRepository;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchasedService {

    private final ProductRepository productRepository;
    private final PurchasedRepository purchasedRepository;
    private final OrderHashCodeService orderHashCodeService;
    private final UserRepository userRepository;
    private final OrderPendingRepository orderPendingRepository;
    private final StripePaymentService stripePaymentService;

    public PurchasedService(
            ProductRepository productRepository,
            PurchasedRepository purchasedRepository,
            OrderHashCodeService orderHashCodeService,
            UserRepository userRepository,
            OrderPendingRepository orderPendingRepository,
            StripePaymentService stripePaymentService
    ) {
        this.productRepository = productRepository;
        this.purchasedRepository = purchasedRepository;
        this.orderHashCodeService = orderHashCodeService;
        this.userRepository = userRepository;
        this.orderPendingRepository = orderPendingRepository;
        this.stripePaymentService = stripePaymentService;
    }

    @Transactional
    public CheckoutResponseDto checkout(
            List<ProductPurchasedDto> productsInCart,
             GetUsersDto userDto,
            FormPayment formPayment
    ) {
        List<ProductPurchasedDto> expandedCart = validateAndExpandCart(productsInCart);

        UserEntity user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new RuntimeException("This user does not exist."));

        String orderHash = String.valueOf(orderHashCodeService.codeGeneration());

        createPendingOrders(expandedCart, user, formPayment, orderHash);

        Long amountInCents = calculateAmountInCents(expandedCart);

        PaymentIntent paymentIntent = stripePaymentService.createPaymentIntent(
                orderHash,
                amountInCents,
                "brl"
        );

        return new CheckoutResponseDto(
                orderHash,
                paymentIntent.getId(),
                paymentIntent.getClientSecret(),
                amountInCents
        );
    }

    private List<ProductPurchasedDto> validateAndExpandCart(List<ProductPurchasedDto> productsInCart) {
        if (productsInCart == null || productsInCart.isEmpty()) {
            throw new RuntimeException("The cart is empty.");
        }

        List<ProductPurchasedDto> expandedCart = new ArrayList<>();

        for (ProductPurchasedDto product : productsInCart) {
            ProductEntity productEntity = productRepository.findById(product.id())
                    .orElseThrow(() -> new RuntimeException("Product not found."));

            if (product.quantity() == null || product.quantity() <= 0) {
                throw new RuntimeException("Invalid quantity.");
            }

            if (productEntity.getStock() <= 0) {
                throw new RuntimeException("This item is out of stock.");
            }

            if (productEntity.getStock() < product.quantity()) {
                throw new RuntimeException("Insufficient stock for this item.");
            }

            if (productEntity.getPrice().compareTo(product.price()) != 0) {
                throw new RuntimeException("Front-end price is different from back-end price.");
            }

            for (int i = 0; i < product.quantity(); i++) {
                expandedCart.add(new ProductPurchasedDto(
                        productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getPrice(),
                        1
                ));
            }
        }

        return expandedCart;
    }

    private List<OrderPendingEntity> createPendingOrders(
            List<ProductPurchasedDto> expandedCart,
            UserEntity user,
            FormPayment formPayment,
            String orderHash
    ) {
        List<OrderPendingEntity> orders = new ArrayList<>();

        for (ProductPurchasedDto product : expandedCart) {
            OrderPendingEntity orderPendingEntity = new OrderPendingEntity(
                    user.getId(),
                    product.id(),
                    orderHash,
                    product.price(),
                    LocalDateTime.now(),
                    formPayment,
                    StateStatus.PENDING_PAYMENT
            );

            orders.add(orderPendingEntity);
        }

        return orderPendingRepository.saveAll(orders);
    }

    private Long calculateAmountInCents(List<ProductPurchasedDto> expandedCart) {
        BigDecimal total = expandedCart.stream()
                .map(p -> BigDecimal.valueOf(p.price()))
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        return total
                .multiply(BigDecimal.valueOf(100))
                .setScale(0)
                .longValueExact();
    }

    @Transactional
    public void approveOrdersAfterPayment(String orderHash) {
        List<OrderPendingEntity> orders =
                orderPendingRepository.findByPurchasedHashCode(orderHash);

        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for this order hash.");
        }

        for (OrderPendingEntity order : orders) {
            order.setStatus(StateStatus.PAID);
        }

        orderPendingRepository.saveAll(orders);

        updateStockFromOrders(orders);
    }

    private void updateStockFromOrders(List<OrderPendingEntity> orders) {
        for (OrderPendingEntity order : orders) {
            ProductEntity product = productRepository.findById(order.getPurchasedId())
                    .orElseThrow(() -> new RuntimeException("Product not found while updating stock."));

            int newStock = product.getStock() - 1;

            if (newStock < 0) {
                throw new RuntimeException("Stock cannot become negative.");
            }

            product.setStock(newStock);
            productRepository.save(product);
        }
    }

    @Transactional
    public void failOrdersAfterPayment(String orderHash) {
        List<OrderPendingEntity> orders =
                orderPendingRepository.findByPurchasedHashCode(orderHash);

        for (OrderPendingEntity order : orders) {
            order.setStatus(StateStatus.FAILED);
        }

        orderPendingRepository.saveAll(orders);
    }

    @Transactional
    public List<OrderPendingDto> listAllByUser(Integer userId) {
        return purchasedRepository.findByUserId(userId)
                .stream()
                .map(item -> new OrderPendingDto(
                        item.getId(),
                        item.getUserId(),
                        item.getPurchasedHashCode(),
                        item.getMomentValue(),
                        item.getCreateAt(),
                        item.getFormPayment(),
                        item.getAproved()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<OrderPendingEntity> updateStatus(@Valid Integer id, StateStatus stateStatus) {
        OrderPendingEntity orderPendingEntity = orderPendingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));

        if (stateStatus.equals(orderPendingEntity.getStatus())) {
            throw new RuntimeException("Error");
        }

        orderPendingEntity.setStatus(stateStatus);
        orderPendingRepository.save(orderPendingEntity);

        return ResponseEntity.ok().body(orderPendingEntity);
    }


}