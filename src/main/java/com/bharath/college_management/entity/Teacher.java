package com.bharath.college_management.entity;

import org.springframework.data.annotation.Id;

public class Teacher {
    @Id
    private String id;
    private String teacherId;
    private String teacherName;
    private int teacherAge;
    private Department teacherDepartment;
}
