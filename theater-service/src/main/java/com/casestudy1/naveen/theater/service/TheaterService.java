package com.casestudy1.naveen.theater.service;


import com.casestudy1.naveen.theater.dto.ShowDTO;
import com.casestudy1.naveen.theater.model.Show;
import com.casestudy1.naveen.theater.repository.ShowRepository;
import com.casestudy1.naveen.theater.repository.ScreenRepository;
import com.casestudy1.naveen.theater.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TheaterService {

    private final ShowRepository showRepository;
    private final ScreenRepository screenRepository;

    // Read scenario: Browse shows by movie and date
    public List<Show> getShowsByMovieAndDate(String movieTitle, LocalDate date) {
        log.info("Fetching shows for movie {} on {}", movieTitle, date);
        return showRepository.findByMovieTitleIgnoreCaseAndShowDate(movieTitle, date);
    }

    // Read scenario: Browse shows by city and date
    public List<Show> getShowsByCityAndDate(String city, LocalDate date) {
        log.info("Fetching shows in city {} on {}", city, date);
        return showRepository.findByScreen_Theater_CityIgnoreCaseAndShowDate(city, date);
    }

    // Write scenario: Create a new show
    public Show createShow(ShowDTO showDTO) {
        log.info("Creating show for movie {} on {}", showDTO.getMovieTitle(), showDTO.getShowDate());
        var screen = screenRepository.findById(showDTO.getScreenId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found with id: " + showDTO.getScreenId()));
        Show show = Show.builder()
                .movieTitle(showDTO.getMovieTitle())
                .showDate(showDTO.getShowDate())
                .showTime(showDTO.getShowTime())
                .seatInventory(showDTO.getSeatInventory())
                .screen(screen)
                .build();
        return showRepository.save(show);
    }

    // Write scenario: Update an existing show
    public Show updateShow(Long id, ShowDTO showDTO) {
        log.info("Updating show id {}", id);
        Show existing = showRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + id));
        existing.setMovieTitle(showDTO.getMovieTitle());
        existing.setShowDate(showDTO.getShowDate());
        existing.setShowTime(showDTO.getShowTime());
        existing.setSeatInventory(showDTO.getSeatInventory());
        // Optionally, update screen if provided...
        return showRepository.save(existing);
    }

    // Write scenario: Delete a show
    public void deleteShow(Long id) {
        log.info("Deleting show id {}", id);
        Show existing = showRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + id));
        showRepository.delete(existing);
    }
}

