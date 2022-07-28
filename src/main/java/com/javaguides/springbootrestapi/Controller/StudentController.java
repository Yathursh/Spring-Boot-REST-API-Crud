package com.javaguides.springbootrestapi.Controller;

import com.javaguides.springbootrestapi.Exception.ResourceNotFoundException;
import com.javaguides.springbootrestapi.Model.Student;
import com.javaguides.springbootrestapi.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/s1/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping
    public List<Student> getAllStudents(){

        return studentRepo.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id){
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student not exist with id: " + id));
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student studentDetails){
        Student updateStudent = studentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student not exist with id: " + id));

        updateStudent.setFirstName(studentDetails.getFirstName());
        updateStudent.setLastName(studentDetails.getLastName());
        updateStudent.setEmailId(studentDetails.getEmailId());
        updateStudent.setStudentNo(studentDetails.getStudentNo());

        studentRepo.save(updateStudent);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id){
        Student deleteStudent = studentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student not exist with id: " + id));
        studentRepo.delete(deleteStudent);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
