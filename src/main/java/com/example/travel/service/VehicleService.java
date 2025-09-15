package com.example.travel.service;

import com.example.travel.model.Vehicle;
import com.example.travel.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll() { return vehicleRepository.findAll(); }
    public Optional<Vehicle> findById(Long id) { return vehicleRepository.findById(id); }
    public Vehicle save(Vehicle v) { return vehicleRepository.save(v); }
    public void deleteById(Long id) { vehicleRepository.deleteById(id); }
}
