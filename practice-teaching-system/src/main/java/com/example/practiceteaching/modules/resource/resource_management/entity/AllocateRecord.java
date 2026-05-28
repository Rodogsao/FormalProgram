package com.example.practiceteaching.modules.resource.resource_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import java.util.Date;

@Data
@ColumnWidth(20)
public class AllocateRecord {
    @TableId(type = IdType.AUTO)
    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("分配ID")
    private Long allocateId;

    @ExcelProperty("操作类型")
    private String operationType;

    @ExcelProperty("操作对象")
    @ColumnWidth(30)
    private String targetObject;

    @ExcelProperty("操作内容")
    @ColumnWidth(40)
    private String content;

    @ExcelProperty("操作人")
    private String operator;

    @ExcelProperty("操作时间")
    private Date createTime;
}