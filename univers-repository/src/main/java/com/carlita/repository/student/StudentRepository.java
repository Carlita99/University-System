package com.carlita.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carlita.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

	@Query("select s from Student s order by s.firstName")
	List<Student> getAllStudents();
}
