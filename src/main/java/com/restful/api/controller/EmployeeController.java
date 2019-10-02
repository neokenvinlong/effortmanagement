package com.restful.api.controller;

import com.restful.api.dto.EmployeeDTO;
import com.restful.api.model.Employee;
import com.restful.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/account/{username}")
    public @ResponseBody Employee getInfoOfSingleEmployeeByAccountName(@PathVariable(value = "username") String username){

        return employeeService.getInfoOfSingleEmployeeByAccountName(username);
    }

    @GetMapping("/employee/name/{name}")
    public @ResponseBody Employee getInfoOfSingleEmployeeByName(@PathVariable(value = "name") String name){

        return employeeService.getInfoOfSingleEmployeeByName(name);
    }

    @GetMapping("/employee/listEmployee")
    public @ResponseBody List<Employee> getListNameEmployeeByProjectIdAndByRole(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.getListNameEmployeeByProjectIdAndByRole(employeeDTO.getProject_id(), employeeDTO.getRole());
    }

    @GetMapping("/employee/countMember/{id}")
    public @ResponseBody int countNumberMemberInAProject(@PathVariable(value = "id") int project_id){

        return employeeService.countNumberMemberInAProject(project_id);
    }

    @GetMapping("/employee/numberMember")
    public @ResponseBody int countNumberMemberInAProjectByRole(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.countNumberMemberInAProjectByRole(employeeDTO.getProject_id(), employeeDTO.getRole());
    }

    @PutMapping("/employee")
    public @ResponseBody void updateInfoEmployee(@RequestBody Employee employeeDetail){
        employeeService.updateInfoEmployee(employeeDetail);
    }

    @PostMapping("/employee")
    public @ResponseBody void addMemberIntoProject(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addMemberIntoProject(employeeDTO);
    }

    @DeleteMapping("/employee")
    public @ResponseBody void deleteMemberInProjectById(@RequestBody EmployeeDTO employeeDTO){
        employeeService.deleteMemberInProjectById(employeeDTO.getId(), employeeDTO.getProject_id());
    }
}
