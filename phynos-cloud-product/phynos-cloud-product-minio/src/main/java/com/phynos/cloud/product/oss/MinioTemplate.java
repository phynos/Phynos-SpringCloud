package com.phynos.cloud.product.oss;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * minio操作模板类
 *
 * @author by lupc
 * @date 2020-09-18 15:23
 */
public class MinioTemplate {

    private final MinioClient client;

    public MinioTemplate(MinioProperties minioProperties) {
        this.client = MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

    public void createBucket(String bucketName) {
        boolean found = false;
        try {
            found = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException | RegionConflictException e) {
            e.printStackTrace();
        }
    }

    public List<Bucket> getAllBuckets() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        return client.listBuckets();
    }

    public Optional<Bucket> getBucket(String bucketName) {
        return null;
    }

    /**
     * 移除桶
     * @param bucketName 桶名称
     */
    public void removeBucket(String bucketName) {
        try {
            client.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (InvalidKeyException | NoSuchAlgorithmException | ErrorResponseException | InvalidResponseException | InvalidBucketNameException | ServerException | InsufficientDataException | XmlParserException | InternalException | IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {

    }

    public String getObjectURL(String bucketName, String objectName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        return client.getObjectUrl(bucketName, objectName);
    }

    /**
     * 获取对象数据
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @return
     */
    public InputStream getObject(String bucketName, String objectName) {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            return client.getObject(getObjectArgs);
        } catch (ErrorResponseException | NoSuchAlgorithmException | ServerException | XmlParserException | IOException | InvalidResponseException | InvalidKeyException | InvalidBucketNameException | InternalException | InsufficientDataException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储对象
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param stream 输入数据流
     * @param size 数据流大小
     * @param contextType 类型
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, -1)
                .contentType(contextType)
                .build();
        try {
            client.putObject(putObjectArgs);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }

    public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
        return null;
    }

    /**
     * 移除对象
     * @param bucketName 桶名称
     * @param objectName 对象名称
     */
    public void removeObject(String bucketName, String objectName) {
        try {
            client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (InvalidKeyException | NoSuchAlgorithmException | ErrorResponseException | InvalidResponseException | InvalidBucketNameException | ServerException | InsufficientDataException | XmlParserException | InternalException | IOException e) {
            e.printStackTrace();
        }
    }

}
