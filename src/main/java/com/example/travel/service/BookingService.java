package com.example.travel.service;

import com.example.travel.model.Booking;
import com.example.travel.model.User;
import com.example.travel.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll() { return bookingRepository.findAll(); }
    public List<Booking> findByUser(User user) { return bookingRepository.findByUser(user); }
    public Optional<Booking> findById(Long id) { return bookingRepository.findById(id); }
    public Booking save(Booking b) { return bookingRepository.save(b); }
    public void deleteById(Long id) { bookingRepository.deleteById(id); }
}
