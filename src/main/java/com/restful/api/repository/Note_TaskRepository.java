package com.restful.api.repository;

import com.restful.api.dto.Note_TaskDTO;
import com.restful.api.model.Note_Task;
import com.restful.api.response.Note_TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Note_TaskRepository extends JpaRepository<Note_Task,Integer> {

    @Query(value = "Select Count(nt.note_task_id)" +
            " From note_task as nt, sub_task as st, employee as e" +
            " Where e.employee_id = nt.employee_id AND nt.sub_task_id = st.sub_task_id" +
            " AND e.employee_id = :emp_id AND st.sub_task_id = :sub_task_id",nativeQuery = true)
    int getNumberNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(@Param("emp_id") int emp_id,
                                                            @Param("sub_task_id") int sub_task_id);

    @Query(value = "Select nt.note_task_id, nt.title, nt.content, nt.send_time, nt.receive_time" +
            ", e.name, e.employee_id" +
            " From note_task as nt, sub_task as st, employee as e" +
            " Where e.employee_id = nt.employee_id AND nt.sub_task_id = st.sub_task_id" +
            " AND e.employee_id = :emp_id AND st.sub_task_id = :sub_task_id",nativeQuery = true)
    List<Note_TaskResponse> getListNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(@Param("emp_id") int emp_id,
                                                                              @Param("sub_task_id") int sub_task_id);

    @Query(value = "Select nt.note_task_id, nt.title, nt.content, nt.send_time, nt.receive_time, e.name, e.employee_id" +
            " From note_task as nt, sub_task as st, employee as e" +
            " Where e.employee_id = nt.employee_id AND nt.sub_task_id = st.sub_task_id" +
            " AND st.sub_task_id = :sub_task_id",nativeQuery = true)
    List<Note_TaskResponse> getListNoteTaskOfEmployeeBySubTaskId(@Param("sub_task_id") int sub_task_id);

    @Query(value = "Select Count(note_task_id)" +
            " From note_task as nt, sub_task as st" +
            " Where nt.sub_task_id = st.sub_task_id" +
            " AND st.sub_task_id = :sub_task_id",nativeQuery = true)
    int getNumberNoteTaskOfEmployeeBySubTaskId(@Param("sub_task_id") int sub_task_id);
}
