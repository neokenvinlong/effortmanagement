package com.restful.api.service;

import com.restful.api.dto.EffortDTO;
import com.restful.api.response.EffortResponse;

import java.util.List;

public interface EffortService {
    void updateApproveById(int id);
    void updateEffortById(EffortDTO effortDTO);
    void createEffort(EffortDTO effortDTO);
    List<EffortResponse> getListEffortWaitingApprove(int project_id);
}
