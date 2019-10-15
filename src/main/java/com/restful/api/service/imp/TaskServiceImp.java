package com.restful.api.service.imp;

import com.restful.api.dto.TaskDTO;
import com.restful.api.exception.ResourceNotFoundException;
import com.restful.api.model.Effort_Employee;
import com.restful.api.model.Employee;
import com.restful.api.model.Project;
import com.restful.api.model.Task;
import com.restful.api.repository.EffortRepository;
import com.restful.api.repository.EmployeeRepository;
import com.restful.api.repository.ProjectRepository;
import com.restful.api.repository.TaskRepository;
import com.restful.api.response.TaskResponse;
import com.restful.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EffortRepository effortRepository;

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
        if(current_taskStatus.equals("NOT-START") || current_taskStatus.equals("CLOSED")) {
            update_taskStatus = "ON-GOING";
            taskRepository.updateStatusTask(update_taskStatus, task_id);
        }
        else if(current_taskStatus.equals("ON-GOING")){
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
        task.setProject(project);
        task.setTitle(taskDTO.getTitle());
        task.setCreatedDate(new Date());

        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Project project = projectRepository.findById(taskDTO.getProject_id())
                .orElseThrow(()-> new ResourceNotFoundException("Project","project_id",taskDTO.getProject_id()));
        Employee employee = employeeRepository.findById(taskDTO.getEmp_id())
                .orElseThrow(()-> new ResourceNotFoundException("Employee","id",taskDTO.getEmp_id()));
        Task task = taskRepository.findById(taskDTO.getId())
                .orElseThrow(()->new ResourceNotFoundException("Task","id",taskDTO.getId()));
        task.setCalendarEffort(taskDTO.getCalendarEffort());
        task.setDescription(taskDTO.getDescription());
        task.setEndDate(taskDTO.getEndDate());
        task.setProject(project);
        task.setEmployee(employee);
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.getStatus());

        taskRepository.save(task);

        effortRepository.updateEmployeeIdInEffortEmployee(taskDTO.getEmp_id(), taskDTO.getId());

//        taskRepository.updateTaskById(taskDTO.getCalendarEffort(), taskDTO.getDescription(),taskDTO.getEndDate(),
//                taskDTO.getTitle(),taskDTO.getId(),taskDTO.getEmp_id());
    }

    @Override
    public void deleteTask(int task_id) {
        taskRepository.updateStatusTask("CANCEL", task_id);
    }

    @Override
    public void updateIsSendTask(int task_id) {
        taskRepository.updateIsSendTask(task_id);
    }

    @Override
    public TaskResponse getInfoOfTaskByTaskId(int task_id) {

        return taskRepository.getInfoOfTaskByTaskId(task_id);
    }

    @Override
    public List<TaskResponse> getListTaskOfEmployeeByAccountName(String account_name) {

        return taskRepository.getListTaskOfEmployeeByAccountName(account_name);
    }

    @Override
    public void n_createTaskAndEffort(TaskDTO taskDTO) {
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
        task.setProject(project);
        task.setTitle(taskDTO.getTitle());
        task.setCreatedDate(new Date());

        taskRepository.save(task);

        Effort_Employee ee = new Effort_Employee();
        ee.setTask(task);
        ee.setEmployee(employee);
        ee.setEffort(0.0);
        ee.setIs_approved(false);
        effortRepository.save(ee);
    }
}
