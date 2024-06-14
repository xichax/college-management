package com.bharath.college_management.service.impl;

import com.bharath.college_management.entity.Student;
import com.bharath.college_management.repository.StudentRepository;
import com.bharath.college_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students;
    }
}
