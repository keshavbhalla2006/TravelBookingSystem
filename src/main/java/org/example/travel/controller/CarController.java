package org.example.travel.controller;

import org.example.travel.model.Car;
import org.example.travel.repository.CarRepository;
import org.example.travel.service.TravelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarRepository carRepository;
    private final TravelService travelService;

    public CarController(CarRepository carRepository, TravelService travelService) {
        this.carRepository = carRepository;
        this.travelService = travelService;
    }

    @GetMapping
    public String list(@RequestParam(required=false, defaultValue="") String pickup,
                       @RequestParam(required=false, defaultValue="") String drop,
                       Model model) {
        model.addAttribute("cars", travelService.searchCars(pickup, drop));
        return "cars";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model){
        model.addAttribute("car", travelService.getCar(id));
        return "car-details";
    }

    @GetMapping("/admin/create")
    public String createForm(Model m){
        m.addAttribute("car", new Car());
        return "car-form";
    }

    @PostMapping("/admin/create")
    public String create(Car car){
        carRepository.save(car);
        return "redirect:/cars";
    }
}
