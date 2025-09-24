package com.desafio.pagamentos.dto;

import com.desafio.pagamentos.domain.Payment;
import com.desafio.pagamentos.domain.PaymentMethod;
import com.desafio.pagamentos.domain.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponseDTO {

    private Long id;
    private Integer debitCode;
    private String payerDocument;
    private PaymentMethod method;
    private String cardNumber;
    private BigDecimal paymentAmount;
    private PaymentStatus status;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDebitCode() {
        return debitCode;
    }

    public void setDebitCode(Integer debitCode) {
        this.debitCode = debitCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public String getPayerDocument() {
        return payerDocument;
    }

    public void setPayerDocument(String payerDocument) {
        this.payerDocument = payerDocument;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PaymentResponseDTO(Payment payment) {
        this.active =payment.getActive();
        this.payerDocument = payment.getPayerDocument();
        this.createdAt= payment.getCreatedAt();
        this.debitCode = payment.getDebitCode();
        this.id = payment.getId();
        this.method = payment.getMethod();
        this.cardNumber = payment.getCardNumber();
        this.paymentAmount = payment.getPaymentAmount();
        this.status = payment.getStatus();
        this.updatedAt = payment.getUpdatedAt();
    }
}
