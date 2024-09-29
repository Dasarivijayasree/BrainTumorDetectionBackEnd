package com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    @NotEmpty(message="enter username")
    private String username;
    @NotEmpty(message="enter password")
    private String password;

    public String getUserName() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
