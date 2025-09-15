package org.example.travel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne(optional = true)
    private Flight flight;

    @ManyToOne(optional = true)
    private Train train;

    @ManyToOne(optional = true)
    private Car car;

    private LocalDateTime bookingTime;
    private Integer seatsBooked;    // for flights/trains
    private Integer unitsBooked;    // for cars
    private Double totalPrice;
    private String status;          // BOOKED, CANCELLED
    private String bookingType;     // FLIGHT, TRAIN, CAR

    public Booking() {}

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public Integer getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(Integer seatsBooked) { this.seatsBooked = seatsBooked; }

    public Integer getUnitsBooked() { return unitsBooked; }
    public void setUnitsBooked(Integer unitsBooked) { this.unitsBooked = unitsBooked; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBookingType() { return bookingType; }
    public void setBookingType(String bookingType) { this.bookingType = bookingType; }
}
