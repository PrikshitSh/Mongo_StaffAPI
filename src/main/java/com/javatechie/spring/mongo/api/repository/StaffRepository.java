package com.javatechie.spring.mongo.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatechie.spring.mongo.api.model.Staff;

public interface StaffRepository extends MongoRepository<Staff, Integer>{

	Optional<Staff> findByEmail(String email);

}



