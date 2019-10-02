package com.restful.api.service.imp;

import com.restful.api.dto.Sub_TaskDTO;
import com.restful.api.exception.ResourceNotFoundException;
import com.restful.api.model.Employee;
import com.restful.api.model.Sub_Task;
import com.restful.api.repository.EmployeeRepository;
import com.restful.api.repository.Sub_TaskRepository;
import com.restful.api.response.Sub_TaskResponse;
import com.restful.api.service.Sub_TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Sub_TaskServiceImp implements Sub_TaskService {

    @Autowired
    Sub_TaskRepository sub_taskRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public int getNumberSubTaskInTask(int task_id) {

        return sub_taskRepository.getNumberSubTaskInTask(task_id);
    }

    @Override
    public List<Sub_TaskResponse> getAllSubTaskInTask(int task_id) {

        return sub_taskRepository.getAllSubTaskInTask(task_id);
    }

    @Override
    public int getNumberSubTaskInTaskOfSingleEmployee(int task_id, int employee_id) {

        return  sub_taskRepository.getNumberSubTaskInTaskOfSingleEmployee(task_id,employee_id);
    }

    @Override
    public List<Sub_TaskResponse> getAllSubTaskInTaskOfSingleEmployee(int task_id, int employee_id) {

        return sub_taskRepository.getAllSubTaskInTaskOfSingleEmployee(task_id,employee_id);
    }

    @Override
    public void updateSubTask(Sub_TaskDTO sub_taskDTO) {

        Sub_Task sub_task = sub_taskRepository.findById(sub_taskDTO.getId())
                .orElseThrow(()->new ResourceNotFoundException("Sub_Task","id",sub_taskDTO.getId()));
        Employee employee = employeeRepository.findById(sub_taskDTO.getEmployee_id())
                .orElseThrow(()->new ResourceNotFoundException("Employee","id",sub_taskDTO.getEmployee_id()));

        sub_task.setDescription(sub_taskDTO.getDescription());
        sub_task.setTitle(sub_taskDTO.getTitle());
        sub_task.setEmployee(employee);
        sub_taskRepository.save(sub_task);
    }

    @Override
    public void createSubTask(Sub_TaskDTO sub_taskDTO) {

    }

    @Override
    public void updateStatusSubTask(int sub_task_id) {

        String status = sub_taskRepository.getStatusSubTaskById(sub_task_id);
        String new_status = "";
        if (status == "NOT-START" || status == "CLOSED"){
            new_status = "ON-GOING";
            sub_taskRepository.updateStatusSub_Task(new_status,sub_task_id);
        }else if (status == "ON-GOING"){
            new_status = "DONE";
            sub_taskRepository.updateStatusSub_Task(new_status,sub_task_id);
        }else{
            new_status = "CLOSED";
            sub_taskRepository.updateStatusSub_Task(new_status,sub_task_id);
        }
    }

    @Override
    public void updateSendStatusSubTask(int sub_task_id) {
        sub_taskRepository.updateSendStatusSubTask(sub_task_id);
    }

    @Override
    public void deleteSubTask(int sub_task_id) {
        sub_taskRepository.updateStatusSub_Task("CANCEL", sub_task_id);
    }
}
