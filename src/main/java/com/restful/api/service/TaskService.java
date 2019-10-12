package com.restful.api.service;

import com.restful.api.dto.TaskDTO;
import com.restful.api.model.Task;
import com.restful.api.response.TaskResponse;

import java.util.List;

public interface TaskService {
    int getNumberTaskOfProject(int project_id);
    List<TaskResponse> getTaskOfProject(int project_id);
    void updateStatusTask(int task_id);
    void createTask(TaskDTO taskDTO);
    void updateTask(TaskDTO taskDTO);
    void deleteTask(int task_id);
    void updateIsSendTask(int task_id);
    List<TaskResponse> getTaskOfProjectOfEmployee(int project_id, int emp_id);
}
