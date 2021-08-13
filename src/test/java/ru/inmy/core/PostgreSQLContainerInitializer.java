package ru.inmy.core;

import org.junit.ClassRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class PostgreSQLContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @ClassRule
    @Container
    public static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
            .withDatabaseName("testTimetable")
            .withUsername("testTimetable")
            .withPassword("testTimetable");

    static {
        postgreSQLContainer.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                "spring.r2dbc.url=" + postgreSQLContainer.getJdbcUrl().replace("jdbc", "r2dbc"),
//                "spring.r2dbc.url=r2dbc:tc:postgresql:///" + postgreSQLContainer.getDatabaseName() + "?TC_IMAGE_TAG=9.6.8",
                "spring.r2dbc.username=" + postgreSQLContainer.getUsername(),
                "spring.r2dbc.password=" + postgreSQLContainer.getPassword()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
