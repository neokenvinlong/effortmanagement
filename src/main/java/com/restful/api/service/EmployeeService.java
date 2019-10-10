package com.restful.api.service;

import com.restful.api.dto.EmployeeDTO;
import com.restful.api.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getInfoOfSingleEmployeeByAccountName(String username);
    Employee getInfoOfSingleEmployeeByName(String name);
    List<Employee> getListNameEmployeeByProjectIdAndByRole(int project_id, String role);
    int countNumberMemberInAProject(int project_id);
    int countNumberMemberInAProjectByRole(int project_id, String role);
    void updateInfoEmployee(Employee employeeDetail);
   // void addMemberIntoProject(EmployeeDTO employeeDTO);
   // void deleteMemberInProjectById(int employee_id, int project_id);
}
