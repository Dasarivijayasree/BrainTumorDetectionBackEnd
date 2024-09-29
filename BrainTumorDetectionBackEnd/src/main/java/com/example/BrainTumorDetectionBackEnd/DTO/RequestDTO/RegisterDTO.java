package com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    @NotEmpty(message="enter username")
    private String username;
    @NotEmpty(message="enter password")
    private String password;

    public String getFisrtName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return username;
    }
    public String getPassword() {
        return password;
    }


}
