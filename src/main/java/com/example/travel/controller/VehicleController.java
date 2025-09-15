package com.example.travel.controller;

import com.example.travel.model.Vehicle;
import com.example.travel.model.enums.VehicleType;
import com.example.travel.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        return "vehicles/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("types", VehicleType.values());
        return "vehicles/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleService.findById(id).orElseThrow());
        model.addAttribute("types", VehicleType.values());
        return "vehicles/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", VehicleType.values());
            return "vehicles/form";
        }
        vehicleService.save(vehicle);
        return "redirect:/vehicles";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        vehicleService.deleteById(id);
        return "redirect:/vehicles";
    }
}
