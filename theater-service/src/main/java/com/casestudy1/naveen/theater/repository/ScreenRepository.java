package com.casestudy1.naveen.theater.repository;

import com.casestudy1.naveen.theater.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
