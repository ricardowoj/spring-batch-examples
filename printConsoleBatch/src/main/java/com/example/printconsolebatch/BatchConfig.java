package com.example.printconsolebatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Job printHelloSpringBatchJob() {
        return jobBuilderFactory
                .get("printHelloJob")
                .start(printHelloStep())
                .build();
    }
    
    public Step printHelloStep() {
        return stepBuilderFactory
                .get("printHelloStep")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("Hello Spring Batch!!!");
                    return null;
                }).build();
    }
}
