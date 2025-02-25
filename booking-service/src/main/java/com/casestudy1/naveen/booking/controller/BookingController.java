package com.casestudy1.naveen.booking.controller;

import com.casestudy1.naveen.booking.dto.BookingDTO;
import com.casestudy1.naveen.booking.model.Booking;
import com.casestudy1.naveen.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    
   
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Book movie tickets")
    @PostMapping
    public ResponseEntity<Booking> bookTickets(@Valid @RequestBody BookingDTO bookingDTO) {
        Booking booking = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @Operation(summary = "Bulk booking")
    @PostMapping("/bulk")
    public ResponseEntity<List<Booking>> bulkBookTickets(@Valid @RequestBody List<BookingDTO> bookingDTOs) {
        List<Booking> bookings = bookingService.bulkCreateBookings(bookingDTOs);
        return new ResponseEntity<>(bookings, HttpStatus.CREATED);
    }

    @Operation(summary = "Cancel a booking")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}
