package com.example.test.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TestImageUploader implements ImageUploader {
    @Override
    public String uploadImage(MultipartFile multipartFile, UUID uuid) throws IOException {

        return multipartFile.getOriginalFilename();

    }

    @Override
    public List<String> uploadImages(List<MultipartFile> multipartFiles, UUID uuid) throws IOException {

        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {

            urls.add(multipartFile.getOriginalFilename());

        }

        return urls;

    }

    @Override
    public void delete(UUID uuid, String fileName) {

    }
}
