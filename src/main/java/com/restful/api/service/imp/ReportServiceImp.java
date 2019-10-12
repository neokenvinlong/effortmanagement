package com.restful.api.service.imp;

import com.restful.api.repository.EffortRepository;
import com.restful.api.repository.TaskRepository;
import com.restful.api.response.EffortResponse;
import com.restful.api.response.ReportResponse;
import com.restful.api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImp implements ReportService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    EffortRepository effortRepository;

    @Override
    public List<ReportResponse> getEffortOfTaskForReport(int project_id) {

        return taskRepository.getEffortOfTaskForReport(project_id);
    }

    @Override
    public List<EffortResponse> getListEffortOfEmployeeForReport( int emp_id) {

        return effortRepository.getListEffortOfEmployeeForReport(emp_id);
    }
}
