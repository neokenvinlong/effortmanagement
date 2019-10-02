package com.restful.api.service;

import com.restful.api.dto.EffortDTO;

public interface EffortService {
    void updateApproveById(int id);
    void updateEffortById(EffortDTO effortDTO);
    void createEffort(EffortDTO effortDTO);
}
