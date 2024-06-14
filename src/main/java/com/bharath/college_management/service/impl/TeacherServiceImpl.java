package com.bharath.college_management.service.impl;

import com.bharath.college_management.entity.Teacher;
import com.bharath.college_management.repository.TeacherRepository;
import com.bharath.college_management.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }
}
