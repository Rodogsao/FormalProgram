package com.example.practiceteaching.modules.teaching.teaching_resource.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("resource")
public class TeachingResource {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 所属课程
     */
    private String courseName;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 上传用户
     */
    private String uploadUser;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}