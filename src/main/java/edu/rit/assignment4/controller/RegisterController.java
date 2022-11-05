package edu.rit.assignment4.controller;

import edu.rit.assignment4.bean.RegisterUser;
import edu.rit.assignment4.bean.Student;
import edu.rit.assignment4.bean.User;
import edu.rit.assignment4.service.RegisterService;
import edu.rit.assignment4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    StudentService studentService;

    @Autowired
    RegisterService registerService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Student> listStudent = studentService.listAll();
        List<User> users = registerService.listAll();
        model.addAttribute("listStudent",listStudent);
        model.addAttribute("userList", users);
        return "index";
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

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") RegisterUser user) {
        registerService.updateUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveWithRole", method =RequestMethod.POST)
    public String saveStudentWithRole(@ModelAttribute("student") Student student ) {
        studentService.save(student);
        return "redirect:/";
    }


    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditRegisterPage(@PathVariable(name="id") Long id) {
        ModelAndView mav=new ModelAndView("edit_register");
        User user= registerService.get(id);
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudentPage(@PathVariable (name="id") Long id) {
        registerService.delete(id);
        return "redirect:/";
    }
}
