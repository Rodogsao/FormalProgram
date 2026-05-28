package com.example.practiceteaching.modules.resource.resource_management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.practiceteaching.common.result.Result;
import com.example.practiceteaching.modules.resource.resource_management.entity.Teacher;
import com.example.practiceteaching.modules.resource.resource_management.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public Result list() {
        return Result.success(teacherService.list());
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        return Result.success(teacherService.save(teacher));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Teacher teacher) {
        return Result.success(teacherService.updateById(teacher));
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return Result.success(teacherService.removeById(id));
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name
    ) {
        IPage<Teacher> iPage = new Page<>(page, pageSize);
        IPage<Teacher> result = teacherService.page(iPage,
                Wrappers.<Teacher>lambdaQuery()
                        .like(name != null && !name.isEmpty(), Teacher::getName, name)
        );
        return Result.success(result);
    }
}