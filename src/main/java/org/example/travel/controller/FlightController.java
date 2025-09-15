package org.example.travel.controller;

import org.example.travel.model.Flight;
import org.example.travel.repository.FlightRepository;
import org.example.travel.service.TravelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightRepository flightRepository;
    private final TravelService travelService;

    public FlightController(FlightRepository flightRepository, TravelService travelService) {
        this.flightRepository = flightRepository;
        this.travelService = travelService;
    }

    @GetMapping
    public String list(@RequestParam(required=false, defaultValue="") String origin,
                       @RequestParam(required=false, defaultValue="") String destination,
                       Model model) {
        model.addAttribute("flights", travelService.searchFlights(origin, destination));
        return "flights";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model){
        model.addAttribute("flight", travelService.getFlight(id));
        return "flight-details";
    }

    // Admin create endpoints (secured to ADMIN)
    @GetMapping("/admin/create")
    public String createForm(Model m){
        m.addAttribute("flight", new Flight());
        return "flight-form";
    }

    @PostMapping("/admin/create")
    public String create(Flight flight){
        flightRepository.save(flight);
        return "redirect:/flights";
    }
}
