package com.phynos.charger.product.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * minio连接属性
 * @author by lupc
 * @date 2020-09-18 15:01
 */
@ConfigurationProperties(prefix = "spring.oss.minio")
public class MinioProperties {

    private String url;

    private String bucket;

    private String accessKey;

    private String secretKey;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
