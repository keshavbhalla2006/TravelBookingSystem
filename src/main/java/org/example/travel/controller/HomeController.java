package org.example.travel.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.travel.repository.FlightRepository;
import org.example.travel.repository.TrainRepository;
import org.example.travel.repository.CarRepository;

@Controller
public class HomeController {

    private final FlightRepository flightRepository;
    private final TrainRepository trainRepository;
    private final CarRepository carRepository;

    public HomeController(FlightRepository flightRepository,
                          TrainRepository trainRepository,
                          CarRepository carRepository) {
        this.flightRepository = flightRepository;
        this.trainRepository = trainRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("flights", flightRepository.findAll());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("flights", flightRepository.findAll());
        model.addAttribute("trains", trainRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());
        return "dashboard";
    }

}
