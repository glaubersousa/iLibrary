package com.isystem.ilibraryapi.service;

import com.isystem.ilibraryapi.exceptions.UserDuplicateException;
import com.isystem.ilibraryapi.model.User;
import com.isystem.ilibraryapi.repository.BookRepository;
import com.isystem.ilibraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("Unable to locate this ID");
    }

    public List<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> getUserByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    public User register(User user) {
        Optional<User> optional = userRepository.findByCpf(user.getCpf());
        Optional<User> optionalEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> optionalPhone = userRepository.findByPhone(user.getPhone());
        if (optional.isEmpty()) {
            if (optionalEmail.isEmpty()) {
                if (optionalPhone.isEmpty()) {
                    return userRepository.save(user);
                }
                throw new UserDuplicateException("There is already a user with this phone " + user.getPhone());
            }
            throw new UserDuplicateException("There is already a user with this e-mail " + user.getEmail());
        }
        throw new UserDuplicateException("There is already a user  with this CPF " + user.getCpf());
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
