package com.restful.api.service;

import com.restful.api.response.EffortResponse;
import com.restful.api.response.ReportResponse;

import java.util.List;

public interface ReportService {
    List<ReportResponse> getEffortOfTaskForReport(int project_id);
    List<EffortResponse> getListEffortOfEmployeeForReport(int project_id, int emp_id);
}
