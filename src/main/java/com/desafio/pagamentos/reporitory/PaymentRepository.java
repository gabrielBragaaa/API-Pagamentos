package com.desafio.pagamentos.reporitory;

import com.desafio.pagamentos.domain.Payment;
import com.desafio.pagamentos.domain.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByDebitCode(Integer debitCode);
    List<Payment> findByPayerDocument(String payerDocument);
    List<Payment> findByStatus(PaymentStatus status);




}
