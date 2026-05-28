package com.resource.controller;

import com.resource.common.Result;
import com.resource.service.SysLogService;
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