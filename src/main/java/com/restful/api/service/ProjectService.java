package com.restful.api.service;

import com.restful.api.model.Project;

import java.util.List;

public interface ProjectService {
  //  void deleteProjectById(int project_id);
   // void updateProjectStatusById(int project_id);
//    String findProjectStatusById(int project_id);
    List<Project> findAllProjectByEmployeeId(int employee_id);
    int countNumberOfProjectByEmployeeId(int employee_id);
    int getTotalNumberMemberInProjectByProjectId(int project_id);
   // void createProject(Project projectDetails);
    //void  updateProject(Project projectDetails);
}
