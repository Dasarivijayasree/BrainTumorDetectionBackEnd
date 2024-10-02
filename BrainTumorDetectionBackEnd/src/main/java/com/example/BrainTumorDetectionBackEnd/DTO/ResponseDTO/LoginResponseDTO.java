package com.example.BrainTumorDetectionBackEnd.DTO.ResponseDTO;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Integer status;
    private String message;

    public void setStatus(Integer status) {
        this.status=status;
    }

    public void setMessage(String message) {
        this.message=message;

    }
}
