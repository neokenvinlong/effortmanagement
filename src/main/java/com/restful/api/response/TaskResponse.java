package com.restful.api.response;

import java.util.Date;

public interface TaskResponse {
     Integer getTask_id() ;

     String getTitle();

     String getDescription() ;

     String getStatus() ;

     Date getEnd_date();

     Date getCreated_date() ;

     Double getCalendar_effort();
}
