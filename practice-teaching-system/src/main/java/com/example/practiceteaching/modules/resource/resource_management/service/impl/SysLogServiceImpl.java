package com.example.practiceteaching.modules.resource.resource_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practiceteaching.modules.resource.resource_management.entity.SysLog;
import com.example.practiceteaching.modules.resource.resource_management.mapper.SysLogMapper;
import com.example.practiceteaching.modules.resource.resource_management.service.SysLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
        implements SysLogService {
}