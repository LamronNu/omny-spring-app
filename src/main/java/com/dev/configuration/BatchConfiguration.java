package com.dev.configuration;

import com.dev.batch.UserProcessor;
import com.dev.batch.UserWriter;
import com.dev.model.BatchJob;
import com.dev.model.User;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * @author OlgaPrylypko
 *         date: 02.01.2016
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private static final Logger LOG = Logger.getLogger(BatchConfiguration.class);

    // tag::readerwriterprocessor[]
    @Bean
    public ItemReader<BatchJob> reader() {
        FlatFileItemReader<BatchJob> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("csv/created-days.csv"));
        reader.setLineMapper(new DefaultLineMapper<BatchJob>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "days" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<BatchJob>() {{
                setTargetType(BatchJob.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<BatchJob, List<User>> processor() {
        return new UserProcessor();
    }

    @Bean
    public ItemWriter<List<User>> writer() {
        return new UserWriter();
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("downloadUserInfo")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<BatchJob> reader,
            ItemWriter<List<User>> writer, ItemProcessor<BatchJob, List<User>> processor) {
        return stepBuilderFactory.get("step1")
                .<BatchJob, List<User>>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    // end::jobstep[]

}
