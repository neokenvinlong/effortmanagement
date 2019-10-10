package com.restful.api.service.imp;

import com.restful.api.dto.TaskDTO;
import com.restful.api.exception.ResourceNotFoundException;
import com.restful.api.model.Employee;
import com.restful.api.model.Project;
import com.restful.api.model.Task;
import com.restful.api.repository.EmployeeRepository;
import com.restful.api.repository.ProjectRepository;
import com.restful.api.repository.TaskRepository;
import com.restful.api.response.TaskResponse;
import com.restful.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public int getNumberTaskOfProject(int project_id) {

        return taskRepository.getNumberTaskOfProject(project_id);
    }

    @Override
    public List<TaskResponse> getTaskOfProject(int project_id) {

        return taskRepository.getTaskOfProject(project_id);
    }

    @Override
    public void updateStatusTask(int task_id) {

        String current_taskStatus = taskRepository.getStatusTaskById(task_id);
        String update_taskStatus = "";
        if(current_taskStatus == "NOT-START" || current_taskStatus == "CLOSED") {
            update_taskStatus = "ON-GOING";
            taskRepository.updateStatusTask(update_taskStatus, task_id);
        }
        else if(current_taskStatus == "ON-GOING"){
            update_taskStatus = "DONE";
            taskRepository.updateStatusTask(update_taskStatus,task_id);
        }else{
            update_taskStatus = "CLOSED";
            taskRepository.updateStatusTask(update_taskStatus,task_id);
        }
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
        Project project = projectRepository.findById(taskDTO.getProject_id())
                .orElseThrow(()-> new ResourceNotFoundException("Project","id",taskDTO.getProject_id()));
        Employee employee = employeeRepository.findById(taskDTO.getEmp_id())
                .orElseThrow(()-> new ResourceNotFoundException("Employee","id",taskDTO.getEmp_id()));
        Task task = new Task();
        task.setCalendarEffort(taskDTO.getCalendarEffort());
        task.setDescription(taskDTO.getDescription());
        task.setEndDate(taskDTO.getEndDate());
        task.setStatus("NOT-START");
        task.setEmployee(employee);

        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Project project = projectRepository.findById(taskDTO.getProject_id())
                .orElseThrow(()-> new ResourceNotFoundException("Project","id",taskDTO.getProject_id()));
        Employee employee = employeeRepository.findById(taskDTO.getEmp_id())
                .orElseThrow(()-> new ResourceNotFoundException("Employee","id",taskDTO.getEmp_id()));
        Task task = taskRepository.findById(taskDTO.getId())
                .orElseThrow(()->new ResourceNotFoundException("Task","id",taskDTO.getId()));
        task.setCalendarEffort(taskDTO.getCalendarEffort());
        task.setDescription(taskDTO.getDescription());
        task.setEndDate(taskDTO.getEndDate());
        task.setProject(project);
        task.setEmployee(employee);

        taskRepository.save(task);
    }

    @Override
    public void deleteTask(int task_id) {
        taskRepository.updateStatusTask("CANCEL", task_id);
    }

    @Override
    public void updateIsSendTask(int task_id) {
        taskRepository.updateIsSendTask(task_id);
    }
}
