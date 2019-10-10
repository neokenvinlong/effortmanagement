package com.restful.api.repository;

import com.restful.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE name LIKE '% || :keyword || %' AND pm_id = :id", nativeQuery = true)
//    public List<Project> findProjectByNameAndByPmId(@Param("keyword") String projectName, @Param("id") int pm_id);
//
//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE status = :keyword AND pm_id = :id", nativeQuery = true)
//    public List<Project> findProjectByStatusAndByPmId(@Param("keyword") String projectStatus, @Param("id") int pm_id);
//
//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE project_id = :keyword  AND pm_id = :id", nativeQuery = true)
//    public List<Project> findProjectByIdAndByPmId(@Param("keyword") int project_id, @Param("id") int pm_id);

//    @Query(value = "Update project Set status = 'CANCEL'" +
//            " FROM project WHERE project_id = :keyword", nativeQuery = true)
//    void deleteProjectById(@Param("keyword") int projectId);
//
//    @Query(value = "Update project Set status = :status" +
//            " FROM project WHERE project_id = :keyword", nativeQuery = true)
//    void updateProjectStatusById(@Param("status") String status, @Param("keyword") int projectId);
//
//    @Query(value = "SELECT status"+
//            " FROM project WHERE project_id = :keyword", nativeQuery = true)
//    Optional<String> findProjectStatusById(@Param("keyword") int projectId);

    @Query(value = "SELECT p.project_id, p.name, p.status" +
            " FROM project as p, project_employee as pe, employee as e" +
            " WHERE e.employee_id = pe.employee_id AND pe.project_id = p.project_id" +
            " AND e.employee_id = :id AND p.status != 'CANCEL' AND pe.status = true", nativeQuery = true)
    List<Project> findAllProjectByEmployeeId(@Param("id") int employee_id);

    @Query(value = "Select count(pe.project_id) From project_employee as pe, employee as e, project as p " +
            "Where pe.employee_id = e.employee_id And e.employee_id = :keyword" +
            " AND p.project_id = pe.project_id" +
            " AND p.status != 'CANCEL' AND pe.status = true", nativeQuery = true)
    int countNumberOfProjectByEmployeeId(@Param("keyword") int employee_id);

//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE planned_start_date >= :startDate AND planned_start_date <= :endDate AND pm_id = :id", nativeQuery = true)
//    public List<Project> findAllProjectBasedOnPlannedStartDateAndByPmId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") int pm_id);
//
//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE planned_end_date >= :startDate AND planned_end_date <= :endDate AND pm_id = :id", nativeQuery = true)
//    public List<Project> findAllProjectBasedOnPlannedEndDateAndPmId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") int pm_id);
//
//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE actual_start_date >= :startDate AND actual_start_date <= :endDate AND pm_id = :id", nativeQuery = true)
//    public List<Project> findAllProjectBasedOnActualStartDateAndPmId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") int pm_id);
//
//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE actual_end_date >= :startDate AND actual_end_date <= :endDate AND pm_id = :id", nativeQuery = true)
//    public List<Project> findAllProjectBasedOnActualEndDateAndPmId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") int pm_id);
//
//    @Query(value = "SELECT project_id, name, description, status, created_at, planned_start_date, " +
//            "planned_end_date, actual_start_date, actual_end_date" +
//            " FROM project WHERE created_at >= :startDate AND created_at <= :endDate AND pm_id = :id", nativeQuery = true)
//    public List<Project> findAllProjectBasedOnCreateDateAndPmId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") int pm_id);

    @Query(value = "Select count(pe.employee_id) " +
            "from Project as p, Employee as e, project_employee as pe" +
            " Where e.employee_id=pe.employee_id AND pe.project_id = p.project_id" +
            " AND p.project_id = :id AND pe.status = true" +
            " AND p.status != 'CANCEL'",nativeQuery = true)
    int getTotalNumberMemberInProjectByProjectId(@Param("id") int project_id);
}
