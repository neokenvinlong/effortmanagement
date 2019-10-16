package com.restful.api.controller;

import com.restful.api.dto.EffortDTO;
import com.restful.api.response.EffortResponse;
import com.restful.api.service.EffortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/efforts")
public class Effort_EmployeeController {

    @Autowired
    EffortService effortService;

    @PatchMapping("/effort/approve/{id}")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody void updateApproveById(@PathVariable(value = "id") int id){
        effortService.updateApproveById(id);
    }

    @PutMapping("/effort")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public @ResponseBody void updateEffortById(@RequestBody EffortDTO effortDTO){
        effortService.updateEffortByTaskId(effortDTO);
    }

    @PostMapping("/effort")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public @ResponseBody void createEffort(@RequestBody EffortDTO effortDTO){
        effortService.createEffort(effortDTO);
    }

    @GetMapping("/effort/approve/project")
    @PreAuthorize("hasRole('ROLE_PM')")
    public @ResponseBody List<EffortResponse> getListEffortWaitingApprove(){
        return effortService.getListEffortWaitingApprove();
    }

    @GetMapping("/effort")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public @ResponseBody int getEffortIdByTaskIdAndEmpId(@RequestBody EffortDTO effortDTO){

        return effortService.getEffortIdByTaskIdAndEmpId(effortDTO.getEmp_id(), effortDTO.getTask_id());
    }
}
