package com.example.BrainTumorDetectionBackEnd.Repository;

import com.example.BrainTumorDetectionBackEnd.Model.Login;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepository extends MongoRepository <Login, String> {
}
