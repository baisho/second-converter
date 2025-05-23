package com.webstardemo.second_converter.service;

import com.webstardemo.second_converter.model.AppUser;
import com.webstardemo.second_converter.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser findUserById(long id) {
        return userRepository.findById(id);
    }

    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

}
