package com.casestudy1.naveen.theater.repository;

import com.casestudy1.naveen.theater.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByCityIgnoreCase(String city);
}
