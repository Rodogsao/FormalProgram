package com.example.practiceteaching.modules.resource.resource_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class SysLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String uri;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}