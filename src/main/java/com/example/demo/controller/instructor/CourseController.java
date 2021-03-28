package com.example.demo.controller.instructor;

import com.example.demo.entity.User;
import com.example.demo.service.CourseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("instructorCourseController")
@RequestMapping("/instructor")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course")
    public String course(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("courses", this.courseService.findByInstructor(user));
        return "course";
    }

}
