package com.grupo16.src.controllers;

import com.grupo16.src.models.dtos.LoginDTO;
import com.grupo16.src.models.dtos.MessageDto;
import com.grupo16.src.models.dtos.TokenDTO;
import com.grupo16.src.models.dtos.UserInfo;
import com.grupo16.src.models.entities.User;
import com.grupo16.src.services.UserService;
import com.grupo16.src.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    TokenManager tokenManager;

    @PostMapping("/signup")
    public ResponseEntity<MessageDto> registerUser(@RequestBody @Valid UserInfo userInfo, BindingResult result){
        try{
            if(result.hasErrors()){
                String a;
                for(Object object : result.getAllErrors()){
                    if(object instanceof FieldError) {
                        FieldError fieldError = (FieldError) object;

                        System.out.println(fieldError.getCode());
                    }
                }
                return new ResponseEntity<>(
                        new MessageDto("El patron ingresado en uno de los campos no es el correcto"),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

            User foundUser = userService.findOneByUsernameAndEmail(userInfo.getUsername(),userInfo.getEmail());

            if(foundUser != null){
                return new ResponseEntity<>(
                        new MessageDto("Este usuario ya existe"),
                        HttpStatus.BAD_REQUEST
                );
            }
            userService.register(userInfo);

            return new ResponseEntity<>(
                    new MessageDto("Usuario Registrado"),
                    HttpStatus.CREATED
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                new MessageDto("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/signin")
    private ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginInfo, BindingResult result) {
        try {

            if(result.hasErrors()) {
                return new ResponseEntity<>(
                        new TokenDTO(),
                        HttpStatus.BAD_REQUEST
                );
            }

            User user = userService.findOneByIdentifier(loginInfo.getIdentifier());

            if(!userService.comparePassword(user, loginInfo.getPassword())) {
                return new ResponseEntity<>(
                        new TokenDTO(),
                        HttpStatus.UNAUTHORIZED
                );
            }

            final String token = tokenManager.generateJwtToken(user.getUsername());

            userService.insertToken(user, token);

            return new ResponseEntity<>(
                    new TokenDTO(token),
                    HttpStatus.CREATED
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(
                    new TokenDTO(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
