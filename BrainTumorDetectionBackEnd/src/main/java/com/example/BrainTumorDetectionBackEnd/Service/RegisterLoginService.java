package com.example.BrainTumorDetectionBackEnd.Service;

import com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO.LoginDTO;
import com.example.BrainTumorDetectionBackEnd.DTO.RequestDTO.RegisterDTO;
import com.example.BrainTumorDetectionBackEnd.DTO.ResponseDTO.LoginResponseDTO;
import com.example.BrainTumorDetectionBackEnd.Model.Login;
import com.example.BrainTumorDetectionBackEnd.Model.Register;
import com.example.BrainTumorDetectionBackEnd.Repository.LoginRepository;
import com.example.BrainTumorDetectionBackEnd.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterLoginService {
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private LoginRepository loginRepository;
    public LoginResponseDTO register(RegisterDTO registerDTO){
        Register reg=new Register(registerDTO.getFisrtName(), registerDTO.getLastName(), registerDTO.getEmail(), registerDTO.getUserName(), registerDTO.getPassword());
        String email=registerDTO.getEmail();
        Integer count=registerRepository.userFound(email);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        if(count==null){
            loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setStatus(100);
            loginResponseDTO.setMessage("Successfully registered");
        }
        else if(count==1){
            loginResponseDTO=new LoginResponseDTO();
            loginResponseDTO.setStatus(404);
            loginResponseDTO.setMessage("Already have an account");
        }
        else {
            loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setStatus(404);
            loginResponseDTO.setMessage("Already have an account");
        }
        return loginResponseDTO;
    }

    public LoginResponseDTO login(LoginDTO loginDTO){
        LoginResponseDTO loginResponseDTO;
        String username=loginDTO.getUserName();
        String password=loginDTO.getPassword();
        Integer bool=registerRepository.findDetails(username, password);
        if(bool==null){
            loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setStatus(404);
            loginResponseDTO.setMessage("Not found");
        }
        else if(bool==1){
            loginResponseDTO=new LoginResponseDTO();
            loginResponseDTO.setStatus(100);
            loginResponseDTO.setMessage("ok");
        }
        else {
            loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setStatus(404);
            loginResponseDTO.setMessage("Not found");
        }
        return loginResponseDTO;
    }



}

