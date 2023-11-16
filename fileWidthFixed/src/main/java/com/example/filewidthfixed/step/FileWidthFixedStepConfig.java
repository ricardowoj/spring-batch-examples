package com.example.filewidthfixed.step;

import com.example.filewidthfixed.domain.ClientDTO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileWidthFixedStepConfig {
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step fileWidthFixedStep(ItemReader<ClientDTO> fileWidthFixedReader, ItemWriter<ClientDTO> fileWidthFixedWriter) {
        return stepBuilderFactory
                .get("fileWidthFixedStep")
                .<ClientDTO, ClientDTO>chunk(1)
                .reader(fileWidthFixedReader)
                .writer(fileWidthFixedWriter)
                .build();
    }
}
