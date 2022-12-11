package com.delta.school.repositories;

import com.delta.school.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);
}
