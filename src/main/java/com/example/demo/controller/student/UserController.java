package com.example.demo.controller.student;

import com.example.demo.entity.User;
import com.example.demo.service.MarkService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("studentUserController")
@RequestMapping("/student")
public class UserController {

    private final MarkService markService;

    public UserController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("statistic", this.markService.findMarkStatistics2(user));
        return "profile";
    }
}
