package com.example.BrainTumorDetectionBackEnd.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="loginDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Id
    private String id;
    @NotEmpty(message="please enter username")
    private String userName;
    @NotEmpty(message="please enter the password")
    private String password;

    public Login(String userName, String password) {
        this.userName=userName;
        this.password=password;

    }
}
