package com.transfer.transfermanagement.controller;

import com.transfer.transfermanagement.model.Payment;
import com.transfer.transfermanagement.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final PaymentService service;

    @GetMapping("/{clientId}/payments")
    public ResponseEntity<List<Payment>> getClientPayments(@PathVariable String clientId) {
        return ResponseEntity.ok(service.getPaymentsByClientId(clientId));
    }
}