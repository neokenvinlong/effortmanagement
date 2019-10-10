package com.restful.api.dto;

import java.io.Serializable;
import java.util.Date;

public class TaskDTO implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private String status;
    private Date endDate;
    private Double calendarEffort;
    private Integer project_id;
    private Integer emp_id;

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getCalendarEffort() {
        return calendarEffort;
    }

    public void setCalendarEffort(Double calendarEffort) {
        this.calendarEffort = calendarEffort;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }
}
