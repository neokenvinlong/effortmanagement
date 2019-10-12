package com.restful.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinColumn(name = "account_name")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Account accountName;

    @Column(name = "skill")
    private String skill;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccountName() {
        return accountName;
    }

    public void setAccountName(Account accountName) {
        this.accountName = accountName;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

//    public List<Project_Employee> getProject_employeeList() {
//        return project_employeeList;
//    }
//
//    public void setProject_employeeList(List<Project_Employee> project_employeeList) {
//        this.project_employeeList = project_employeeList;
  //  }
//
//    public List<Sub_Task> getSub_taskList() {
//        return sub_taskList;
//    }
//
//    public void setSub_taskList(List<Sub_Task> sub_taskList) {
//        this.sub_taskList = sub_taskList;
//    }
//
//    public List<Note_Task> getNote_taskList() {
//        return note_taskList;
//    }
//
//    public void setNote_taskList(List<Note_Task> note_taskList) {
//        this.note_taskList = note_taskList;
//    }
//
//    public List<Effort_Employee> getEffort_employeeList() {
//        return effort_employeeList;
//    }
//
//    public void setEffort_employeeList(List<Effort_Employee> effort_employeeList) {
//        this.effort_employeeList = effort_employeeList;
   // }
}
