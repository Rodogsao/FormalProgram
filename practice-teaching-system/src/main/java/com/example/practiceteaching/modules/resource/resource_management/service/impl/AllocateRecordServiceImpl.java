package com.example.practiceteaching.modules.resource.resource_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practiceteaching.modules.resource.resource_management.entity.AllocateRecord;
import com.example.practiceteaching.modules.resource.resource_management.mapper.AllocateRecordMapper;
import com.example.practiceteaching.modules.resource.resource_management.service.AllocateRecordService;
import org.springframework.stereotype.Service;

@Service
public class AllocateRecordServiceImpl extends ServiceImpl<AllocateRecordMapper, AllocateRecord>
        implements AllocateRecordService {
}