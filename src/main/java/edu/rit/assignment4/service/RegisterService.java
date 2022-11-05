package edu.rit.assignment4.service;

import edu.rit.assignment4.bean.*;
import edu.rit.assignment4.repository.StudentRepository;
import edu.rit.assignment4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegisterService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void registerUser(RegisterUser registerUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Student student = new Student();
        student.setName(registerUser.getSname());
        student.setCity(registerUser.getCity());
        studentRepository.save(student);
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(encoder.encode(registerUser.getPassword()));
        Role role = new Role((long) RoleEnum.valueOf(registerUser.getRole()).ordinal()+1, registerUser.getRole());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    public void updateUser(RegisterUser user) {
        User userToUpdate = this.get(user.getId());
        userToUpdate.setUsername(user.getUsername());
        Role role = new Role((long) RoleEnum.valueOf(user.getRole()).ordinal()+1, user.getRole());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        userToUpdate.setRoles(roleSet);
        userRepository.save(userToUpdate);
    }

}
