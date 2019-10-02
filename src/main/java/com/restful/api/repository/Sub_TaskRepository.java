package com.restful.api.repository;

import com.restful.api.dto.Sub_TaskDTO;
import com.restful.api.model.Sub_Task;
import com.restful.api.response.Sub_TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Sub_TaskRepository  extends JpaRepository<Sub_Task, Integer> {

    @Query(value = "Select Count(st.sub_task_id) from sub_task as st, task as t" +
            " Where st.task_id = t.task_id AND st.status != 'CANCEL'" +
            " AND t.task_id = :id",nativeQuery = true)
    int getNumberSubTaskInTask(@Param("id") int task_id);

    @Query(value = "Select st.sub_task_id, st.title, st.description, st.status, st.is_send, st.sub_calendar_effort," +
            " st.employee_id, e.name From sub_task as st, task as t, employee as e" +
            " Where st.employee_id = e.employee_id AND st.task_id = t.task_id" +
            " AND t.task_id = :id AND st.status != 'CANCEL'",nativeQuery = true)
    List<Sub_TaskResponse> getAllSubTaskInTask(@Param("id") int task_id);

    @Query(value = "Select Count(st.sub_task_id) from sub_task as st, task as t, employee as e" +
            " Where st.task_id = t.task_id AND t.task_id = :task_id" +
            " AND e.employee_id = st.employee_id AND e.employee_id = :id" +
            " AND st.status != 'CANCEL'",nativeQuery = true)
    int getNumberSubTaskInTaskOfSingleEmployee(@Param("task_id") int task_id, @Param("id") int employee_id);

    @Query(value = "Select st.sub_task_id, st.title, st.description, st.status, st.is_send, st.sub_calendar_effort" +
            " From sub_task as st, task as t, employee as e" +
            " Where st.employee_id = e.employee_id AND st.task_id = t.task_id" +
            " AND t.task_id = :task_id AND e.employee_id = :id" +
            " AND st.status != 'CANCEL'",nativeQuery = true)
    List<Sub_TaskResponse> getAllSubTaskInTaskOfSingleEmployee(@Param("task_id") int task_id, @Param("id") int employee_id);

    @Query(value = "Update Sub_Task Set Status = :status Where sub_task_id = :id",nativeQuery = true)
    void updateStatusSub_Task(@Param("status") String status, @Param("id") int sub_task_id);

    @Query(value = "Update Sub_Task Set isSend = true Where sub_task_id = :id",nativeQuery = true)
    void updateSendStatusSubTask(@Param("id") int sub_task_id);

    @Query(value = "Select status from Sub_task Where sub_task_id = :id", nativeQuery = true)
    String getStatusSubTaskById(@Param("id") int sub_task_id);
}
