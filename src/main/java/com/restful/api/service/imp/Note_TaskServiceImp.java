package com.restful.api.service.imp;

import com.restful.api.dto.Note_TaskDTO;
import com.restful.api.exception.ResourceNotFoundException;
import com.restful.api.model.Employee;
import com.restful.api.model.Note_Task;
import com.restful.api.model.Sub_Task;
import com.restful.api.repository.EmployeeRepository;
import com.restful.api.repository.Note_TaskRepository;
import com.restful.api.repository.Sub_TaskRepository;
import com.restful.api.response.Note_TaskResponse;
import com.restful.api.service.Note_TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class Note_TaskServiceImp implements Note_TaskService {

    @Autowired
    Note_TaskRepository note_taskRepository;
    @Autowired
    Sub_TaskRepository sub_taskRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public int getNumberNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(int emp_id, int sub_task_id) {

        return note_taskRepository.getNumberNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(emp_id,sub_task_id);
    }

    @Override
    public List<Note_TaskResponse> getListNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(int emp_id, int sub_task_id) {

        return note_taskRepository.getListNoteTaskOfEmployeeByEmployeeIdAndSubTaskId(emp_id,sub_task_id);
    }

    @Override
    public List<Note_TaskResponse> getListNoteTaskOfEmployeeBySubTaskId(int sub_task_id) {

        return note_taskRepository.getListNoteTaskOfEmployeeBySubTaskId(sub_task_id);
    }

    @Override
    public int getNumberNoteTaskOfEmployeeBySubTaskId(int sub_task_id) {

        return note_taskRepository.getNumberNoteTaskOfEmployeeBySubTaskId(sub_task_id);
    }

    @Override
    public void updateNoteTask(Note_Task note_taskDetails) {

        int note_taskDetailsId = note_taskDetails.getId();
        Note_Task note_task = note_taskRepository.findById(note_taskDetailsId)
                .orElseThrow(()-> new ResourceNotFoundException("Note_Task","id",note_taskDetailsId));

        note_task.setContent(note_taskDetails.getContent());
        LocalDate localDate = LocalDate.now();
        note_task.setReceiveTime(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        note_task.setTitle(note_taskDetails.getTitle());

        note_taskRepository.save(note_task);
    }

    @Override
    public void createNoteTask(Note_TaskDTO note_taskDetails) {

        int employee_id = note_taskDetails.getEmployee_id();
        Employee employee = employeeRepository.findById(employee_id)
                .orElseThrow(()->new ResourceNotFoundException("Employee","id",employee_id));

        int sub_task_id = note_taskDetails.getSub_task_id();
        Sub_Task sub_task = sub_taskRepository.findById(sub_task_id)
                .orElseThrow(()->new ResourceNotFoundException("Sub_Task","id",sub_task_id));

        Note_Task note_task = new Note_Task();
        note_task.setTitle(note_taskDetails.getTitle());
        note_task.setContent(note_taskDetails.getContent());
        note_task.setEmployee(employee);
        note_task.setSub_task(sub_task);

        note_taskRepository.save(note_task);
    }
}
