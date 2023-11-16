package com.example.filewidthfixed.writer;

import com.example.filewidthfixed.domain.ClientDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileWidthFixedWriterConfig {
    
    @Bean
    public ItemWriter<ClientDTO> fileWidthFixedWriter() {
        return items -> items.forEach(System.out::println);
    }
}
