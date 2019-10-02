package com.restful.api.controller;

import com.restful.api.dto.Note_TaskDTO;
import com.restful.api.model.Note_Task;
import com.restful.api.response.Note_TaskResponse;
import com.restful.api.service.Note_TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note_tasks")
public class NoteTaskController {

    @Autowired
    Note_TaskService note_taskService;

    @GetMapping("/note_task/number")
    public @ResponseBody int getNumberNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(@RequestBody Note_TaskDTO note_taskDTO){

        return note_taskService.getNumberNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(note_taskDTO.getEmployee_id(), note_taskDTO.getSub_task_id());
    }

    @GetMapping("/note_task/list")
    public @ResponseBody List<Note_TaskResponse> getListNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(@RequestBody Note_TaskDTO note_taskDTO){

        return note_taskService.getListNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(note_taskDTO.getEmployee_id(), note_taskDTO.getSub_task_id());
    }

    @GetMapping("/note_task/list/{id}")
    public @ResponseBody List<Note_TaskResponse> getListNoteTaskOfEmployeeBySubTaskId(@PathVariable(value = "id") int sub_task_id){

        return note_taskService.getListNoteTaskOfEmployeeBySubTaskId(sub_task_id);
    }

    @GetMapping("/note_task/num/{id}")
    public @ResponseBody int getNumberNoteTaskOfEmployeeBySubTaskId(@PathVariable(value = "id") int sub_task_id){

        return note_taskService.getNumberNoteTaskOfEmployeeBySubTaskId(sub_task_id);
    }

    @PutMapping("/note_task")
    public @ResponseBody void updateNoteTask(@RequestBody Note_Task note_taskDetails){
        note_taskService.updateNoteTask(note_taskDetails);
    }

    @PostMapping("/note_task")
    public @ResponseBody void createNoteTask(@RequestBody Note_TaskDTO note_taskDetails){
        note_taskService.createNoteTask(note_taskDetails);
    }
}
