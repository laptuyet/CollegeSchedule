package com.schedule.repo;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.schedule.domain.Instructor;
import com.schedule.domain.Student;
import com.schedule.model.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserCustomRepo {

	private final EntityManager em;

	public User getUserByUsername(String username) {
		
		String studentSql = "select * from student s where s.username = ?1";
		String instructorSql = "select * from instructor i where i.username = ?1";
		
		User res;
		
		Query query1 = em.createNativeQuery(studentSql, Student.class);
		query1.setParameter(1, username);
		
		Query query2 = em.createNativeQuery(instructorSql, Instructor.class);
		query2.setParameter(1, username);
		
		var list1 = query1.getResultList();
		var list2 = query2.getResultList();
		
		if(!list1.isEmpty()) {
			res = (Student) list1.get(0);
		} else if (!list2.isEmpty()) {
			res = (Instructor) list2.get(0);
		} else res = null;
		
		return res;
	}
}
