package com.bharath.college_management.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {
    @Id
    private String id;
    private String studentId;
    private String studentName;
    private int studentAge;
    private Department studentDepartment;

}
