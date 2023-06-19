package com.schedule.designpattern;

import com.schedule.domain.Instructor;
import com.schedule.domain.Student;
import com.schedule.model.User;

public class UserFactory {
	public static User getUser(UserType userType) {

		switch (userType) {
		case INSTRUCTOR: {
			return new Instructor();
		}
		case STUDENT: {
			return new Student();
		}
		default:
			throw new IllegalArgumentException("This user type is unsupported");
		}
	}
}
