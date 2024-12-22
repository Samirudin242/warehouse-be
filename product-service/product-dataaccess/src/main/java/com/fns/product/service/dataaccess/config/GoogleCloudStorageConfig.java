package com.fns.product.service.dataaccess.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleCloudStorageConfig {

    @Value("${google.cloud.storage.bucket-name}")
    private String bucketName;

    @Value("${google.cloud.storage.project-id}")
    private String projectId;

    @Bean
    public Storage googleStorage() {
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .build()
                .getService();
    }
}
