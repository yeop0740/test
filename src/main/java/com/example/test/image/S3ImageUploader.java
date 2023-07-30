package com.example.test.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class S3ImageUploader implements ImageUploader {

    private final S3Client s3;

    private String bucketName;

    private Region region;

    private S3Client getClient() {

        return S3Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(region)
                .build();

    }

    @Override
    public String uploadImage(MultipartFile multipartFile, UUID uuid) throws IOException, S3Exception {

        String fileName = multipartFile.getOriginalFilename();
        String key = createKey(uuid, fileName);
        byte[] bytes = multipartFile.getBytes();

        PutObjectRequest putOb = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3.putObject(putOb, RequestBody.fromBytes(bytes));
        return getUrl(uuid, fileName);

    }

    @Override
    public List<String> uploadImages(List<MultipartFile> multipartFiles, UUID uuid) throws IOException {

        List<String> urls = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {

            urls.add(uploadImage(multipartFile, uuid));

        }

        return urls;

    }

    private String getUrl(UUID uuid, String fileName) throws S3Exception {

        String key = createKey(uuid, fileName);

        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        URL url = s3.utilities().getUrl(request);
        return url.toString();

    }

    @Override
    public String createKey(UUID uuid, String fileName) {

        return new StringBuffer()
                .append(uuid.toString())
                .append("/")
                .append(fileName)
                .toString();

    }

    @Override
    public void delete(UUID uuid, String fileName) {

        String key = createKey(uuid, fileName);

        S3Client s3 = getClient();
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3.deleteObject(deleteObjectRequest);

    }
}
