package com.kumaran.coursemanagement.service.impl;

import com.kumaran.coursemanagement.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) {

        try {

            File folder = new File(uploadDir);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = System.currentTimeMillis()
                    + "_"
                    + file.getOriginalFilename();

            String filePath = uploadDir
                    + File.separator
                    + fileName;

            File destinationFile = new File(filePath);

            file.transferTo(destinationFile);

            return fileName;

        } catch (IOException e) {

            e.printStackTrace();

            throw new RuntimeException("File upload failed : " + e.getMessage(), e);
        }
    }
}