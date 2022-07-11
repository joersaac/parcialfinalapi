package com.grupo16.src.repositories;

import com.grupo16.src.models.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
    Booking findOneByUserAndSchedule(User user,Schedule schedule);
    List<Booking> findBySchedule(Schedule schedule);
    Page<Booking> findOneByUser(User user, Pageable pageable);
    Booking findOneById(Long id);
}
