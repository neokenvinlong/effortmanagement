package com.restful.api.repository;

import com.restful.api.model.Effort_Employee;
import com.restful.api.response.EffortResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EffortRepository extends JpaRepository<Effort_Employee, Integer> {
    @Query(value = "Update Effort_Employee Set is_approved = true Where id = :id", nativeQuery = true)
    void updateApproveById(@Param("id") int id);

    @Query(value = "Select ef.id, t.title, t.calendar_effort, ef.effort" +
            " From effort_employee as ef, employee as e, task as t, project as p" +
            " Where ef.employee_id = e.employee_id AND ef.task_id = t.task_id" +
            " AND p.project_id = t.project_id AND t.employee_id = e.employee_id" +
            " AND t.status != 'CANCEL' AND p.status != 'CANCEL'" +
            " AND p.project_id = :project_id AND e.employee_id = :employee_id", nativeQuery = true)
    List<EffortResponse> getListEffortOfEmployeeForReport(@Param("project_id") int id, @Param("employee_id") int emp_id);
//    @Query(value = "Update Effort_Employee Set effort = :effort Where id = :id", nativeQuery = true)
//    void updateEffortById(@Param("effort") double effort, @Param("id") int id);
}
