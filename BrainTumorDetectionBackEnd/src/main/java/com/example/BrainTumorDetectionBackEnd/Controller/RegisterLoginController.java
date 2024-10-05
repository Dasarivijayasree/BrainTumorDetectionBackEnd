package com.example.BrainTumorDetectionBackEnd.Controller;

import com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO.LoginDTO;
import com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO.RegisterDTO;
import com.example.BrainTumorDetectionBackEnd.DTO.ResponseDTO.LoginResponseDTO;
import com.example.BrainTumorDetectionBackEnd.Service.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin (origins="*")
@RequestMapping ("/user")
public class RegisterLoginController {
    @Autowired
    private RegisterLoginService registerLoginService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponseDTO registerDetails(@RequestBody RegisterDTO registerDTO){
        return registerLoginService.register(registerDTO);
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponseDTO loginDetails(@RequestBody LoginDTO loginDTO){
        return registerLoginService.login(loginDTO);
    }

    @PostMapping("/predict")
    public ResponseEntity<String> predictTumor(@RequestParam("file")MultipartFile file){
        try{
            String prediction = registerLoginService.predictTumor(file);
            return ResponseEntity.ok(prediction);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
    }
}
