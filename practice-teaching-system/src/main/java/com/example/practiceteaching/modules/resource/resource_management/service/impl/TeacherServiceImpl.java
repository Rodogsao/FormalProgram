package com.example.practiceteaching.modules.resource.resource_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practiceteaching.modules.resource.resource_management.entity.Teacher;
import com.example.practiceteaching.modules.resource.resource_management.mapper.TeacherMapper;
import com.example.practiceteaching.modules.resource.resource_management.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {
}