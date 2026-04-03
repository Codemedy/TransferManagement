package com.transfer.transfermanagement.controller;

import com.transfer.transfermanagement.model.CreatePaymentRequest;
import com.transfer.transfermanagement.model.Payment;
import com.transfer.transfermanagement.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Transfer Managing")
public class PaymentController {
    private final PaymentService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreatePaymentRequest request) {
        Payment payment = service.createPayment(Payment.builder()
                .amount(request.amount())
                .currency(request.currency())
                .description(request.description())
                .clientId(request.clientId())
                .build());
        return ResponseEntity.ok(Map.of("paymentId", payment.getId().toString(), "status", payment.getStatus()));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getStatus(@PathVariable Long paymentId) {
        return ResponseEntity.ok(service.getPaymentById(paymentId));
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<?> confirm(@PathVariable Long paymentId) {
        Payment payment = service.confirmPayment(paymentId);
        return ResponseEntity.ok(Map.of("paymentId", payment.getId().toString(), "status", payment.getStatus()));
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long paymentId) {
        Payment payment = service.cancelPayment(paymentId);
        return ResponseEntity.ok(Map.of("paymentId", payment.getId().toString(), "status", payment.getStatus()));
    }
}