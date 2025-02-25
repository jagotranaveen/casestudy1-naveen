package com.casestudy1.naveen.theater.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterDTO {
    private Long id;
    @NotBlank(message = "Theater name is required")
    private String name;
    @NotBlank(message = "City is required")
    private String city;
    private String address;
}

