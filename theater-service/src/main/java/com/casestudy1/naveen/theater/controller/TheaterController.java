package com.casestudy1.naveen.theater.controller;

import com.casestudy1.naveen.theater.dto.ShowDTO;
import com.casestudy1.naveen.theater.model.Show;
import com.casestudy1.naveen.theater.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/theater")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @Operation(summary = "Get shows by movie and date")
    @GetMapping("/shows/movie/{movieTitle}")
    public ResponseEntity<List<Show>> getShowsByMovie(
            @PathVariable String movieTitle,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Show> shows = theaterService.getShowsByMovieAndDate(movieTitle, date);
        return ResponseEntity.ok(shows);
    }

    @Operation(summary = "Get shows by city and date")
    @GetMapping("/shows/city/{city}")
    public ResponseEntity<List<Show>> getShowsByCity(
            @PathVariable String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Show> shows = theaterService.getShowsByCityAndDate(city, date);
        return ResponseEntity.ok(shows);
    }

    @Operation(summary = "Create a new show")
    @PostMapping("/shows")
    public ResponseEntity<Show> createShow(@Valid @RequestBody ShowDTO showDTO) {
        Show created = theaterService.createShow(showDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing show")
    @PutMapping("/shows/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @Valid @RequestBody ShowDTO showDTO) {
        Show updated = theaterService.updateShow(id, showDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a show")
    @DeleteMapping("/shows/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        theaterService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}

