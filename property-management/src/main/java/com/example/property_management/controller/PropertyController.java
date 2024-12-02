package com.example.property_management.controller;

import com.example.property_management.model.Property;
import com.example.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Add a new property
    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        if (property.getClient() == null || property.getClient().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(savedProperty);
    }

    // Get property details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    // Delete a property by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok("Property with ID " + id + " has been deleted.");
    }

    // Get all properties by client ID
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Property>> getPropertiesByClientId(@PathVariable Long clientId) {
        List<Property> properties = propertyService.getPropertiesByClientId(clientId);
        return ResponseEntity.ok(properties);
    }
}
