package com.restful.api.service.imp;

import com.restful.api.dto.EmployeeDTO;
import com.restful.api.exception.ResourceNotFoundException;
import com.restful.api.model.Account;
import com.restful.api.model.Employee;
import com.restful.api.model.Project;
import com.restful.api.model.Project_Employee;
import com.restful.api.repository.AccountRepository;
import com.restful.api.repository.EmployeeRepository;
import com.restful.api.repository.ProjectRepository;
import com.restful.api.repository.Project_Employee_Repository;
import com.restful.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    Project_Employee_Repository project_employee_repository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Employee getInfoOfSingleEmployeeByAccountName(String username) {

        return employeeRepository.getInfoOfSingleEmployeeByAccountName(username)
                .orElseThrow(()-> new ResourceNotFoundException("Account","name",username));
    }

    @Override
    public Employee getInfoOfSingleEmployeeByName(String name) {

        return employeeRepository.getInfoOfSingleEmployeeByName(name)
                .orElseThrow(()-> new ResourceNotFoundException("Employee","name",name));
    }
//
//    @Override
//    public List<Employee> getListNameOfEmployeeByNameAndByProjectId(String name, int project_id) {
//
//        return employeeRepository.getListNameOfEmployeeByNameAndByProjectId(name,project_id);
//    }

    @Override
    public List<Employee> getListNameEmployeeByProjectIdAndByRole(int project_id, String role) {

        return employeeRepository.getListNameEmployeeByProjectIdAndByRole(project_id,role);
    }

    @Override
    public int countNumberMemberInAProject(int project_id) {

        return employeeRepository.countNumberMemberInAProject(project_id);
    }

    @Override
    public int countNumberMemberInAProjectByRole(int project_id, String role) {

        return employeeRepository.countNumberMemberInAProjectByRole(project_id, role);
    }

    @Override
    public void updateInfoEmployee(Employee employeeDetail) {

        Employee employee = employeeRepository.findById(employeeDetail.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Employee","id",employeeDetail.getId()));

        employee.setPhone(employeeDetail.getPhone());
        employee.setEmail(employeeDetail.getEmail());
        employee.setSkill(employeeDetail.getSkill());

        employeeRepository.save(employee);
    }

    @Override
    public void addMemberIntoProject(EmployeeDTO employeeDTO) {
        Account account = accountRepository.findByName(employeeDTO.getAccount_name());

        Employee employee = new Employee();
        employee.setSkill(employeeDTO.getSkill());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setAccountName(account);
        employee.setName(employeeDTO.getName());
        employeeRepository.save(employee);

        Project_Employee project_employee = new Project_Employee();
        project_employee.setEmployee(employee);
        Integer project_id = employeeDTO.getProject_id();
        Project project = projectRepository.findById(project_id)
                .orElseThrow(()-> new ResourceNotFoundException("Project","id",project_id));
        project_employee.setProject(project);
        project_employee.setStatus(true);
        project_employee_repository.save(project_employee);

    }

    @Override
    public void deleteMemberInProjectById(int employee_id, int project_id) {
        employeeRepository.deleteEmployeeInProjectById(employee_id,project_id);
    }
}
