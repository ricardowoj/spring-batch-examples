package com.example.printconsolebatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
                .get("printHelloStep")
                .start(printHelloStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }
    
    public Step printHelloStep() {
        return stepBuilderFactory
                .get("printHelloStep")
                .tasklet(getTasklet()).build();
    }

    @Bean
    @StepScope
    public static Tasklet getTasklet() {
        return (stepContribution, chunkContext) -> {
            System.out.println(String.format("Hello %s!!!", System.getProperty("name")));
            return null;
        };
    }
}
