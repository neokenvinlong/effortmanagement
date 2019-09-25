package com.restful.api.controller;

import com.restful.api.model.Project;
import com.restful.api.repository.ProjectRepository;
import com.restful.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    // Get All Project
    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Create a new project
    @PostMapping("/projects")
    public @ResponseBody Project createProject(@Valid @RequestBody Project project) {
        
        return projectRepository.save(project);
    }

    // Get a Single Project
    @GetMapping("/projects/{id}")
    public @ResponseBody Project getProjectById(@PathVariable(value = "id") int projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));
    }

    // Update a Project
    @PutMapping("/projects/{id}")
    public @ResponseBody Project updateProject(@PathVariable(value = "id") int projectId,
                           @Valid @RequestBody Project projectDetails) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));

        project.setName(projectDetails.getName());
        project.setDesc(projectDetails.getDesc());
        project.setStatus(projectDetails.getStatus());
        project.setPlannedStartDate(projectDetails.getPlannedStartDate());
        project.setPlannedEndDate(projectDetails.getPlannedEndDate());
        project.setActualStartDate(projectDetails.getActualStartDate());
        project.setActualEndDate(projectDetails.getActualEndDate());
//        project.setContent(projectDetails.getContent());
//        project.setApprover(projectDetails.getApprover());
//        project.setCreater(projectDetails.getCreater());
        Project updatedProject = projectRepository.save(project);
        return updatedProject;
    }

    // Delete a Project
    @DeleteMapping("/projects/{id}")
    public @ResponseBody ResponseEntity<?> deleteProject(@PathVariable(value = "id") int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));

        projectRepository.delete(project);

        return ResponseEntity.ok().build();
    }
}
