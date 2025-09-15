package org.example.travel.repository;

import org.example.travel.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCase(String origin, String destination);
}
