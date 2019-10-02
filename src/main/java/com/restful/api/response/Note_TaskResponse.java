package com.restful.api.response;

import java.util.Date;

public interface Note_TaskResponse {
    Integer getNote_task_id() ;

    String getTitle();

    String getContent();

    Integer getEmployee_id();

    String getName();

    Date getSendTime();

    Date getReceiveTime();
}
