package com.example.practiceteaching.modules.resource.resource_management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.practiceteaching.common.result.Result;
import com.example.practiceteaching.modules.resource.resource_management.entity.Dormitory;
import com.example.practiceteaching.modules.resource.resource_management.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;

    @GetMapping("/list")
    public Result list() {
        return Result.success(dormitoryService.list());
    }

    @PostMapping("/add")
    public Result add(@RequestBody Dormitory dormitory) {
        return Result.success(dormitoryService.save(dormitory));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Dormitory dormitory) {
        return Result.success(dormitoryService.updateById(dormitory));
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return Result.success(dormitoryService.removeById(id));
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name
    ) {
        IPage<Dormitory> iPage = new Page<>(page, pageSize);
        IPage<Dormitory> result = dormitoryService.page(iPage,
                Wrappers.<Dormitory>lambdaQuery()
                        .like(name != null && !name.isEmpty(), Dormitory::getName, name)
        );
        return Result.success(result);
    }
}