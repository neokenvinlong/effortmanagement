package com.restful.api.repository;

import com.restful.api.model.Task;
import com.restful.api.response.ReportResponse;
import com.restful.api.response.TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "Select Count(t.task_id) from task as t, project as p" +
            " Where p.project_id = t.project_id AND p.project_id = :id " +
            " AND t.status != 'CANCEL'",nativeQuery = true)
    int getNumberTaskOfProject(@Param("id") int project_id);

    @Query(value = "Select t.task_id, t.title, t.description, t.status, t.created_date, t.end_date," +
            " t.calendar_effort from task as t, project as p" +
            " Where p.project_id = t.project_id AND p.project_id = :id " +
            "AND t.status != 'CANCEL'",nativeQuery = true)
    List<TaskResponse> getTaskOfProject(@Param("id") int project_id);

    @Query(value = "Select t.task_id, t.title, t.description, t.status, t.created_date, t.end_date," +
            " t.calendar_effort from task as t, project as p, employee as e" +
            " Where p.project_id = t.project_id AND p.project_id = :id" +
            " AND e.employee_id = t.employee_id AND t.is_send=true" +
            " AND t.status != 'CANCEL' AND t.employee_id = :emp_id",nativeQuery = true)
    List<TaskResponse> getTaskOfProjectOfEmployee(@Param("id") int project_id, @Param("emp_id") int employee_id);

    @Query(value = "Update Task Set status = :status Where task_id = :id",nativeQuery = true)
    void updateStatusTask(@Param("status") String status, @Param("id")int task_id);

    @Query(value = "Update Task Set is_send = true Where task_id = :id",nativeQuery = true)
    void updateIsSendTask(@Param("id")int task_id);

    @Query(value = "Select status from task Where task_id = :id",nativeQuery = true)
    String getStatusTaskById(@Param("id") int task_id);

    @Query(value = "Select t.title, t.calendar_effort from" +
            " task as t, project as p Where" +
            " t.project_id = p.project_id AND t.status != 'CANCEL'"+
            " AND p.status != 'CANCEL' AND p.project_id = :id",nativeQuery = true)
    List<ReportResponse> getEffortOfTaskForReport(@Param("id") int project_id);
}
