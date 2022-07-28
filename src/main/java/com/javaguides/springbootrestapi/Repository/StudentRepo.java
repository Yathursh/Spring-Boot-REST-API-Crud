package com.javaguides.springbootrestapi.Repository;

import com.javaguides.springbootrestapi.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
