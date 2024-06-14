package com.bharath.college_management.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Department {
    @Id
    private String id;
    private String depId;
    private String departmentName;
    private Boolean running;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private List<Student> students;

    private List<Teacher> teachers;
}
