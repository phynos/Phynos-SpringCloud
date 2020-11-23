package com.phynos.charger.product.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio自动配置
 * @author by lupc
 * @date 2020-09-18 15:01
 */
@Configuration
@ConditionalOnClass(MinioTemplate.class)
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfiguration {

    private final MinioProperties minioProperties;

    public MinioAutoConfiguration(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Bean
    public MinioTemplate minioTemplate() {
        MinioTemplate minioTemplate = new MinioTemplate(minioProperties);
        return minioTemplate;
    }

}
