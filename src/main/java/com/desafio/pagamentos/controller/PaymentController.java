package com.desafio.pagamentos.controller;

import com.desafio.pagamentos.domain.Payment;
import com.desafio.pagamentos.domain.PaymentMethod;
import com.desafio.pagamentos.domain.PaymentStatus;
import com.desafio.pagamentos.dto.PaymentRequestDTO;
import com.desafio.pagamentos.dto.PaymentResponseDTO;
import com.desafio.pagamentos.dto.StatusUpdateRequestDTO;
import com.desafio.pagamentos.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO req) {
        Payment p = new Payment();
        p.setDebitCode(req.getDebitCode());
        p.setPayerDocument(req.getPayerDocument());
        try{
            p.setMethod(PaymentMethod.valueOf(req.getMethod().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment method: " + req.getMethod());
        }

        p.setCardNumber(req.getCardNumber());
        p.setPaymentAmount(req.getPaymentAmount());

        Payment saved = service.createPayment(p);
        PaymentResponseDTO response = new PaymentResponseDTO(saved);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updateStatus(@PathVariable Long id,
    @Valid @RequestBody StatusUpdateRequestDTO req){
        PaymentStatus newStatus = PaymentStatus.valueOf(req.getNewStatus().toUpperCase());
        Payment updated = service.updateStatus(id,newStatus);
        PaymentResponseDTO responseDTO = new PaymentResponseDTO(updated);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> list(
            @RequestParam(required = false) Integer debitCode,
            @RequestParam(required = false) String payerDocument,
            @RequestParam(required = false) String status
    ){
        if (debitCode != null){
            return ResponseEntity.ok(service.filterByDebitCode(debitCode));
        } else if (payerDocument != null) {
            return ResponseEntity.ok(service.filterByPayerDocument(payerDocument));
        } else if (status != null) {
            PaymentStatus s = PaymentStatus.valueOf(status.toUpperCase());
            return ResponseEntity.ok(service.filterByStatus(s));
        }else {
            return ResponseEntity.ok(service.listAll());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> softDelete(@PathVariable Long id){
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

}
