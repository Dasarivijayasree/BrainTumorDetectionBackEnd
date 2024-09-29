package com.example.BrainTumorDetectionBackEnd.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="RegisterDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Register {
    @Id
    private String id;
    @NotEmpty(message="enter first name")
    private String firstName;
    @NotEmpty(message="enter last name")
    private String lastName;
    @NotEmpty(message="enter email")
    private String email;
    @NotEmpty(message="enter username")
    private String userName;
    @NotEmpty(message="enter password")
    private String password;


    public Register(String firstName, String lastName, String email, String userName, String password) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.userName=userName;
        this.password=password;
    }
}
