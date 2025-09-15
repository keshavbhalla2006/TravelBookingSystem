package org.example.travel.service;

import org.example.travel.model.*;
import org.example.travel.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TravelService {
    private final FlightRepository flightRepository;
    private final TrainRepository trainRepository;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    public TravelService(FlightRepository flightRepository,
                         TrainRepository trainRepository,
                         CarRepository carRepository,
                         BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.trainRepository = trainRepository;
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    // Flights (existing)
    public List<Flight> searchFlights(String origin, String destination) {
        return flightRepository.findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCase(origin, destination);
    }
    public Flight getFlight(Long id) { return flightRepository.findById(id).orElseThrow(); }
    public Booking bookFlight(Booking booking) {
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("BOOKED");
        booking.setBookingType("FLIGHT");
        Flight f = booking.getFlight();
        f.setSeats(f.getSeats() - booking.getSeatsBooked());
        flightRepository.save(f);
        return bookingRepository.save(booking);
    }

    // Trains
    public List<Train> searchTrains(String origin, String destination) {
        return trainRepository.findByOriginContainingIgnoreCaseAndDestinationContainingIgnoreCase(origin, destination);
    }
    public Train getTrain(Long id) { return trainRepository.findById(id).orElseThrow(); }
    public Booking bookTrain(Booking booking) {
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("BOOKED");
        booking.setBookingType("TRAIN");
        Train t = booking.getTrain();
        t.setSeats(t.getSeats() - booking.getSeatsBooked());
        trainRepository.save(t);
        return bookingRepository.save(booking);
    }

    // Cars
    public List<Car> searchCars(String pickup, String drop) {
        return carRepository.findByPickupLocationContainingIgnoreCaseAndDropLocationContainingIgnoreCase(pickup, drop);
    }
    public Car getCar(Long id) { return carRepository.findById(id).orElseThrow(); }
    public Booking bookCar(Booking booking) {
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("BOOKED");
        booking.setBookingType("CAR");
        Car c = booking.getCar();
        // reduce available units
        if (booking.getUnitsBooked() == null) booking.setUnitsBooked(1);
        c.setAvailableUnits(c.getAvailableUnits() - booking.getUnitsBooked());
        carRepository.save(c);
        return bookingRepository.save(booking);
    }
}
