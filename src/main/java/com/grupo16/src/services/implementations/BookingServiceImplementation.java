package com.grupo16.src.services.implementations;

import com.grupo16.src.models.dtos.BookingDto;
import com.grupo16.src.models.dtos.PageableDTO;
import com.grupo16.src.models.entities.Booking;
import com.grupo16.src.models.entities.Schedule;
import com.grupo16.src.models.entities.User;
import com.grupo16.src.repositories.BookingRepository;
import com.grupo16.src.services.BookingService;
import com.grupo16.src.services.ScheduleService;
import com.grupo16.src.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImplementation implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public Booking getOneByUserAndSchedule(User user,Schedule schedule) {
        return bookingRepository.findOneByUserAndSchedule(user,schedule);
    }

    @Override
    public void reserve(BookingDto bookingDto) throws Exception {
        Booking booking= new Booking();
        booking.setQuantity(1);
        booking.setTime(new Timestamp(System.currentTimeMillis()));
        booking.setUser(userService.findOneByUsernameAndEmail(bookingDto.getUsername(),bookingDto.getUsername()));
        booking.setSchedule(scheduleService.getOneByIdentifier(bookingDto.getCode()));
        bookingRepository.save(booking);
    }

    @Override
    public void deleteReserve(Long id){
        bookingRepository.deleteById(id);
    }

    @Override
    public Page<Booking> getUsersBookings(User user, PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(), Sort.by("id").descending());
        return bookingRepository.findOneByUser(user,request);
    }

    @Override
    public List<Booking> getBySchedule(Schedule schedule) {
        return bookingRepository.findBySchedule(schedule);
    }
}
