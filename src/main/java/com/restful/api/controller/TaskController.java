package com.restful.api.controller;

import com.restful.api.dto.TaskDTO;
import com.restful.api.model.Task;
import com.restful.api.response.TaskResponse;
import com.restful.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/task/number/{id}")
    public @ResponseBody int getNumberTaskOfProject(@PathVariable(value = "id") int project_id){

        return taskService.getNumberTaskOfProject(project_id);
    }

    @GetMapping("/task/list/{id}")
    public @ResponseBody List<TaskResponse> getTaskOfProject(@PathVariable(value = "id") int project_id){

        return taskService.getTaskOfProject(project_id);
    }

    @PutMapping("/task/status/{id}")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody void updateStatusTask(@PathVariable(value = "id") int task_id){
        taskService.updateStatusTask(task_id);
    }

    @PostMapping("/task")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody void createTask(@RequestBody TaskDTO taskDTO){
        taskService.createTask(taskDTO);
    }

    @PutMapping("/task")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody void updateTask(@RequestBody TaskDTO taskDTO){
        taskService.updateTask(taskDTO);
    }

    @DeleteMapping("/task/{id}")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody void deleteTask(@PathVariable(value = "id") int task_id){
        taskService.deleteTask(task_id);
    }

    @PatchMapping("/task/{id}")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody void updateIsSendTask(@PathVariable(value = "id")int task_id){
        taskService.updateIsSendTask(task_id);
    }

    @GetMapping("/task/info/{id}")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody List<TaskResponse> getInfoOfTaskByTaskId(@PathVariable(value = "id") int task_id){

        return taskService.getInfoOfTaskByTaskId(task_id);
    }

}
