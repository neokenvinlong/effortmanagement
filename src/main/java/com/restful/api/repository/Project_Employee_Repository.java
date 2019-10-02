package com.restful.api.repository;

import com.restful.api.model.Project_Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Project_Employee_Repository extends JpaRepository<Project_Employee, Integer> {
}
