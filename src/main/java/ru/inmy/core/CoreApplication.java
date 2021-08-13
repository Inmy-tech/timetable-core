package ru.inmy.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        ReactorDebugAgent.init();
        SpringApplication.run(CoreApplication.class, args);
    }
}
