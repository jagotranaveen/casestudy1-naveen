package com.casestudy1.naveen.booking.service;

import com.casestudy1.naveen.booking.dto.BookingDTO;
import com.casestudy1.naveen.booking.model.Booking;
import com.casestudy1.naveen.booking.repository.BookingRepository;
import com.casestudy1.naveen.booking.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j  // Provides a logger instance named 'log'
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final WebClient.Builder webClientBuilder;

    public Booking createBooking(BookingDTO bookingDTO) {
        log.info("Creating booking for show id {}", bookingDTO.getShowId());
        
        // Example: fetch show details from Theater Service
        Object showDetails = webClientBuilder.build()
            .get()
            .uri("http://theater-service/api/theater/shows/{id}", bookingDTO.getShowId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();

        // Discount logic: 50% off on the third ticket and 20% off for afternoon shows.
        double pricePerTicket = 10.0;
        int ticketCount = bookingDTO.getTicketCount();
        double totalPrice = 0;
        for (int i = 1; i <= ticketCount; i++) {
            if (i == 3) {
                totalPrice += pricePerTicket * 0.5;
            } else {
                totalPrice += pricePerTicket;
            }
        }
        // For afternoon shows, apply an extra 20% discount.
        boolean isAfternoon = true; // Replace with proper check using showDetails
        if (isAfternoon) {
            totalPrice = totalPrice * 0.8;
        }

        Booking booking = Booking.builder()
                .showId(bookingDTO.getShowId())
                .seats(bookingDTO.getSeats())
                .totalPrice(totalPrice)
                .bookingTime(LocalDateTime.now())
                .build();
        return bookingRepository.save(booking);
    }

    public List<Booking> bulkCreateBookings(List<BookingDTO> bookingDTOs) {
        log.info("Creating bulk bookings, count: {}", bookingDTOs.size());
        return bookingDTOs.stream().map(this::createBooking).toList();
    }

    public void cancelBooking(Long bookingId) {
        log.info("Cancelling booking id {}", bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        bookingRepository.delete(booking);
    }
}
