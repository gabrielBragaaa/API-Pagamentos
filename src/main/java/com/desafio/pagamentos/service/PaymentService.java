package com.desafio.pagamentos.service;

import com.desafio.pagamentos.domain.Payment;
import com.desafio.pagamentos.domain.PaymentMethod;
import com.desafio.pagamentos.domain.PaymentStatus;
import com.desafio.pagamentos.reporitory.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }
    public Optional<Payment> findById(Long id) {
        return repository.findById(id);
    }

    public List<Payment> listAll() {
        return repository.findAll();
    }

    public List<Payment> filterByDebitCode(Integer code) {
        return repository.findByDebitCode(code);
    }

    public List<Payment> filterByPayerDocument(String doc) {
        return repository.findByPayerDocument(doc);
    }

    public List<Payment> filterByStatus(PaymentStatus status) {
        return repository.findByStatus(status);
    }

    @Transactional
    public Payment createPayment(Payment p) {
        if ((p.getMethod() == PaymentMethod.CARTAO_CREDITO || p.getMethod() == PaymentMethod.CARTAO_DEBITO)
                && (p.getCardNumber() == null || p.getCardNumber().isBlank())) {
            throw new IllegalArgumentException("Número do cartão é obrigatório para efetuar o pagamento");
        }if (p.getMethod() == PaymentMethod.PIX && p.getCardNumber() != null){
            throw new IllegalArgumentException("Não é permitido enviar número do cartão para pagamento via PIX");
        }
        p.setStatus(PaymentStatus.PENDENTE);
        p.setActive(true);
        return repository.save(p);
    }

    @Transactional
    public Payment updateStatus(Long id, PaymentStatus newStatus) {
        Payment p = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));

        if (p.getStatus() == PaymentStatus.PENDENTE) {
            if (newStatus == PaymentStatus.PROCESSADO_SUCESSO || newStatus == PaymentStatus.PROCESSADO_FALHA) {
                p.setStatus(newStatus);
                p.setUpdatedAt(java.time.LocalDateTime.now());
                return repository.save(p);
            } else {
                throw new IllegalArgumentException("Transação invalida de PROCESSADO_PENDENTE para " + newStatus);
            }
        } else if (p.getStatus() == PaymentStatus.PROCESSADO_SUCESSO) {
            throw new IllegalArgumentException("Não é possível alterar o status quando já está PROCESSADO_SUCESSO");
        } else if (p.getStatus() == PaymentStatus.PROCESSADO_FALHA) {
            if (newStatus == PaymentStatus.PENDENTE) {
                p.setStatus(newStatus);
                p.setUpdatedAt(java.time.LocalDateTime.now());
                return repository.save(p);
            } else {
                throw new IllegalArgumentException("Transação invalida de PROCESSADO_FALHA para " + newStatus);
            }
        } else {
            throw new IllegalStateException("Status desconhecido");
        }
    }

    @Transactional
    public void softDelete(Long id) {
        Payment p = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
        if (p.getStatus() != PaymentStatus.PENDENTE) {
            throw new IllegalArgumentException("So é possivel deletar pagementos com status PENDENTE");
        }
        p.setActive(false);
        p.setUpdatedAt(java.time.LocalDateTime.now());
        repository.save(p);
    }

}
