package com.casestudy1.naveen.booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Generates getters, setters, equals, hashCode, and toString.
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    @NotNull(message = "Show ID is required")
    private Long showId;

    @NotBlank(message = "Seats selection is required (comma-separated)")
    private String seats;

    @NotNull(message = "Ticket count is required")
    @Min(value = 1, message = "At least one ticket must be booked")
    private Integer ticketCount;
}
