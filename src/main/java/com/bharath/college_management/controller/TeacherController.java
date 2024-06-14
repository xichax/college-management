package com.bharath.college_management.controller;

import com.bharath.college_management.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;
}
