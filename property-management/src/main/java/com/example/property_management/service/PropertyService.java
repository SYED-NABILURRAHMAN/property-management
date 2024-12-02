package com.example.property_management.service;

import com.example.property_management.model.Client;
import com.example.property_management.model.Property;
import com.example.property_management.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ClientService clientService;

    @Transactional
    public Property saveProperty(Property property) {
        // Ensure client exists
        if (property.getClient() == null || property.getClient().getId() == null) {
            throw new IllegalArgumentException("Client information is missing");
        }

        Client client = clientService.getClientById(property.getClient().getId());
        if (client == null) {
            throw new IllegalArgumentException("Client not found with ID: " + property.getClient().getId());
        }

        property.setClient(client);

        // Handle default values for nullable fields
        if (property.getAddress() == null || property.getAddress().isEmpty()) {
            property.setAddress("Unknown Address");
        }
        if (property.getType() == null || property.getType().isEmpty()) {
            property.setType("Unknown Type");
        }

        return propertyRepository.save(property);
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Property with ID " + id + " does not exist"));
    }

    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new IllegalArgumentException("Property with ID " + id + " does not exist");
        }
        propertyRepository.deleteById(id);
    }

    public List<Property> getPropertiesByClientId(Long clientId) {
        // Fetch client by ID to ensure it exists
        Client client = clientService.getClientById(clientId);
        return propertyRepository.findByClient(client);
    }
}
