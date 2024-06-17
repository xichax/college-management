package com.bharath.college_management.repository;

import com.bharath.college_management.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findStudentByStudentAge(int age); // or use  @Query("{'studentAge': ?0}")

    Optional<Student> findByStudentId(String studentId);
}
