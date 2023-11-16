package com.example.filewidthfixed.reader;

import com.example.filewidthfixed.domain.ClientDTO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

@Configuration
public class FileWidthFixedReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<ClientDTO> fileWidthFixedReader() {
        return new FlatFileItemReaderBuilder<ClientDTO>()
                .name("fileWidthFixedReader")
                .resource(getPathResource())
                .lineTokenizer(preparaTokeniezer())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ClientDTO>() {{
                    setTargetType(ClientDTO.class);
                }})
                .build();
    }

    private LineTokenizer preparaTokeniezer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter("|");
        tokenizer.setNames(prepareNames());
        return tokenizer;
    }

    private Resource getPathResource() {
        return new PathResource("/Users/ricardowojciechowski/Desktop/Study/" +
                "spring-batch-examples/fileWidthFixed/files/client.txt");
    }

    private String[] prepareNames() {
        return new String[]{"name", "lastName", "age", "email"};
    }
}
