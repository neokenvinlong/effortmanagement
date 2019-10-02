package com.restful.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sub_task")
public class Sub_Task implements Serializable {

    @Id
    @Column(name = "sub_task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "status")
    @NotBlank
    private String status;

    @Column(name = "is_send")
    @NotBlank
    private Boolean is_send;

    @Column(name = "sub_calendar_effort")
    @NotBlank
    private Double sub_calendarEffort;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

   @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
           CascadeType.REFRESH })
   @JoinColumn(name = "task_id")
   @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
   private Task task;

//   private List<Note_Task> note_taskList;
//
//   private Effort_Employee effort_employee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIs_send() {
        return is_send;
    }

    public void setIs_send(Boolean is_send) {
        this.is_send = is_send;
    }

    public Double getSub_calendarEffort() {
        return sub_calendarEffort;
    }

    public void setSub_calendarEffort(Double sub_calendarEffort) {
        this.sub_calendarEffort = sub_calendarEffort;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

//    public List<Note_Task> getNote_taskList() {
//        return note_taskList;
//    }
//
//    public void setNote_taskList(List<Note_Task> note_taskList) {
//        this.note_taskList = note_taskList;
//    }
//
//    public Effort_Employee getEffort_employee() {
//        return effort_employee;
//    }
//
//    public void setEffort_employee(Effort_Employee effort_employee) {
//        this.effort_employee = effort_employee;
//    }
}
