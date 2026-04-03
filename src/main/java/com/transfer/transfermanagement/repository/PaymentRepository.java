package com.transfer.transfermanagement.repository;

import com.transfer.transfermanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByClientId(String clientId);
}
