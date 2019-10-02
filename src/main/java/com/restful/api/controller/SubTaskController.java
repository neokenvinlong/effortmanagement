package com.restful.api.controller;

import com.restful.api.dto.Sub_TaskDTO;
import com.restful.api.model.Sub_Task;
import com.restful.api.response.Sub_TaskResponse;
import com.restful.api.service.Sub_TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub_tasks")
public class SubTaskController {

    @Autowired
    Sub_TaskService sub_taskService;

    @GetMapping("/sub_task/{id}")
    public @ResponseBody int getNumberSubTaskInTask(@PathVariable(value = "id") int task_id){

        return sub_taskService.getNumberSubTaskInTask(task_id);
    }

    @GetMapping("/sub_task/all/{id}")
    public @ResponseBody List<Sub_TaskResponse> getAllSubTaskInTask(@PathVariable(value = "id") int task_id){

        return sub_taskService.getAllSubTaskInTask(task_id);
    }

    @GetMapping("/sub_task/number")
    public @ResponseBody int getNumberSubTaskInTaskOfSingleEmployee(@RequestBody Sub_TaskDTO sub_taskDTO){

        return sub_taskService.getNumberSubTaskInTaskOfSingleEmployee(sub_taskDTO.getTaskId(), sub_taskDTO.getEmployee_id());
    }

    @GetMapping("sub_task/all")
    public @ResponseBody List<Sub_TaskResponse> getAllSubTaskInTaskOfSingleEmployee(@RequestBody Sub_TaskDTO sub_taskDTO){

        return sub_taskService.getAllSubTaskInTaskOfSingleEmployee(sub_taskDTO.getTaskId(), sub_taskDTO.getEmployee_id());
    }

    @PutMapping("/sub_task")
    public  @ResponseBody void updateSubTask(@RequestBody Sub_TaskDTO sub_taskDTO){
        sub_taskService.updateSubTask(sub_taskDTO);
    }

    @PostMapping("/sub_task")
    public @ResponseBody void createSubTask(@RequestBody Sub_TaskDTO sub_taskDTO){
        sub_taskService.createSubTask(sub_taskDTO);
    }

    @PutMapping("/sub_task/status/{id}")
    public @ResponseBody void updateStatusSubTask(@PathVariable(value = "id") int sub_task_id){
        sub_taskService.updateStatusSubTask(sub_task_id);
    }

    @PutMapping("/sub_task/send/{id}")
    public @ResponseBody void updateSendStatusSubTask(@PathVariable(value = "id") int sub_task_id){
        sub_taskService.updateSendStatusSubTask(sub_task_id);
    }

    @DeleteMapping("/sub_task/{id}")
    public @ResponseBody void deleteSubTask(@PathVariable(value = "id") int sub_task_id){
        sub_taskService.deleteSubTask(sub_task_id);
    }
}
