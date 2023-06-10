package com.schedule.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.schedule.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    private Boolean isDrop; // đã bỏ học

    @ManyToOne
    @JoinColumn(name = "studentClass_id")
    private StudentClass studentClass;
}
