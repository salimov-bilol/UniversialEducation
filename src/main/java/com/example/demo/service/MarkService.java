package com.example.demo.service;

import com.example.demo.entity.Mark;
import com.example.demo.entity.Statistic;
import com.example.demo.entity.User;
import com.example.demo.repository.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkService {

    private final MarkRepository markRepository;

    private final UserService userService;

    private final CourseService courseService;

    public MarkService(MarkRepository markRepository, UserService userService, CourseService courseService) {
        this.markRepository = markRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    public void save(Mark mark) {
        mark.setUser(this.userService.findById(mark.getStudentId()));
        mark.setCourse(this.courseService.findById(mark.getCourseId()));
        this.markRepository.save(mark);
    }

    public List<Mark> findAllByCourseId(Long courseId) {
        return this.markRepository.findAllByCourse(this.courseService.findById(courseId));
    }

    public Statistic findMarkStatistics(Long courseId) {
        return new Statistic(
                this.markRepository.
                        findAllByCourse(this.courseService.findById(courseId)).
                        stream().
                        collect(Collectors.summarizingInt(Mark::getMark))
        );


    }

    public Statistic findMarkStatistics2(User user) {
        return new Statistic(
                this.markRepository.
                        findAllByUser(user).
                        stream().
                        collect(Collectors.summarizingInt(Mark::getMark))
        );


    }


}
