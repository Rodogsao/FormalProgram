package com.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.resource.entity.AllocateRecord;
import com.resource.mapper.AllocateRecordMapper;
import com.resource.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import java.net.URLEncoder;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/allocateRecord")
@RequiredArgsConstructor
public class AllocateRecordController {

    private final AllocateRecordMapper allocateRecordMapper;

    // 列表 + 模糊查询
    @GetMapping("/list")
    public Result list(
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String type
    ) {
        QueryWrapper<AllocateRecord> wrapper = new QueryWrapper<>();

        // 模糊查询操作对象或操作内容
        if (content != null && !content.isEmpty()) {
            wrapper.and(w -> w.like("content", content).or().like("target_object", content));
        }

        List<AllocateRecord> list = allocateRecordMapper.selectList(wrapper);
        return Result.success(list);
    }

    // Excel 导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("分配记录", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        List<AllocateRecord> list = allocateRecordMapper.selectList(null);
        EasyExcel.write(response.getOutputStream(), AllocateRecord.class)
                .sheet("分配记录")
                .doWrite(list);
    }

    // Excel 导入
    @PostMapping("/import")
    public Result importExcel(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), AllocateRecord.class, new PageReadListener<AllocateRecord>(dataList -> {
            for (AllocateRecord record : dataList) {
                // 如果 ID 已存在可能会冲突，视情况可将 id 设为 null 让自增生效
                record.setId(null);
                allocateRecordMapper.insert(record);
            }
        })).sheet().doRead();
        return Result.success("导入成功");
    }
}