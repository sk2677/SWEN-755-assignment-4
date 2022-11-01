package edu.rit.assignment4.controller;

import edu.rit.assignment4.bean.RegisterUser;
import edu.rit.assignment4.bean.Student;
import edu.rit.assignment4.bean.User;
import edu.rit.assignment4.service.RegisterService;
import edu.rit.assignment4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    StudentService studentService;

    @Autowired
    RegisterService registerService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Student> listStudent = studentService.listAll();
        model.addAttribute("listStudent",listStudent);
        return "index";
    }

    @RequestMapping("/new")
    public String newStudentPage(Model model) {
        Student student=new Student();
        model.addAttribute(student);
        return "new_student";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        RegisterUser user=new RegisterUser();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") RegisterUser user) {
        System.out.println(user);
        registerService.registerUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student ) {
        studentService.save(student);
        return "redirect:/";
    }


    @RequestMapping(value = "/saveWithRole", method =RequestMethod.POST)
    public String saveStudentWithRole(@ModelAttribute("student") Student student ) {
        studentService.save(student);
        return "redirect:/";
    }
    @RequestMapping("/edit/{sid}")
    public ModelAndView showEditStudentPage(@PathVariable(name="sid") Long sid) {
        ModelAndView mav=new ModelAndView("edit_student");
        Student student= studentService.get(sid);
        mav.addObject("student",student);
        return mav;
    }

    @RequestMapping("/delete/{sid}")
    public String deleteStudentPage(@PathVariable (name="sid") Long sid) {
        studentService.delete(sid);
        return "redirect:/";
    }
}
