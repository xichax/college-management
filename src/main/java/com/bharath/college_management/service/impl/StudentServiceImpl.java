package com.bharath.college_management.service.impl;

import com.bharath.college_management.Exceptions.BranchInvalidException;
import com.bharath.college_management.Exceptions.DepartmentNotFoundException;
import com.bharath.college_management.Exceptions.StudentNotFoundException;
import com.bharath.college_management.entity.Department;
import com.bharath.college_management.entity.Student;
import com.bharath.college_management.enums.Branch;
import com.bharath.college_management.repository.DepartmentRepository;
import com.bharath.college_management.repository.StudentRepository;
import com.bharath.college_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public Student saveStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        String studentDepartmentId = student.getStudentDepartmentId();

        if (!Branch.isValidBranch(studentDepartmentId)) {
            throw new BranchInvalidException("Branch not found: " + studentDepartmentId);
        }

        Optional<Department> studentDepartment = departmentRepository.findByDepartmentId(studentDepartmentId);
        if(studentDepartment.isPresent()) {
            studentDepartment.get().getStudents().add(student);
            departmentRepository.save(studentDepartment.get());
        } else {
            throw new DepartmentNotFoundException("Department not found with Id : " + studentDepartmentId);
        }

        return savedStudent;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student getStudentById(String studentId) {
        try {
            Optional<Student> foundStudent = studentRepository.findByStudentId(studentId);
            return foundStudent.orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));
        } catch (Exception e) {
            throw new StudentNotFoundException("Student not found with ID: " + studentId, e);
        }
    }

    public List<Student> getStudentByAge(int age) {
        List<Student> foundStudents = studentRepository.findStudentByStudentAge(age);
        return foundStudents != null ? foundStudents : Collections.emptyList();
    }

    public Student updateStudent(String studentId, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setStudentId(student.getStudentId());
            existingStudent.setStudentName(student.getStudentName());
            existingStudent.setStudentAge(student.getStudentAge());
            existingStudent.setContact(student.getContact());
            existingStudent.setStudentDepartmentId(student.getStudentDepartmentId());
            existingStudent.setCreatedAt(student.getCreatedAt());
            existingStudent.setUpdatedAt(LocalDateTime.now());
            return studentRepository.save(existingStudent);
        } else {
            throw new StudentNotFoundException("Student not found with ID: " + studentId);
        }
    }

    public void deleteStudent(String studentId) {
        Optional<Student> optionalStudent = studentRepository.findByStudentId(studentId);
        if (optionalStudent.isEmpty()) {
            throw new StudentNotFoundException("Student not found with ID: " + studentId);
        }
        String departmentId = optionalStudent.get().getStudentDepartmentId();
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentId(departmentId);
        Department existingDepartment;
        if (optionalDepartment.isPresent()) {
            existingDepartment = optionalDepartment.get();
            existingDepartment.getStudents().removeIf(student -> student.getStudentId().equals(studentId));
        } else {
            throw new DepartmentNotFoundException("Department not found with ID: " + departmentId);
        }
        departmentRepository.save(existingDepartment);
        studentRepository.delete(optionalStudent.get());
    }

    public List<Student> getStudentsInPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.getContent();
    }
}
