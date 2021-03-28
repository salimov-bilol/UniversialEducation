package com.example.demo.service;

import com.example.demo.entity.Enrollment;
import com.example.demo.exception.StudentAlreadyEnrolledException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class EnrollmentService {

    private final JdbcTemplate jdbcTemplate;

    private final UserService userService;

    public EnrollmentService(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    public void save(Enrollment enrollment) throws StudentAlreadyEnrolledException {
        long userId = this.userService.findByUsername(enrollment.getUsername()).getId();
        long courseId = enrollment.getCourseId();
        if (isEnrolled(userId, courseId)) {
            throw new StudentAlreadyEnrolledException("Student with username " + enrollment.getUsername() +
                    " is already enrolled for the course!");
        }
        this.jdbcTemplate.update("insert into lms.enrollment(user_id, course_id) VALUE (?,?)", userId, courseId);
    }

    private boolean isEnrolled(Long userId, Long courseId) {
        var query = "select * from lms.enrollment where user_id = ? and course_id = ?";
        RowMapper<Date> mapperFunction = (rs, numRow) -> rs.getDate("date");
        try {
            this.jdbcTemplate.queryForObject(query, mapperFunction, userId, courseId);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}