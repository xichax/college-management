package com.bharath.college_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "department")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {
    @Id
    private String id;
    private String departmentId;
    private String departmentName;
    private Boolean availableOnline;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @DBRef
    private List<Student> students = new ArrayList<>();

    @DBRef
    private List<Teacher> teachers = new ArrayList<>();

}
