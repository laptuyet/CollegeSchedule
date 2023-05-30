package com.schedule.domain;

import com.schedule.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
