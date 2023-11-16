package com.example.filewidthfixed.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class FileWidthFixedJobConfig {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Bean
    public Job fileWidthFixedJob(Step fileWidthFixedStep) {
        return jobBuilderFactory
                .get("fileWidthFixedJob")
                .start(fileWidthFixedStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
