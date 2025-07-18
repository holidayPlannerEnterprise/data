package com.arpajit.holidayplanner.data;

import java.util.*;
import java.time.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends JpaRepository<TableHolidays, String> {
    List<TableHolidays> findAll();
    List<TableHolidays> findByHolDtBetween(LocalDate start, LocalDate end);
    boolean existsByHolDt(LocalDate date);
}