package com.example.test.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ImageUploader {

    public String upload(MultipartFile multipartFile, UUID uuid) throws IOException;

    public void delete(UUID uuid, String fileName);

}
