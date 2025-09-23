package com.desafio.pagamentos.dto;

import jakarta.validation.constraints.NotBlank;

public class StatusUpdateRequestDTO {

    @NotBlank
    private String newStatus;

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
