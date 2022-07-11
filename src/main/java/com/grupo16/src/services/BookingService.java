package com.grupo16.src.services;

import com.grupo16.src.models.dtos.BookingDto;
import com.grupo16.src.models.dtos.PageableDTO;
import com.grupo16.src.models.entities.Booking;
import com.grupo16.src.models.entities.Schedule;
import com.grupo16.src.models.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {
    Booking getOneByUserAndSchedule(User user,Schedule schedule);
    void reserve(BookingDto booking) throws Exception;
    void deleteReserve(Long id);
    Page<Booking> getUsersBookings(User user, PageableDTO info);
    List<Booking> getBySchedule(Schedule schedule);
    Booking getOneById(Long id);
}
