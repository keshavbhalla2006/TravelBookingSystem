package org.example.travel.controller;

import org.example.travel.model.Train;
import org.example.travel.repository.TrainRepository;
import org.example.travel.service.TravelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trains")
public class TrainController {
    private final TrainRepository trainRepository;
    private final TravelService travelService;

    public TrainController(TrainRepository trainRepository, TravelService travelService) {
        this.trainRepository = trainRepository;
        this.travelService = travelService;
    }

    @GetMapping
    public String list(@RequestParam(required=false, defaultValue="") String origin,
                       @RequestParam(required=false, defaultValue="") String destination,
                       Model model) {
        model.addAttribute("trains", travelService.searchTrains(origin, destination));
        return "trains";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model){
        model.addAttribute("train", travelService.getTrain(id));
        return "train-details";
    }

    // admin create endpoints (optional)
    @GetMapping("/admin/create")
    public String createForm(Model m){
        m.addAttribute("train", new Train());
        return "train-form";
    }

    @PostMapping("/admin/create")
    public String create(Train train){
        trainRepository.save(train);
        return "redirect:/trains";
    }
}
