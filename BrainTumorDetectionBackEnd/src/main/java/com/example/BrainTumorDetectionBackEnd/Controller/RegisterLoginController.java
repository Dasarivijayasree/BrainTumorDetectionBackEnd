package com.example.BrainTumorDetectionBackEnd.Controller;

import com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO.LoginDTO;
import com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO.RegisterDTO;
import com.example.BrainTumorDetectionBackEnd.DTO.ResponseDTO.LoginResponseDTO;
import com.example.BrainTumorDetectionBackEnd.Service.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin (origins="*")
@RequestMapping ("/user")
public class RegisterLoginController {
    @Autowired
    private RegisterLoginService registerLoginService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerDetails(@RequestBody RegisterDTO registerDTO){
        registerLoginService.register(registerDTO);
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponseDTO loginDetails(@RequestBody LoginDTO loginDTO){
        return registerLoginService.login(loginDTO);
    }
}
