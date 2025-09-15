package org.example.travel.controller;

import org.example.travel.model.*;
import org.example.travel.repository.*;
import org.example.travel.service.TravelService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final FlightRepository flightRepository;
    private final TrainRepository trainRepository;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TravelService travelService;

    public BookingController(
            FlightRepository flightRepository,
            TrainRepository trainRepository,
            CarRepository carRepository,
            BookingRepository bookingRepository,
            UserRepository userRepository,
            TravelService travelService
    ) {
        this.flightRepository = flightRepository;
        this.trainRepository = trainRepository;
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.travelService = travelService;
    }

    @PostMapping("/flight/{id}")
    public String bookFlight(@PathVariable Long id,
                             @RequestParam Integer seats,
                             @AuthenticationPrincipal UserDetails userDetails,
                             Model model) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found: " + id));
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userDetails.getUsername()));

        if (flight.getSeats() < seats) {
            model.addAttribute("error", "Not enough seats");
            model.addAttribute("flight", flight);
            return "flight-details";
        }

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setUser(user);
        booking.setSeatsBooked(seats);
        booking.setTotalPrice(flight.getPrice() * seats);
        travelService.bookFlight(booking);

        return "redirect:/booking/my";
    }

    @GetMapping("/my")
    public String myBookings(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userDetails.getUsername()));
        model.addAttribute("bookings", bookingRepository.findByUser(user));
        return "bookings";
    }

    // Book a train
    @PostMapping("/train/{id}")
    public String bookTrain(@PathVariable Long id,
                            @RequestParam Integer seats,
                            @AuthenticationPrincipal UserDetails userDetails,
                            Model model) {
        // <-- FIX: use the instance field `trainRepository` (not TrainRepository)
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Train not found: " + id));
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userDetails.getUsername()));

        if (train.getSeats() < seats) {
            model.addAttribute("error", "Not enough seats");
            model.addAttribute("train", train);
            return "train-details";
        }

        Booking booking = new Booking();
        booking.setTrain(train);
        booking.setUser(user);
        booking.setSeatsBooked(seats);
        booking.setTotalPrice(train.getPrice() * seats);
        travelService.bookTrain(booking);

        return "redirect:/booking/my";
    }

    // Book a car
    @PostMapping("/car/{id}")
    public String bookCar(@PathVariable Long id,
                          @RequestParam(required = false, defaultValue = "1") Integer units,
                          @AuthenticationPrincipal UserDetails userDetails,
                          Model model) {
        // <-- FIX: use the instance field `carRepository` (not CarRepository)
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found: " + id));
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userDetails.getUsername()));

        if (car.getAvailableUnits() < units) {
            model.addAttribute("error", "Not enough cars available");
            model.addAttribute("car", car);
            return "car-details";
        }

        Booking booking = new Booking();
        booking.setCar(car);
        booking.setUser(user);
        booking.setUnitsBooked(units);
        booking.setTotalPrice(car.getPricePerKm() * units);
        travelService.bookCar(booking);

        return "redirect:/booking/my";
    }
}
