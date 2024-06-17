package com.bharath.college_management.service;

import com.bharath.college_management.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();

    Student getStudentById(String studentId);

    List<Student> getStudentByAge(int age);

    Student updateStudent(String studentId, Student student);

    void deleteStudent(String studentId);

    List<Student> getStudentsInPages(int page, int size);


}
