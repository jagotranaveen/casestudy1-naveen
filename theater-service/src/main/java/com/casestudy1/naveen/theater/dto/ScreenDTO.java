package com.casestudy1.naveen.theater.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenDTO {
    private Long id;

    @NotNull(message = "Screen number is required")
    private Integer screenNumber;

    @NotNull(message = "Theater ID is required")
    private Long theaterId;
}
