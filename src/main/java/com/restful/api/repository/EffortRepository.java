package com.restful.api.repository;

import com.restful.api.model.Effort_Employee;
import com.restful.api.response.EffortResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EffortRepository extends JpaRepository<Effort_Employee, Integer> {

    @Modifying
    @Query(value = "Update Effort_Employee Set is_approved = true Where id = :id", nativeQuery = true)
    void updateApproveById(@Param("id") int id);

    @Query(value = "Select ef.id, t.title, t.calendar_effort, ef.effort" +
            " From effort_employee as ef, employee as e, task as t" +
            " Where ef.employee_id = e.employee_id AND ef.task_id = t.task_id" +
            " AND t.employee_id = e.employee_id" +
            " AND t.status != 'CANCEL' AND ef.is_approved = true" +
            " AND ef.employee_id = :emp_id", nativeQuery = true)
    List<EffortResponse> getListEffortOfEmployeeForReport(@Param("emp_id") int emp_id);

    @Query(value = "Select ef.id, t.title, t.calendar_effort, ef.effort, e.account_name" +
            " From effort_employee as ef, employee as e, task as t, project as p, project_employee as pe" +
            " Where ef.employee_id = e.employee_id AND ef.task_id = t.task_id" +
            " AND p.project_id = t.project_id AND ef.employee_id = e.employee_id" +
            " AND t.status != 'CANCEL' AND p.status != 'CANCEL'" +
            " AND pe.employee_id = e.employee_id AND pe.project_id = p.project_id" +
            " AND ef.is_approved = false AND t.is_send = true", nativeQuery = true)
    List<EffortResponse> getListEffortWaitingApprove();
//    @Query(value = "Update Effort_Employee Set effort = :effort Where id = :id", nativeQuery = true)
//    void updateEffortById(@Param("effort") double effort, @Param("id") int id);

    @Query(value = "Select id from effort_employee Where employee_id = :emp_id AND task_id = :id",nativeQuery = true)
    int getEffortIdByTaskIdAndEmpId(@Param("emp_id") int emp_id, @Param("id") int task_id);

    @Modifying
    @Query(value = "Update effort_employee Set employee_id = :emp_id Where task_id = :task_id",nativeQuery = true)
    void updateEmployeeIdInEffortEmployee(@Param("emp_id") int emp_id, @Param("task_id") int task_id);

    @Modifying
    @Query(value = "Update effort_employee Set effort = :effort Where task_id = :task_id", nativeQuery = true)
    void updateEffortByTaskId(@Param("effort") double effort, @Param("task_id") int task_id);
}
