package com.restful.api.controller;

import com.restful.api.model.Project;
import com.restful.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    // Get All Project By Employee Id
    @GetMapping("/project/employee/{id}")
    public List<Project> findAllProjectByEmployeeId(@PathVariable(value = "id") int employee_id) {
        return projectService.findAllProjectByEmployeeId(employee_id);
    }

    // Create a new project
    @PostMapping("/project")
    public @ResponseBody void createProject(@Valid @RequestBody Project project) {
        
        projectService.createProject(project);
    }
//
//    // Get a Single Project by id and by pm id
//    @GetMapping("/project/{id}")
//    public @ResponseBody Project getProjectByIdAndByPmId(@PathVariable(value = "id") int projectId) {
//
//        return projectService.findProjectByIdAndByPmId(projectId, pm_id);
//    }

    // Update a Project
    @PutMapping("/project/{id}")
    public @ResponseBody void updateProject(@Valid @RequestBody Project projectDetails) {

        projectService.updateProject(projectDetails);
    }

    // Delete a Project
    @DeleteMapping("/project/{id}")
    public @ResponseBody void deleteProject(@PathVariable(value = "id") int projectId) {
        projectService.deleteProjectById(projectId);
    }

//    // Get a Single Project By Name
//    @GetMapping("/project/{name}")
//    public @ResponseBody Project getProjectByName(@PathVariable(value = "name") String projectName){
//
//        return projectService.findProjectByNameAndPmId(projectName, pm_id);
//    }
//
//    // Get a Single Project By Status
//    @GetMapping("/project/{name}")
//    public @ResponseBody Project getProjectByStatus(@PathVariable(value = "status") String projectStatus){
//
//        return projectService.findProjectByStatusAndPmId(projectStatus, pm_id);
//    }

    // Update Status Project
    @PatchMapping("/project/{id}")
    public @ResponseBody void updateProjectStatusById(@PathVariable(value = "id") int projectId){
        projectService.updateProjectStatusById(projectId);
    }

    @GetMapping("/project/totalProject/{id}")
    public @ResponseBody int countNumberOfProjectByEmployeeId(@PathVariable(value = "id") int employee_id){

        return projectService.countNumberOfProjectByEmployeeId(employee_id);
    }

//     public List<Project> findAllProjectBasedOnPlannedEndDateAndPmId(){
//
//        return projectService.findProjectBasedOnPlannedEndDateAndPmId(start_date, end_date, pm_id);
//     }
//
//    public List<Project> findAllProjectBasedOnPlannedStartDateAndPmId(){
//
//        return projectService.findProjectBasedOnPlannedStartDateAndPmId(start_date, end_date, pm_id);
//    }
//
//    public List<Project> findAllProjectBasedOnActualStartDateAndPmId(){
//
//        return projectService.findProjectBasedOnActualStartDateAndPmId(start_date, end_date, pm_id);
//    }
//
//    public List<Project> findAllProjectBasedOnActualEndDateAndPmId(){
//
//        return projectService.findProjectBasedOnActualEndDateAndPmId(start_date, end_date, pm_id);
//    }
//
//    public List<Project> findAllProjectBasedOnCreateDateAndPmId(){
//
//        return projectService.findProjectBasedOnCreatedDateAndPmId(start_date, end_date, pm_id);
//   }



    @GetMapping("/project/total/{id}")
    public @ResponseBody int getTotalNumberMemberInProjectByProjectId(@PathVariable(value = "id") int project_id){

        return projectService.getTotalNumberMemberInProjectByProjectId(project_id);
    }
}
