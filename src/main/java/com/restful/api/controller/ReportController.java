package com.restful.api.controller;

import com.restful.api.dto.ReportDTO;
import com.restful.api.response.EffortResponse;
import com.restful.api.response.EmployeeResponse;
import com.restful.api.response.ReportResponse;
import com.restful.api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/report/task/{project_id}")
    public @ResponseBody List<ReportResponse> getEffortOfTaskForReport(@PathVariable(value = "project_id") int project_id){

        return reportService.getEffortOfTaskForReport(project_id);
    }

    @GetMapping("/report/employee")
    public @ResponseBody List<EffortResponse> getListEffortOfEmployeeForReport(@RequestBody ReportDTO reportDTO){

        return reportService.getListEffortOfEmployeeForReport(reportDTO.getEmployee_id());
    }
}
