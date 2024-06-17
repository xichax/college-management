package com.bharath.college_management.service;

import com.bharath.college_management.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();

    Teacher getTeacherById(String teacherId);

    List<Teacher> getTeacherByAge(int age);

    Teacher updateTeacher(String teacherId, Teacher teacher);

    void deleteTeacher(String teacherId);

    List<Teacher> getTeachersInPages(int page, int size);
}
