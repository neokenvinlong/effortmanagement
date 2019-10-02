package com.restful.api.service.imp;

import com.restful.api.exception.ResourceNotFoundException;
import com.restful.api.model.Project;
import com.restful.api.repository.ProjectRepository;
import com.restful.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public void deleteProjectById(int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));
        projectRepository.deleteProjectById(projectId);
    }

    @Override
    public void createProject(Project projectDetails) {
        Project project = new Project();
        project.setDesc(projectDetails.getDesc());
        project.setName(projectDetails.getName());
        project.setPlannedEndDate(projectDetails.getPlannedEndDate());
        project.setPlannedStartDate(projectDetails.getPlannedStartDate());
        project.setActualStartDate(projectDetails.getActualStartDate());
        project.setPlannedEndDate(projectDetails.getActualEndDate());
        project.setStatus("NOT-START");

        projectRepository.save(project);
    }

    @Override
    public void updateProject(Project projectDetails) {
        int projectId = projectDetails.getId();
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));

        project.setName(projectDetails.getName());
        project.setDesc(projectDetails.getDesc());
        project.setStatus(projectDetails.getStatus());
        project.setPlannedStartDate(projectDetails.getPlannedStartDate());
        project.setPlannedEndDate(projectDetails.getPlannedEndDate());
        project.setActualStartDate(projectDetails.getActualStartDate());
        project.setActualEndDate(projectDetails.getActualEndDate());

        projectRepository.save(project);
    }

    @Override
    public int countNumberOfProjectByEmployeeId(int employee_id) {

        return  projectRepository.countNumberOfProjectByEmployeeId(employee_id);
    }

    @Override
    public void updateProjectStatusById(int projectId) {
        String current_projectStatus = projectRepository.findProjectStatusById(projectId)
                .orElseThrow(()->new ResourceNotFoundException("Project","project_id",projectId));
        String update_projectStatus = "";
        if(current_projectStatus == "NOT-START" || current_projectStatus == "CLOSED") {
            update_projectStatus = "ON-GOING";
            projectRepository.updateProjectStatusById(update_projectStatus, projectId);
        }
         else if(current_projectStatus == "ON-GOING"){
             update_projectStatus = "DONE";
             projectRepository.updateProjectStatusById(update_projectStatus,projectId);
        }else{
            update_projectStatus = "CLOSED";
            projectRepository.updateProjectStatusById(update_projectStatus,projectId);
        }
    }

    @Override
    public List<Project> findAllProjectByEmployeeId(int employee_id) {

        return projectRepository.findAllProjectByEmployeeId(employee_id);
    }

    @Override
    public int getTotalNumberMemberInProjectByProjectId(int project_id) {

        return projectRepository.getTotalNumberMemberInProjectByProjectId(project_id);
    }

}
