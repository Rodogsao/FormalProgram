package com.example.practiceteaching.modules.resource.resource_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practiceteaching.modules.resource.resource_management.entity.Classroom;
import com.example.practiceteaching.modules.resource.resource_management.mapper.ClassroomMapper;
import com.example.practiceteaching.modules.resource.resource_management.service.ClassroomService;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom>
        implements ClassroomService {
}