package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.batch.common.FullReportListener;
import fr.training.spring.library.batch.exportjob.dto.LibraryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;

@Configuration
public class ExportJobConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ExportJobConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FullReportListener jobListener;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "exportJob1")
    public Job exportJob(final Step exportStepCsv, final Step exportStepJson) {
        return jobBuilderFactory.get("export-job1") //
                .incrementer(new RunIdIncrementer()) // job can be launched as many times as desired
                .start(exportStepCsv) //
                .next(exportStepJson)
                .listener(jobListener) //
                .build();
    }

    @Bean
    public Step exportStepJson(final JsonFileItemWriter<LibraryDto> exportWriterJson, final LibraryProcessor libraryProcessor) {
        return stepBuilderFactory.get("export-stepJson").<Long, LibraryDto>chunk(10) //
                .reader(exportReader())  //
                .processor(libraryProcessor) //
                .writer(exportWriterJson) //
                .build();
    }

    @Bean
    public Step exportStepCsv(final FlatFileItemWriter<LibraryDto> exportWriterCsv, final LibraryProcessor libraryProcessor) {
        return stepBuilderFactory.get("export-stepCsv").<Long, LibraryDto>chunk(10) //
                .reader(exportReader())  //
                .processor(libraryProcessor) //
                .writer(exportWriterCsv) //
                .build();
    }

    /**
     * ItemReader is an abstract representation of how data is provided as input to
     * a Step. When the inputs are exhausted, the ItemReader returns null.
     */
    @Bean
    public JdbcCursorItemReader<Long> exportReader() {
        final JdbcCursorItemReader<Long> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT id from Library");
        reader.setRowMapper(new SingleColumnRowMapper<Long>());
        return reader;
    }

    /**
     * ItemWriter is the output of a Step. The writer writes one batch or chunk of
     * items at a time to the target system. ItemWriter has no knowledge of the
     * input it will receive next, only the item that was passed in its current
     * invocation.
     */

    @Bean
    @StepScope
    public FlatFileItemWriter<LibraryDto> exportWriterCsv(
            @Value("#{jobParameters['output-file-csv']}") final String outputFile) {
        final FlatFileItemWriter<LibraryDto> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(outputFile));

        final DelimitedLineAggregator<LibraryDto> lineAggregator = new DelimitedLineAggregator<>();

        final BeanWrapperFieldExtractor<LibraryDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "type", "address", "director", "books"});
        lineAggregator.setFieldExtractor(fieldExtractor);
        lineAggregator.setDelimiter(";");

        writer.setLineAggregator(lineAggregator);
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(final Writer writer) throws IOException {
                writer.write("id;type;address;director;book");
            }
        });
        return writer;
    }

    /**
     * ItemWriter is the output of a Step. The writer writes one batch or chunk of
     * items at a time to the target system. ItemWriter has no knowledge of the
     * input it will receive next, only the item that was passed in its current
     * invocation.
     * @return
     */

    @Bean
    @StepScope
    public JsonFileItemWriter<LibraryDto> exportWriterJson(
            @Value("#{jobParameters['output-file-json']}") final String outputFile) {
        return new JsonFileItemWriterBuilder<LibraryDto>() //
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>()) //
                .resource(new FileSystemResource(outputFile)) //
                .name("LibraryJsonFileItemWriter") //
                .build();
    }

}
