package com.example.property_management.service;

import com.example.property_management.model.Client;
import com.example.property_management.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Method to get a client by its ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with ID " + id + " does not exist"));
    }


    // Method to save a new client
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
}
