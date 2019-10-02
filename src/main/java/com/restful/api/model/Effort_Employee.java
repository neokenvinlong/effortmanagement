package com.restful.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "effort_employee")
public class Effort_Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "effort")
    @NotBlank
    private Double effort;

    @Column(name = "is_approved")
    @NotBlank
    private Boolean is_approved;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE,
            CascadeType.REFRESH })
    @JoinColumn(name = "sub_task_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sub_Task sub_task;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getEffort() {
        return effort;
    }

    public void setEffort(Double effort) {
        this.effort = effort;
    }

    public Boolean getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(Boolean is_approved) {
        this.is_approved = is_approved;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Sub_Task getSub_task() {
        return sub_task;
    }

    public void setSub_task(Sub_Task sub_task) {
        this.sub_task = sub_task;
    }
}
