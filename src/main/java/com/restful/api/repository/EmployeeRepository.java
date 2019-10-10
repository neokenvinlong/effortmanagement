package com.restful.api.repository;

import com.restful.api.dto.EmployeeDTO;
import com.restful.api.model.Employee;
import com.restful.api.response.EmployeeInProjectResponse;
import com.restful.api.response.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//          ----------          Get Info Of Employee          ----------
//                  --------------   Get Info To Update   --------------
    @Query(value = "Select e.employee_id, e.name, e.email, e.phone, e.account_name, e.skill" +
            " From account as a, employee as e Where e.account_name = a.name AND a.name = :keyword", nativeQuery = true)
    Optional<Employee> getInfoOfSingleEmployeeByAccountName(@Param("keyword") String account_name);

    @Query(value = "Select employee_id, name, email, phone, account_name, skill" +
            " From employee Where name = :keyword", nativeQuery = true)
    Optional<Employee> getInfoOfSingleEmployeeByName(@Param("keyword") String name); // Co kha nang trung name

//                  --------------   Get Info To Show   --------------
            //          ------------   SEARCH   ------------------
//    @Query(value = "Select name, employee_id, account_name, phone, email" +
//            " From employee as e, project_employee as pe, project as e" +
//            " Where name LIKE ' || :keyword || ' AND e.employee_id = pe.employee_id AND pe.project_id = p.project_id" +
//            " AND p.project_id = :id", nativeQuery = true)
//    List<EmployeeDTO> getListNameOfEmployeeByNameAndByProjectId(@Param(value = "keyword") String name, @Param(value = "id") int project_id);

    @Query(value = "Select e.name, e.employee_id, e.account_name, e.phone, e.email, e.skill" +
            " From employee as e, project_employee as pe, project as p, account as a" +
            " Where e.employee_id = pe.employee_id AND pe.project_id = p.project_id" +
            " AND p.project_id = :id" +
            " AND a.role = :role", nativeQuery = true)
    List<Employee> getListNameEmployeeByProjectIdAndByRole(@Param("id") int project_id,
                                                                   @Param("role") String role);

    @Query(value = "Select e.name, e.employee_id" +
            " From employee as e, project_employee as pe, project as p, account as a" +
            " Where e.employee_id = pe.employee_id AND pe.project_id = p.project_id" +
            " AND p.project_id = :id", nativeQuery = true)
    List<EmployeeInProjectResponse> getListNameEmployeeByProjectId(@Param("id") int project_id);

//          ----------          Count Number Member In Project          ----------

    @Query(value = "Select count(pe.employee_id) from employee as e, project_employee as pe, project as p" +
            " Where e.employee_id = pe.employee_id AND pe.project_id = p.project_id" +
            " AND p.project_id = :id",nativeQuery = true)
    int countNumberMemberInAProject(@Param("id") int project_id);

    @Query(value = "Select count(pe.employee_id) from employee as e, project_employee as pe, project as p, account as a" +
            " Where e.employee_id = pe.employee_id AND pe.project_id = p.project_id AND a.name = e.account_name" +
            " AND p.project_id = :id AND a.role = :role ",nativeQuery = true)
    int countNumberMemberInAProjectByRole(@Param("id") int project_id, @Param("role") String role);

//          ----------          Delete Member           ----------
//    @Query(value = "Update Project_Employee Set Status='False' Where employee_id = :id AND project_id = :project_id",nativeQuery = true)
//    void deleteEmployeeInProjectById(@Param("id") int employee_id, @Param("project_id") int project_id);

}
