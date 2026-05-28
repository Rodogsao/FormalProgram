package com.example.practiceteaching.modules.resource.resource_management.controller;

import com.example.practiceteaching.common.result.Result;
import com.example.practiceteaching.modules.resource.resource_management.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class SysLogController {
    @Autowired
    private SysLogService logService;

    @GetMapping("/list")
    public Result list(){
        return Result.success(logService.list());
    }
}