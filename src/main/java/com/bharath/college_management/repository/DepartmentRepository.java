package com.bharath.college_management.repository;

import com.bharath.college_management.Exceptions.DepartmentNotFoundException;
import com.bharath.college_management.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    Optional<Department> findByDepartmentId(String departmentId);
}
