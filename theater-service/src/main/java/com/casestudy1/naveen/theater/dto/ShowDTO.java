package com.casestudy1.naveen.theater.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowDTO {
    private Long id;
    @NotBlank(message = "Movie title is required")
    private String movieTitle;
    @NotNull(message = "Show date is required")
    @FutureOrPresent(message = "Show date cannot be in the past")
    private LocalDate showDate;
    @NotNull(message = "Show time is required")
    private LocalTime showTime;
    @NotNull(message = "Seat inventory is required")
    @Min(value = 1, message = "Seat inventory must be at least 1")
    private Integer seatInventory;
    @NotNull(message = "Screen id is required")
    private Long screenId;
}
