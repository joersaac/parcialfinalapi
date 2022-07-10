package com.grupo16.src.services;

import com.grupo16.src.models.dtos.PageableDTO;
import com.grupo16.src.models.entities.Schedule;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleService {
    Page<Schedule> getTodaysSchedule(PageableDTO info);
    Schedule getOneByIdentifier(Long id);
}
