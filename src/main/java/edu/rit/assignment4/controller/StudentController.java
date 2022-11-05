package edu.rit.assignment4.controller;

import edu.rit.assignment4.bean.Student;
import edu.rit.assignment4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;


    @RequestMapping("/new")
    public String newStudentPage(Model model) {
        Student student=new Student();
        model.addAttribute(student);
        return "new_student";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student ) {
        studentService.save(student);
        return "redirect:/";
    }


    @RequestMapping("/edit/{studentId}")
    public ModelAndView showEditStudentPage(@PathVariable(name="studentId") Long studentId) {
        ModelAndView mav=new ModelAndView("edit_student");
        Student student= studentService.get(studentId);
        mav.addObject("student",student);
        return mav;
    }

}
