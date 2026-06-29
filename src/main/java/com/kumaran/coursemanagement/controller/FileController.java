package com.kumaran.coursemanagement.controller;

import com.kumaran.coursemanagement.response.ApiResponse;
import com.kumaran.coursemanagement.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<String>> uploadFile(
            @RequestPart("file") MultipartFile file) {

        String fileName = service.uploadFile(file);

        ApiResponse<String> response =
                new ApiResponse<>(true,
                        "File uploaded successfully",
                        fileName);

        return ResponseEntity.ok(response);
    }
}