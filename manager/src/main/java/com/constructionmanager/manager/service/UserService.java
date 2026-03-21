package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.User;
import com.constructionmanager.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    // TODO: Need to make sure all actions being performed by user to himself and no other users


    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getCurrentUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with this ID"));
    }

    public boolean userExist(Integer id) {
        if (userRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    public boolean verifyUserLogin(String login) {
        Optional<User> existUserEmail = userRepository.findByEmail(login);
        Optional<User> existUserPhoneNumber = userRepository.findByPhoneNumber(login);

        if ((existUserEmail.isPresent() && !existUserEmail.isEmpty())
                || (existUserPhoneNumber.isPresent() && !existUserPhoneNumber.isEmpty())) {
            return true;
        }
        return false;
    }

    public User login(String login, String password) {
        Optional<User> existUserEmail = userRepository.findByEmail(login);
        Optional<User> existUserPhoneNumber = userRepository.findByPhoneNumber(login);

        if (existUserEmail.isPresent() && !existUserEmail.isEmpty()) {
            if (existUserEmail.get().getPassword().equals(password.toCharArray())) {
                return existUserEmail
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "This user does not exist!"));
            }

            if (existUserPhoneNumber.isPresent() && !existUserPhoneNumber.isEmpty()) {
                if (existUserPhoneNumber.get().getPassword().equals(password.toCharArray())){
                    return existUserPhoneNumber
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "This user does not exist!"));
                }
            }
        }

        return null;
    }

    public User updateUser(Integer id, User newUserDetail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with this id"));

        user.setEmail(newUserDetail.getEmail());
        user.setPhoneNumber(newUserDetail.getPhoneNumber());
        user.setPassword(newUserDetail.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist!");
        }
        userRepository.deleteById(id);
    }
}
