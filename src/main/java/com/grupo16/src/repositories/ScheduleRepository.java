package com.grupo16.src.repositories;

import com.grupo16.src.models.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;

public interface ScheduleRepository extends PagingAndSortingRepository<Schedule,Long> {
    Page<Schedule> findAllByTimestampBetween(Timestamp todayZeroHour, Timestamp tomorrowZeroHour, Pageable pageable);
}
