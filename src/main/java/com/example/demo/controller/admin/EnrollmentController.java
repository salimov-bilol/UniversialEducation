package com.example.demo.controller.admin;

import com.example.demo.entity.Enrollment;
import com.example.demo.exception.StudentAlreadyEnrolledException;
import com.example.demo.service.CourseService;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class EnrollmentController {

    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final UserService userService;

    public EnrollmentController(CourseService courseService, EnrollmentService enrollmentService, UserService userService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.userService = userService;
    }

    @GetMapping("/enrollment")
    public String enrollment(Model model) {
        model.addAttribute("courses", this.courseService.findAll());
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("users", this.userService.findStudents());
        return "enrollment";
    }

    @PostMapping("/enrollment")
    public String enrollment(Enrollment enrollment) throws StudentAlreadyEnrolledException {
        this.enrollmentService.save(enrollment);
        return "redirect:/index";
    }
}
