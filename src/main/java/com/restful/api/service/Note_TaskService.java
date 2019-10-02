package com.restful.api.service;

import com.restful.api.dto.Note_TaskDTO;
import com.restful.api.model.Note_Task;
import com.restful.api.response.Note_TaskResponse;

import java.util.List;

public interface Note_TaskService {

    int getNumberNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(int emp_id, int sub_task_id);
    List<Note_TaskResponse> getListNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(int emp_id, int sub_task_id);
    List<Note_TaskResponse> getListNoteTaskOfEmployeeBySubTaskId(int sub_task_id);
    int getNumberNoteTaskOfEmployeeBySubTaskId(int sub_task_id);
    void updateNoteTask(Note_Task note_taskDetails);
    void createNoteTask(Note_TaskDTO note_taskDetails);
}
