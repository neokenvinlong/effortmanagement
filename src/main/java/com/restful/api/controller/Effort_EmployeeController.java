package com.restful.api.controller;

import com.restful.api.dto.EffortDTO;
import com.restful.api.service.EffortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/efforts")
public class Effort_EmployeeController {

    @Autowired
    EffortService effortService;

    @PatchMapping("/effort/approve/{id}")
    @Secured("ROLE_PM")
    public @ResponseBody void updateApproveById(@PathVariable(value = "id") int id){
        effortService.updateApproveById(id);
    }

    @PutMapping("effort/{id}")
    @Secured("ROLE_EMPLOYEE")
    public @ResponseBody void updateEffortById(@RequestBody EffortDTO effortDTO){
        effortService.updateEffortById(effortDTO);
    }

    @PostMapping
    @Secured("ROLE_EMPLOYEE")
    public @ResponseBody void createEffort(@RequestBody EffortDTO effortDTO){
        effortService.createEffort(effortDTO);
    }
}
