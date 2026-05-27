package com.example.practiceteaching.modules.teaching.teaching_resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.practiceteaching.modules.teaching.teaching_resource.entity.TeachingResource;

import java.util.List;

public interface TeachingResourceService {

    List<TeachingResource> list();

    void save(TeachingResource resource);

    void delete(Long id);

    void update(TeachingResource resource);

    IPage<TeachingResource> page(
            Integer pageNum,
            Integer pageSize,
            String resourceName,
            String courseName,
            String resourceType
    );

    TeachingResource getById(Long id);

}