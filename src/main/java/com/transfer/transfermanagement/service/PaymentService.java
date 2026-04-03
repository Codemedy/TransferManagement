package com.transfer.transfermanagement.service;

import com.transfer.transfermanagement.model.Payment;
import com.transfer.transfermanagement.model.PaymentStatus;
import com.transfer.transfermanagement.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;

    public Payment createPayment(Payment payment) {
        payment.setStatus(PaymentStatus.PENDING);
        return repository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + id));
    }

    public Payment confirmPayment(Long id) {
        Payment payment = getPaymentById(id);
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new IllegalStateException("Cannot confirm payment in status: " + payment.getStatus());
        }
        payment.setStatus(PaymentStatus.CONFIRMED);
        return repository.save(payment);
    }

    public Payment cancelPayment(Long id) {
        Payment payment = getPaymentById(id);
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new IllegalStateException("Cannot cancel payment in status: " + payment.getStatus());
        }
        payment.setStatus(PaymentStatus.CANCELED);
        return repository.save(payment);
    }

    public List<Payment> getPaymentsByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }
}