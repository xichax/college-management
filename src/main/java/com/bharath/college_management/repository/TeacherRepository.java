package com.bharath.college_management.repository;

import com.bharath.college_management.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    List<Teacher> findTeacherByTeacherAge(int age);

    Optional<Teacher> findByTeacherId(String teacherId);
}
