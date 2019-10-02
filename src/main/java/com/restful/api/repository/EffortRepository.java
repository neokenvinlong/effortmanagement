package com.restful.api.repository;

import com.restful.api.model.Effort_Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EffortRepository extends JpaRepository<Effort_Employee, Integer> {
    @Query(value = "Update Effort_Employee Set is_approved = true Where id = :id", nativeQuery = true)
    void updateApproveById(@Param("id") int id);
}
