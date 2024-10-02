package com.example.BrainTumorDetectionBackEnd.Repository;

import com.example.BrainTumorDetectionBackEnd.Model.Register;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterRepository extends MongoRepository<Register, String> {

    @Aggregation(pipeline = {
            "{ $match: { 'userName': ?0, 'password': ?1 } }",
            "{ $count: 'matchingDocuments' }"
    })
    Integer findDetails(String username, String password);
}
