package ru.inmy.core;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.Objects;

import static ru.inmy.core.PostgreSQLContainerInitializer.postgreSQLContainer;

@TestConfiguration
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(postgreSQLContainer.getJdbcUrl())
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword())
                .build();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) throws SQLException {
        tryToCreateSchema(dataSource);
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDropFirst(true);
        liquibase.setDataSource(dataSource);
        liquibase.setDefaultSchema("public");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("db/liquibase-changelog.xml")).getFile());
        liquibase.setChangeLog("file:" + file.getAbsolutePath());
        return liquibase;
    }

    private void tryToCreateSchema(DataSource dataSource) throws SQLException {
        String CREATE_SCHEMA_QUERY = "CREATE SCHEMA IF NOT EXISTS public";
        dataSource.getConnection().createStatement().execute(CREATE_SCHEMA_QUERY);
    }
}
