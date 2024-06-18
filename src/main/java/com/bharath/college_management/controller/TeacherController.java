package com.bharath.college_management.controller;

import com.bharath.college_management.entity.Teacher;
import com.bharath.college_management.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cm")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    //    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "get/t/get-all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/t/create")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }


    @GetMapping("get/age")
    public ResponseEntity<List<Teacher>> getTeacherByAge(@RequestParam int age) {
        List<Teacher> teachers = teacherService.getTeacherByAge(age);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PutMapping("t/update/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String teacherId, @RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(teacherId, teacher);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    @DeleteMapping("t/delete/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String teacherId) {
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("get/t/page")
    public ResponseEntity<List<Teacher>> getTeachersInPages(@RequestParam int page, @RequestParam int size) {
        List<Teacher> teachers = teacherService.getTeachersInPages(page, size);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
}
