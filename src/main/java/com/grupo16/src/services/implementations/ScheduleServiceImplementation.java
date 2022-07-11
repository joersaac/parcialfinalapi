package com.grupo16.src.services.implementations;

import com.grupo16.src.models.dtos.PageableDTO;
import com.grupo16.src.models.entities.Movie;
import com.grupo16.src.models.entities.Schedule;
import com.grupo16.src.repositories.ScheduleRepository;
import com.grupo16.src.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class ScheduleServiceImplementation implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Page<Schedule> getTodaysSchedule(PageableDTO info) {
        Date dt = new Date();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);  // number of days to add
        dt = c.getTime();  // dt is now the new date
        Timestamp tomorrow = new Timestamp(dt.getTime());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(), Sort.by("id").descending());
        return scheduleRepository.findAllByTimestampBetween(timestamp,tomorrow,request);
    }

    @Override
    public Schedule getOneByIdentifier(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Schedule> getMovieSchedule(Movie movie, PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(), Sort.by("id").descending());
        return scheduleRepository.findByMovie(movie,request);
    }
}
