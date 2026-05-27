package com.example.practiceteaching.modules.file.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 项目根目录/files/
     */
    private static final String FILE_PATH =
            System.getProperty("user.dir")
                    + "/files/";

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {

        // 原始文件名
        String originalFilename =
                file.getOriginalFilename();

        // 文件后缀
        String suffix =
                originalFilename.substring(
                        originalFilename.lastIndexOf(".")
                );

        // UUID文件名
        String fileName =
                UUID.randomUUID() + suffix;

        // 创建目录
        File dir = new File(FILE_PATH);

        if (!dir.exists()) {

            dir.mkdirs();
        }

        // 创建目标文件
        File dest =
                new File(FILE_PATH + fileName);

        // 上传文件
        file.transferTo(dest);

        // 返回访问路径
        return "http://localhost:8080/file/" + fileName;
    }

    /**
     * 文件下载
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(
            @PathVariable String fileName)
            throws Exception {

        // 文件路径
        String filePath = FILE_PATH + fileName;

        // 文件对象
        File file = new File(filePath);

        // 文件不存在
        if (!file.exists()) {

            return ResponseEntity.notFound().build();
        }

        // 读取文件
        Resource resource =
                new FileSystemResource(file);

        // 文件名编码
        String encodeFileName =
                URLEncoder.encode(
                        fileName,
                        StandardCharsets.UTF_8
                );

        // 返回下载响应
        return ResponseEntity.ok()

                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename="
                                + encodeFileName
                )

                .body(resource);
    }
}
