package com.example.test.image;

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
import java.util.UUID;

@Component
public class S3ImageUploader implements ImageUploader {

    private String bucketName;

    private Region region;

    private S3Client getClient() {

        return S3Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(region)
                .build();

    }

    @Override
    public String upload(MultipartFile multipartFile, UUID uuid) throws IOException, S3Exception {

        S3Client s3 = getClient();
        String fileName = multipartFile.getOriginalFilename();
        String key = createKey(uuid, fileName);
        byte[] bytes = multipartFile.getBytes();

        PutObjectRequest putOb = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3.putObject(putOb, RequestBody.fromBytes(bytes));
        return getUrl(s3, uuid, fileName);

    }

    private String getUrl(S3Client s3, UUID uuid, String fileName) throws S3Exception {

        String key = createKey(uuid, fileName);

        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        URL url = s3.utilities().getUrl(request);
        return url.toString();

    }

    private String createKey(UUID uuid, String fileName) {

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
