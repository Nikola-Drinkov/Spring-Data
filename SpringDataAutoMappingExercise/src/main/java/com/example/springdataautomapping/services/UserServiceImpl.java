package com.example.springdataautomapping.services;

import com.example.springdataautomapping.domain.entities.User;
import com.example.springdataautomapping.domain.models.UserLoginDTO;
import com.example.springdataautomapping.domain.models.UserRegisterDTO;
import com.example.springdataautomapping.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.springdataautomapping.constants.ErrorMessage.*;
import static com.example.springdataautomapping.constants.Messages.*;

@Service
public class UserServiceImpl implements UserService {
    private User loggedInUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(String[] args) {
       final String email = args[1];
       final String password = args[2];
       final String confirmPassword = args[3];
       final String fullName = args[4];

       UserRegisterDTO userRegisterDTO;
       try {
          userRegisterDTO =  new UserRegisterDTO(email,password,confirmPassword,fullName);
       }catch (IllegalStateException e){
           return e.getMessage();
       }

        if(this.userRepository.findFirstByEmail(userRegisterDTO.getEmail()).isPresent()){
            return EMAIL_PRESENT;
        }

        User userToRegister = this.modelMapper.map(userRegisterDTO, User.class);
        if(this.userRepository.count()==0){
            userToRegister.setIsAdmin(true);
        }
        else{
            userToRegister.setIsAdmin(false);
        }

        this.userRepository.saveAndFlush(userToRegister);
        return String.format(SUCCESSFUL_REGISTER,userToRegister.getFullName());
    }

    @Override
    public String loginUser(String[] args) {
        if(loggedInUser!=null) return USER_ALREADY_LOGGED;

        final String email = args[1];
        final String password = args[2];

        Optional<User> userToLogIn = this.userRepository.findFirstByEmail(email);
        if(userToLogIn.isEmpty()){
            return INCORRECT_EMAIL;
        }

        UserLoginDTO userLoginDTO = new UserLoginDTO(email,password);
        try {
            userLoginDTO.validate(userToLogIn.get().getPassword());
        }catch (IllegalStateException e){
            return e.getMessage();
        }

        this.loggedInUser = userToLogIn.get();
        return String.format(SUCCESSFUL_LOGIN,loggedInUser.getFullName());
    }

    @Override
    public String logout() {
        if(this.loggedInUser==null){
            return NO_USER_TO_LOG_OUT;
        }
        else {
            String nameToLogout = this.loggedInUser.getFullName();
            this.loggedInUser = null;
            return String.format(SUCCESSFUL_LOGOUT,nameToLogout);
        }

    }

    @Override
    public Boolean isLoggedAdmin() {
        return this.loggedInUser!=null && loggedInUser.getIsAdmin();
    }
}
