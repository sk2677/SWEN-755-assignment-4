package edu.rit.assignment4.service;

import edu.rit.assignment4.bean.*;
import edu.rit.assignment4.repository.StudentRepository;
import edu.rit.assignment4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    public void registerUser(RegisterUser registerUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Student student = new Student();
        student.setName(registerUser.getSname());
        student.setCity(registerUser.getCity());
        studentRepository.save(student);
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(encoder.encode(registerUser.getPassword()));
        User u = userRepository.save(user);

//        u.getRoles().add(role);
        userRepository.save(u);
    }

}
