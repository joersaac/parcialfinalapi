package com.grupo16.src.controllers;

import com.grupo16.src.models.dtos.BookingDto;
import com.grupo16.src.models.dtos.MessageDto;
import com.grupo16.src.models.dtos.PageableDTO;
import com.grupo16.src.models.dtos.UserInfo;
import com.grupo16.src.models.entities.*;
import com.grupo16.src.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping("/movies")
    public ResponseEntity<Page<Movie>> findAllMovies(PageableDTO info){
        try{
            Page<Movie> movies = movieService.findAll(info);

            return  new ResponseEntity<>(movies,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/{category}")
    public ResponseEntity<Page<Movie>> getMovieByCategory(PageableDTO info,@PathVariable(name="category") String category){
        try{
            Category foundCategory = categoryService.findOneByName(category);
            Page<Movie> foundMovies = movieService.findByCategory(foundCategory,info);

            return  new ResponseEntity<>(foundMovies,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/today")
    public ResponseEntity<Page<Schedule>> getTodaysMovies(PageableDTO info){
        try{
            Page<Schedule> todaysSchedule = scheduleService.getTodaysSchedule(info);
            return  new ResponseEntity<>(todaysSchedule,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<MessageDto> makeAReserve(@RequestBody @Valid BookingDto bookingDto, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(
                        new MessageDto("El patron ingresado en uno de los campos no es el correcto"),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
            User foundUser = userService.findOneByUsernameAndEmail(bookingDto.getUsername(),bookingDto.getUsername());
            Schedule foundSchedule = scheduleService.getOneByIdentifier(bookingDto.getCode());
            if( foundUser == null && foundSchedule == null){
                return new ResponseEntity<>(
                        new MessageDto("El usuario o la funcion no han sido encontrados"),
                        HttpStatus.BAD_REQUEST
                );
            }

            List<Booking> foundBooking = bookingService.getBySchedule(foundSchedule);

            if(foundBooking.size() >= foundSchedule.getCapacity()){
                return new ResponseEntity<>(
                        new MessageDto("La funcion a la que desea realizar una reserva ya se encuentra llena"),
                        HttpStatus.BAD_REQUEST
                );
            }
            bookingService.reserve(bookingDto);


            return new ResponseEntity<>(
                    new MessageDto("Reserva hecha"),
                    HttpStatus.CREATED
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new MessageDto("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/reserve")
    public ResponseEntity<MessageDto> deleteReserve(@RequestBody @Valid BookingDto bookingDto, BindingResult result) {
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(
                        new MessageDto("El patron ingresado en uno de los campos no es el correcto"),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
            User foundUser = userService.findOneByUsernameAndEmail(bookingDto.getUsername(),bookingDto.getUsername());
            Schedule foundSchedule = scheduleService.getOneByIdentifier(bookingDto.getCode());
            if( foundUser == null && foundSchedule == null){
                return new ResponseEntity<>(
                        new MessageDto("El usuario o la funcion no han sido encontrados"),
                        HttpStatus.BAD_REQUEST
                );
            }

            Booking foundBooking = bookingService.getOneByUserAndSchedule(foundUser,foundSchedule);

            if(foundBooking == null){
                return new ResponseEntity<>(
                        new MessageDto("No se encontro la reserva"),
                        HttpStatus.BAD_REQUEST
                );
            }
            bookingService.deleteReserve(foundBooking.getId());

            return new ResponseEntity<>(
                    new MessageDto("Reserva eliminada"),
                    HttpStatus.CREATED
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new MessageDto("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/booking")
    public ResponseEntity<Page<Booking>> findUserBookings(@Valid PageableDTO info, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            User foundUser = userService.findOneByUsernameAndEmail(info.getUsername(),info.getUsername());
            if( foundUser == null ){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Page<Booking> foundBookings = bookingService.getUsersBookings(foundUser,info);


            return new ResponseEntity<>(foundBookings, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
