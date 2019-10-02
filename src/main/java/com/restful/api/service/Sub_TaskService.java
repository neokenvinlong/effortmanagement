package com.restful.api.service;

import com.restful.api.dto.Sub_TaskDTO;
import com.restful.api.model.Sub_Task;
import com.restful.api.response.Sub_TaskResponse;

import java.util.List;

public interface Sub_TaskService {
    int getNumberSubTaskInTask(int task_id);
    List<Sub_TaskResponse> getAllSubTaskInTask(int task_id);
    int getNumberSubTaskInTaskOfSingleEmployee(int task_id, int employee_id);
    List<Sub_TaskResponse> getAllSubTaskInTaskOfSingleEmployee(int task_id, int employee_id);
    void updateSubTask(Sub_TaskDTO sub_taskDTO);
    void createSubTask(Sub_TaskDTO sub_taskDTO);
    void updateStatusSubTask(int sub_task_id);
    void updateSendStatusSubTask(int sub_task_id);
    void deleteSubTask(int sub_task_id);
}
