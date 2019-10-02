package com.restful.api.dto;

import java.io.Serializable;
import java.util.Date;

public class Note_TaskDTO implements Serializable {
    private Integer note_task_id;
    private String title;
    private String content;
    private Integer employee_id;
    private Integer sub_task_id;
    private String name;
    private Date sendTime;
    private Date receiveTime;

    public Integer getNote_task_id() {
        return note_task_id;
    }

    public void setNote_task_id(Integer note_task_id) {
        this.note_task_id = note_task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getSub_task_id() {
        return sub_task_id;
    }

    public void setSub_task_id(Integer sub_task_id) {
        this.sub_task_id = sub_task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
