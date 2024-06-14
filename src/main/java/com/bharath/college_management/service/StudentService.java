package com.bharath.college_management.service;

import com.bharath.college_management.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();


}
