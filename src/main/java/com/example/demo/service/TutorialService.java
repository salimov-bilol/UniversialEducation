package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Tutorial;
import com.example.demo.repository.TutorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    private final CourseService courseService;

    public TutorialService(TutorialRepository tutorialRepository, CourseService courseService) {
        this.tutorialRepository = tutorialRepository;
        this.courseService = courseService;
    }

    public Tutorial findById(Long id) {
        return this.tutorialRepository.
                findById(id).
                orElseThrow(() -> new NullPointerException("Tutorial with id " + id + " does not exist!"));
    }

    public Long findCourseId(Long id) {
        return this.findById(id).getCourse().getId();
    }

    public void save(Tutorial tutorial) {
        tutorial.setCourse(this.courseService.findById(tutorial.getCourseId()));
        this.tutorialRepository.save(tutorial);
    }

    public void delete(Long id) {
        this.tutorialRepository.deleteById(id);
    }

    public List<Tutorial> findAllByCourseId(Long id) {
        Course course = this.courseService.findById(id);
        return this.tutorialRepository.findAllByCourse(course);
    }
}
