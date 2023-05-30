package com.schedule.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "instructor_course", uniqueConstraints = {
        @UniqueConstraint(name = "UK_instructor_course", columnNames = {"instructor_id", "course_id"})
})
@Data
public class InstructorCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
