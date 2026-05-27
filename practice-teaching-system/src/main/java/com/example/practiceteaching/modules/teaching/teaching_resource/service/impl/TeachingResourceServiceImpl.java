package com.example.practiceteaching.modules.teaching.teaching_resource.service.impl;

import com.example.practiceteaching.modules.teaching.teaching_resource.entity.TeachingResource;
import com.example.practiceteaching.modules.teaching.teaching_resource.mapper.TeachingResourceMapper;
import com.example.practiceteaching.modules.teaching.teaching_resource.service.TeachingResourceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

@Service
public class TeachingResourceServiceImpl
        implements TeachingResourceService {

    @Resource
    private TeachingResourceMapper teachingResourceMapper;

    @Override
    public List<TeachingResource> list() {

        return teachingResourceMapper.selectList(null);
    }

    @Override
    public void save(TeachingResource resource) {

        teachingResourceMapper.insert(resource);
    }

    @Override
    public void update(TeachingResource resource) {

        teachingResourceMapper.updateById(resource);
    }
    @Override
    public void delete(Long id) {

        teachingResourceMapper.deleteById(id);
    }

    @Override
    public IPage<TeachingResource> page(
            Integer pageNum,
            Integer pageSize,
            String resourceName,
            String courseName,
            String resourceType
    ) {

        Page<TeachingResource> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<TeachingResource> wrapper =
                new LambdaQueryWrapper<>();

        // 资源名称模糊查询
        wrapper.like(
                resourceName != null && !resourceName.isEmpty(),
                TeachingResource::getResourceName,
                resourceName
        );

        // 课程名称模糊查询
        wrapper.like(
                courseName != null && !courseName.isEmpty(),
                TeachingResource::getCourseName,
                courseName
        );

        // 资源类型精确查询
        wrapper.eq(
                resourceType != null && !resourceType.isEmpty(),
                TeachingResource::getResourceType,
                resourceType
        );

        // 按上传时间倒序
        wrapper.orderByDesc(TeachingResource::getUploadTime);

        return teachingResourceMapper.selectPage(page, wrapper);
    }

    @Override
    public TeachingResource getById(Long id) {

        return teachingResourceMapper.selectById(id);
    }

}
