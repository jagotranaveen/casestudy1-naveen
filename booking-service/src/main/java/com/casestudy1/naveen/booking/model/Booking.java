package com.casestudy1.naveen.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data  // Generates getters and setters.
@NoArgsConstructor
@AllArgsConstructor
@Builder  // Enables the builder() method.
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Show ID is required")
    private Long showId;

    @NotNull(message = "Seat details are required")
    private String seats;

    @Min(value = 0, message = "Price cannot be negative")
    private Double totalPrice;

    private LocalDateTime bookingTime;
}
