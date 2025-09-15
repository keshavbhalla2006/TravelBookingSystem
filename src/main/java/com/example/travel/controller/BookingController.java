package com.example.travel.controller;

import com.example.travel.model.Booking;
import com.example.travel.model.User;
import com.example.travel.model.enums.BookingStatus;
import com.example.travel.service.BookingService;
import com.example.travel.service.UserService;
import com.example.travel.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final VehicleService vehicleService;
    private final UserService userService;

    public BookingController(BookingService bookingService, VehicleService vehicleService, UserService userService) {
        this.bookingService = bookingService;
        this.vehicleService = vehicleService;
        this.userService = userService;
    }

    @GetMapping
    public String list(Authentication auth, Model model) {
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("bookings", bookingService.findAll());
        } else {
            User user = userService.findByEmail(auth.getName()).orElseThrow();
            model.addAttribute("bookings", bookingService.findByUser(user));
        }
        return "bookings/list";
    }

    @GetMapping("/create")
    public String createForm(Authentication auth, Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("statuses", BookingStatus.values());
        return "bookings/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Authentication auth, Model model) {
        Booking booking = bookingService.findById(id).orElseThrow();
        model.addAttribute("booking", booking);
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("statuses", BookingStatus.values());
        return "bookings/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("booking") Booking booking, BindingResult result, Authentication auth, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.findAll());
            model.addAttribute("statuses", BookingStatus.values());
            return "bookings/form";
        }
        // assign current user
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        booking.setUser(user);
        bookingService.save(booking);
        return "redirect:/bookings";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        bookingService.deleteById(id);
        return "redirect:/bookings";
    }
}
