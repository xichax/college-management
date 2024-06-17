package com.bharath.college_management.service.impl;

import com.bharath.college_management.Exceptions.BranchInvalidException;
import com.bharath.college_management.Exceptions.DepartmentNotFoundException;
import com.bharath.college_management.Exceptions.TeacherNotFoundException;
import com.bharath.college_management.entity.Department;
import com.bharath.college_management.entity.Student;
import com.bharath.college_management.entity.Teacher;
import com.bharath.college_management.enums.Branch;
import com.bharath.college_management.repository.DepartmentRepository;
import com.bharath.college_management.repository.TeacherRepository;
import com.bharath.college_management.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public Teacher saveTeacher(Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        String teacherDepartmentId = teacher.getTeacherDepartmentId();

        if (!Branch.isValidBranch(teacherDepartmentId)) {
            throw new BranchInvalidException("Branch not found: " + teacherDepartmentId);
        }

        Optional<Department> teacherDepartment = departmentRepository.findByDepartmentId(teacherDepartmentId);
        if(teacherDepartment.isPresent()) {
            teacherDepartment.get().getTeachers().add(teacher);
            departmentRepository.save(teacherDepartment.get());
        } else {
            throw new DepartmentNotFoundException("Department not found with Id : " + teacherDepartmentId);
        }

        return savedTeacher;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(String teacherId) {
        try {
            Optional<Teacher> foundTeacher = teacherRepository.findByTeacherId(teacherId);
            return foundTeacher.orElseThrow(() -> new TeacherNotFoundException("Teacher not found with ID: " + teacherId));
        } catch (Exception e) {
            throw new TeacherNotFoundException("Teacher not found with ID: " + teacherId, e);
        }
    }

    public List<Teacher> getTeachersByAge(int age) {
        List<Teacher> foundTeachers = teacherRepository.findTeacherByTeacherAge(age);
        return foundTeachers != null ? foundTeachers : Collections.emptyList();
    }

    public List<Teacher> getTeacherByAge(int age) {
        List<Teacher> foundTeachers = teacherRepository.findTeacherByTeacherAge(age);
        return foundTeachers != null ? foundTeachers : Collections.emptyList();
    }

    public Teacher updateTeacher(String teacherId, Teacher teacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isPresent()) {
            Teacher existingTeacher = optionalTeacher.get();
            existingTeacher.setTeacherId(teacher.getTeacherId());
            existingTeacher.setTeacherName(teacher.getTeacherName());
            existingTeacher.setTeacherAge(teacher.getTeacherAge());
            existingTeacher.setContact(teacher.getContact());
            existingTeacher.setTeacherDepartmentId(teacher.getTeacherDepartmentId());
            existingTeacher.setCreatedAt(teacher.getCreatedAt());
            existingTeacher.setUpdatedAt(LocalDateTime.now());
            return teacherRepository.save(existingTeacher);
        } else {
            throw new TeacherNotFoundException("Teacher not found with ID: " + teacherId);
        }
    }

    public void deleteTeacher(String teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findByTeacherId(teacherId);
        if (!optionalTeacher.isPresent()) {
            throw new TeacherNotFoundException("Teacher not found with ID: " + teacherId);
        }
        String departmentId = optionalTeacher.get().getTeacherDepartmentId();
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentId(departmentId);
        Department existingDepartment;
        if (optionalDepartment.isPresent()) {
            existingDepartment = optionalDepartment.get();
            existingDepartment.getTeachers().removeIf(teacher -> teacher.getTeacherId().equals(teacherId));
        } else {
            throw new DepartmentNotFoundException("Department not found with ID: " + departmentId);
        }
        departmentRepository.save(existingDepartment);
        teacherRepository.delete(optionalTeacher.get());
    }


    public List<Teacher> getTeachersInPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);
        return teacherPage.getContent();
    }
}
