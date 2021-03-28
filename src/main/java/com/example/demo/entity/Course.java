package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer duration;

    private BigDecimal feePerMonth;

    private Integer numberOfStudents;

    @Transient
    private Long instructorId;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User user;

}
