package com.resource.controller;

import com.resource.common.Result;
import com.resource.entity.Classroom;
import com.resource.service.ClassroomService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    // 原来的全部接口不动
    @GetMapping("/list")
    public Result list() {
        return Result.success(classroomService.list());
    }

    // 新增：分页 + 模糊查询（给你的管理页面用）
    @GetMapping("/page")
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name
    ) {
        IPage<Classroom> iPage = new Page<>(page, pageSize);
        IPage<Classroom> result = classroomService.page(iPage,
                Wrappers.<Classroom>lambdaQuery()
                        .like(name != null && !name.isEmpty(), Classroom::getName, name)
        );
        return Result.success(result);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Classroom classroom) {
        return Result.success(classroomService.save(classroom));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Classroom classroom) {
        return Result.success(classroomService.updateById(classroom));
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return Result.success(classroomService.removeById(id));
    }
}