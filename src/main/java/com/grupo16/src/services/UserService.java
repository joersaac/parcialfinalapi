package com.grupo16.src.services;

import com.grupo16.src.models.dtos.UserInfo;
import com.grupo16.src.models.entities.User;

import java.util.List;

public interface UserService {
    void register(UserInfo userInfo) throws Exception;
    User findOneById(Long id) throws Exception;
    User findOneByIdentifier(String identifier) throws Exception;
    List<User> findAll() throws Exception;
    Boolean comparePassword(User user,String passToCompare) throws Exception;
    User findOneByUsernameAndEmail(String username,String email) throws Exception;
    void insertToken(User user, String token) throws  Exception;
    Boolean isTokenValid(User user, String token) throws Exception;
}
