package com.example.practiceteaching.modules.resource.resource_management.service;

import com.example.practiceteaching.modules.resource.resource_management.entity.ResourceAllocate;

public interface ResourceAllocateService {
    void allocate(ResourceAllocate allocate, String operator);
    void updateAllocate(ResourceAllocate allocate, String operator);
    void deleteAllocate(Long id, String operator);
}