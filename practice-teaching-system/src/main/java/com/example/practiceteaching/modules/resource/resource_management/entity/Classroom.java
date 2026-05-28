package com.example.practiceteaching.modules.resource.resource_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Classroom {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer capacity;
    private String location;
    private Integer status;
    private Date createTime;
    private String operator;
    private LocalDateTime endTime;
}