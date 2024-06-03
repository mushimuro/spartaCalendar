package com.sparta.calendar.repository;

import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Optional<Calendar> findByUsername(String username);
}
