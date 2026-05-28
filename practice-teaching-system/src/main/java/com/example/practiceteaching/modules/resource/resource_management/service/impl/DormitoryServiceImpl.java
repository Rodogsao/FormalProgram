package com.example.practiceteaching.modules.resource.resource_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practiceteaching.modules.resource.resource_management.entity.Dormitory;
import com.example.practiceteaching.modules.resource.resource_management.mapper.DormitoryMapper;
import com.example.practiceteaching.modules.resource.resource_management.service.DormitoryService;
import org.springframework.stereotype.Service;

@Service
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory>
        implements DormitoryService {
}