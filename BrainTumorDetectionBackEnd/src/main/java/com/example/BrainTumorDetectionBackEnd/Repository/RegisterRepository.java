package com.example.BrainTumorDetectionBackEnd.Repository;

import com.example.BrainTumorDetectionBackEnd.Model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterRepository extends MongoRepository <Register, String> {
}
