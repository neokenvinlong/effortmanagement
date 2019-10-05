package com.restful.api.service.imp;

import com.restful.api.dto.EffortDTO;
import com.restful.api.exception.ResourceNotFoundException;
import com.restful.api.model.Effort_Employee;
import com.restful.api.model.Employee;
import com.restful.api.model.Task;
import com.restful.api.repository.EffortRepository;
import com.restful.api.repository.EmployeeRepository;
import com.restful.api.repository.TaskRepository;
import com.restful.api.service.EffortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EffortServiceImp implements EffortService {

    @Autowired
    EffortRepository effortRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TaskRepository taskRepository;

    @Override
    public void updateApproveById(int id) {
        effortRepository.updateApproveById(id);
    }

    @Override
    public void updateEffortById(EffortDTO effortDTO) {
        Effort_Employee effort_employee = effortRepository.findById(effortDTO.getId())
                .orElseThrow(()->new ResourceNotFoundException("Effort_Employee","id",effortDTO.getId()));
        effort_employee.setEffort(effortDTO.getEffort());
        effortRepository.save(effort_employee);
    }

    @Override
    public void createEffort(EffortDTO effortDTO) {
        Employee employee = employeeRepository.findById(effortDTO.getEmp_id())
                .orElseThrow(()->new ResourceNotFoundException("Employee","id",effortDTO.getEmp_id()));
        Task task = taskRepository.findById(effortDTO.getTask_id())
                .orElseThrow(()->new ResourceNotFoundException("Task","id",effortDTO.getTask_id()));
        Effort_Employee effort_employee = new Effort_Employee();
        effort_employee.setEffort(effortDTO.getEffort());
        effort_employee.setIs_approved(false);
        effort_employee.setEmployee(employee);
        effort_employee.setTask(task);

        effortRepository.save(effort_employee);
    }
}
