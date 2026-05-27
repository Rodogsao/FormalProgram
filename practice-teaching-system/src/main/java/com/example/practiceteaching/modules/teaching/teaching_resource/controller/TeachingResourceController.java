package com.example.practiceteaching.modules.teaching.teaching_resource.controller;

import com.example.practiceteaching.common.result.Result;
import com.example.practiceteaching.modules.teaching.teaching_resource.entity.TeachingResource;
import com.example.practiceteaching.modules.teaching.teaching_resource.service.TeachingResourceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.io.File;

@RestController
@RequestMapping("/teaching/resource")
public class TeachingResourceController {

    @Resource
    private TeachingResourceService teachingResourceService;

    /**
     * 查询全部资源
     */
    @GetMapping("/list")
    public Result<List<TeachingResource>> list(){

        return Result.success(
                teachingResourceService.list()
        );
    }

    /**
     * 新增资源
     */
    @PostMapping("/save")
    public Result<String> save(
            @RequestBody TeachingResource resource){

        teachingResourceService.save(resource);

        return Result.success("新增成功");
    }

    /**
     * 删除资源
     */
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(
            @PathVariable Long id){

        // 查询资源
        TeachingResource resource =
                teachingResourceService.getById(id);

        // 判断资源是否存在
        if(resource == null){

            return Result.error("资源不存在");
        }

        // 获取文件URL
        String fileUrl =
                resource.getFileUrl();

        // 截取文件名
        String fileName =
                fileUrl.substring(
                        fileUrl.lastIndexOf("/") + 1
                );

        // 文件路径
        String filePath =
                System.getProperty("user.dir")
                        + "/files/"
                        + fileName;

        // 创建文件对象
        File file = new File(filePath);

        // 删除物理文件
        if(file.exists()){

            file.delete();
        }

        // 删除数据库记录
        teachingResourceService.delete(id);

        return Result.success("删除成功");
    }

    /**
     * 修改资源
     */
    @PutMapping("/update")
    public Result<String> update(
            @RequestBody TeachingResource resource){

        teachingResourceService.update(resource);

        return Result.success("修改成功");
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<IPage<TeachingResource>> page(

            @RequestParam Integer pageNum,

            @RequestParam Integer pageSize,

            @RequestParam(required = false)
            String resourceName,

            @RequestParam(required = false)
            String courseName,

            @RequestParam(required = false)
            String resourceType
    ) {

        IPage<TeachingResource> page =
                teachingResourceService.page(
                        pageNum,
                        pageSize,
                        resourceName,
                        courseName,
                        resourceType
                );

        return Result.success(page);
    }
}