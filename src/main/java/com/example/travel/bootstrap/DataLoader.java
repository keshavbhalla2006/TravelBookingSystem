package com.example.travel.bootstrap;

import com.example.travel.model.User;
import com.example.travel.model.Vehicle;
import com.example.travel.model.enums.Role;
import com.example.travel.model.enums.VehicleType;
import com.example.travel.repository.UserRepository;
import com.example.travel.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, VehicleRepository vehicleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Admin user
        userRepository.findByEmail("admin@local").orElseGet(() -> {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@local");
            admin.setRole(Role.ADMIN);
            admin.setPassword(passwordEncoder.encode("admin123"));
            return userRepository.save(admin);
        });

        // Sample vehicles
        if (vehicleRepository.count() == 0) {
            Vehicle v1 = new Vehicle();
            v1.setType(VehicleType.BUS);
            v1.setNumber("BUS-1001");
            v1.setCapacity(40);
            v1.setDescription("City Express");
            vehicleRepository.save(v1);

            Vehicle v2 = new Vehicle();
            v2.setType(VehicleType.TRAIN);
            v2.setNumber("TRAIN-2201");
            v2.setCapacity(600);
            v2.setDescription("InterCity");
            vehicleRepository.save(v2);

            Vehicle v3 = new Vehicle();
            v3.setType(VehicleType.FLIGHT);
            v3.setNumber("FL-9W123");
            v3.setCapacity(180);
            v3.setDescription("Domestic Flight");
            vehicleRepository.save(v3);
        }
    }
}
