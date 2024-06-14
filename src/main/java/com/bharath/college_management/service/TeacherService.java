package com.bharath.college_management.service;

import com.bharath.college_management.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();
}
