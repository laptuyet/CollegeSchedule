package com.schedule.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<StudentTimeTable> timeTables;
}
