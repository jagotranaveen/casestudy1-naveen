package com.casestudy1.naveen.theater.repository;

import com.casestudy1.naveen.theater.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieTitleIgnoreCaseAndShowDate(String movieTitle, LocalDate showDate);
    List<Show> findByScreen_Theater_CityIgnoreCaseAndShowDate(String city, LocalDate showDate);
}
