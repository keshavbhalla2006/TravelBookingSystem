package org.example.travel.repository;

import org.example.travel.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByPickupLocationContainingIgnoreCaseAndDropLocationContainingIgnoreCase(String pickup, String drop);
}
