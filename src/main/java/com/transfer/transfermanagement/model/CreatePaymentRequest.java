package com.transfer.transfermanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @Pattern(regexp = "KZT|USD|EUR|RUB", message = "Invalid currency")
        String currency,

        String description,

        @NotBlank(message = "ClientId is required")
        String clientId
) {}
