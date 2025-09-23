package com.desafio.pagamentos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public class PaymentRequestDTO {

    @NotNull
    private Integer debitCode;
    @NotBlank
    private String payerDocument;
    @NotNull
    private String method;

    private String cardNumber;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal paymentAmount;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getDebitCode() {
        return debitCode;
    }

    public void setDebitCode(Integer debitCode) {
        this.debitCode = debitCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
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
}
