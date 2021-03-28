package com.example.demo.controller.admin;

import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("adminUserController")
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    private final CourseService courseService;

    public UserController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/student")
    public String student(Model model) {
        model.addAttribute("students", this.userService.findStudents());
        return "students";
    }

    @GetMapping("/studentByCourse")
    public String studentByCourse(@RequestParam Long courseId, Model model) {
        model.addAttribute("students", this.courseService.findByCourse(courseId));
        return "students";
    }

    @GetMapping("/studentProfile")
    public String profile(Model model, @RequestParam Long id, @RequestParam Long courseId) {
        var user = this.userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("courseId", courseId);
        return "profile";
    }

    @GetMapping("/deleteUser")
    public String delete(@RequestParam Long id) {
        this.userService.delete(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/userByUsername")
    public String studentByUsername(Model model, @RequestParam String username) {
        model.addAttribute("users", this.userService.findAllByUsernameLike(username));
        return "users";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "users";
    }
}
