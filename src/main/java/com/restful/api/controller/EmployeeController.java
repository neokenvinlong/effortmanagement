package com.restful.api.controller;

import com.restful.api.dto.EmployeeDTO;
import com.restful.api.model.Employee;
import com.restful.api.response.EmployeeInProjectResponse;
import com.restful.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/account/{username}")
    @Secured({"ROLE_PM","ROLE_EMPLOYEE"})
    public @ResponseBody Employee getInfoOfSingleEmployeeByAccountName(@PathVariable(value = "username") String username){

        return employeeService.getInfoOfSingleEmployeeByAccountName(username);
    }

    @GetMapping("/employee/name/{name}")
    @Secured({"ROLE_PM","ROLE_EMPLOYEE"})
    public @ResponseBody Employee getInfoOfSingleEmployeeByName(@PathVariable(value = "name") String name){

        return employeeService.getInfoOfSingleEmployeeByName(name);
    }

    @GetMapping("/employee/listEmployee")
    @Secured({"ROLE_PM","ROLE_EMPLOYEE"})
    public @ResponseBody List<Employee> getListNameEmployeeByProjectIdAndByRole(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.getListNameEmployeeByProjectIdAndByRole(employeeDTO.getProject_id(), employeeDTO.getRole());
    }

    @GetMapping("/employee/countMember/{id}")
    @Secured({"ROLE_PM","ROLE_EMPLOYEE"})
    public @ResponseBody int countNumberMemberInAProject(@PathVariable(value = "id") int project_id){

        return employeeService.countNumberMemberInAProject(project_id);
    }

    @GetMapping("/employee/numberMember")
    @Secured({"ROLE_PM","ROLE_EMPLOYEE"})
    public @ResponseBody int countNumberMemberInAProjectByRole(@RequestBody EmployeeDTO employeeDTO){

        return employeeService.countNumberMemberInAProjectByRole(employeeDTO.getProject_id(), employeeDTO.getRole());
    }

    @PutMapping("/employee")
    @Secured({"ROLE_PM","ROLE_EMPLOYEE"})
    public @ResponseBody void updateInfoEmployee(@RequestBody Employee employeeDetail){
        employeeService.updateInfoEmployee(employeeDetail);
    }

//    @PostMapping("/employee")
//    @Secured("ROLE_PM")
//    public @ResponseBody void addMemberIntoProject(@RequestBody EmployeeDTO employeeDTO){
//        employeeService.addMemberIntoProject(employeeDTO);
//    }
//
//    @DeleteMapping("/employee")
//    @Secured("ROLE_PM")
//    public @ResponseBody void deleteMemberInProjectById(@RequestBody EmployeeDTO employeeDTO){
//        employeeService.deleteMemberInProjectById(employeeDTO.getId(), employeeDTO.getProject_id());
//    }

    @GetMapping("/employee/project/{project_id}")
    @Secured("ROLE_PM")
    public @ResponseBody List<EmployeeInProjectResponse> getListNameEmployeeByProjectId(@PathVariable(value = "project_id") int project_id){

        return employeeService.getListNameEmployeeByProjectId(project_id);
    }
}
