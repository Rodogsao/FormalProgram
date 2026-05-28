package com.example.practiceteaching.modules.resource.resource_management.controller;

import com.example.practiceteaching.modules.resource.resource_management.entity.ResourceAllocate;
import com.example.practiceteaching.modules.resource.resource_management.service.ResourceAllocateService;
import com.example.practiceteaching.modules.resource.resource_management.mapper.ResourceAllocateMapper;
import com.example.practiceteaching.common.result.Result;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allocate")
@RequiredArgsConstructor
public class ResourceAllocateController {

    private final ResourceAllocateService resourceAllocateService;
    private final ResourceAllocateMapper resourceAllocateMapper;

    @GetMapping("/list")
    public Result list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Page<ResourceAllocate> pg = new Page<>(page, pageSize);
        return Result.success(resourceAllocateMapper.selectPage(pg, null));
    }

    @PostMapping("/save")
    public Result save(@RequestBody ResourceAllocate allocate) {
        resourceAllocateService.allocate(allocate, allocate.getOperator() != null ? allocate.getOperator() : "admin");
        return Result.success("分配成功");
    }

    @PostMapping("/update")
    public Result update(@RequestBody ResourceAllocate allocate) {
        resourceAllocateService.updateAllocate(allocate, "admin");
        return Result.success("修改成功");
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        resourceAllocateService.deleteAllocate(id, "admin");
        return Result.success("回收成功");
    }
}