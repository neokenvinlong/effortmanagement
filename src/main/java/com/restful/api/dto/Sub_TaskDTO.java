package com.restful.api.dto;

import java.io.Serializable;

public class Sub_TaskDTO implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private String status;
    private Boolean is_send;
    private Double sub_calendarEffort;
    private Integer taskId;
    private Integer employee_id;
    private String name;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
