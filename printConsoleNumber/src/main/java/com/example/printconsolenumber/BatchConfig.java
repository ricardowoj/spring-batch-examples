package com.example.printconsolenumber;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job printNumberJob() {
        return jobBuilderFactory
                .get("printNumberJob")
                .start(printNumberStep())
                .incrementer(new RunIdIncrementer())
                .build();
                
    }

    private Step printNumberStep() {
        return stepBuilderFactory
                .get("printNumberStep")
                .<Integer, String>chunk(100)
                .reader(countNumberReader())
                .processor(pairOrOdProcessor())
                .writer(printWriter())
                .build();
    }

    public ItemReader<Integer> countNumberReader() {
        Random rand = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            boolean numberValidation = true;
            while(numberValidation) {
                int numberRand = rand.nextInt();
                if(numberRand > 0) {
                    numberValidation = false;
                    numbers.add(numberRand);
                }
            }
        }
        return new IteratorItemReader<>(numbers);
    }

    private FunctionItemProcessor<Integer, String> pairOrOdProcessor() {
        return new FunctionItemProcessor<>
                (item -> item % 2 == 0 ?
                        String.format("Item %s is Pair", item) : String.format("Item %s is Odd", item));
    }
    
    private ItemWriter<String> printWriter() {
        return items -> items.forEach(System.out::println);
    }
}
