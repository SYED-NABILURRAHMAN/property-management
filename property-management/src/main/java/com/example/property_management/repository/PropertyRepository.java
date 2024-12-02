package com.example.property_management.repository;

import com.example.property_management.model.Property;
import com.example.property_management.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByClient(Client client);
}
