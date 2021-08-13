//package ru.inmy.core.event;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//import ru.inmy.core.PostgreSQLContainerInitializer;
//import ru.inmy.core.TestConfig;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@DataR2dbcTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(initializers = {PostgreSQLContainerInitializer.class}, classes = {TestConfig.class})
//public class EventRepositoryTest {
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Test
//    void saveOneObject() {
//        LocalDateTime now = LocalDateTime.now();
//        Mono<Event> event = eventRepository.save(new Event(null, "Todo smth", now));
//
//        StepVerifier
//                .create(event)
//                .expectNextMatches(e ->
//                        e.getEventId() != null &&
//                                e.getEventName().equals("Todo smth") &&
//                                e.getEventDateTime().equals(now)
//                )
//                .verifyComplete();
//    }
//
//    @Test
//    void saveSeveralObjects() {
//        LocalDateTime now = LocalDateTime.now();
//        Flux<Event> events = eventRepository.saveAll(
//                Arrays.asList(
//                        new Event(null, "Todo smth", now),
//                        new Event(null, "Todo smth2", now)
//                )
//        );
//
//        StepVerifier
//                .create(events)
//                .expectNextMatches(e ->
//                        e.getEventId() != null &&
//                                e.getEventName().equals("Todo smth") &&
//                                e.getEventDateTime().equals(now)
//                ).expectNextMatches(e ->
//                e.getEventId() != null &&
//                        e.getEventName().equals("Todo smth2") &&
//                        e.getEventDateTime().equals(now)
//        )
//                .verifyComplete();
//    }
//
//    @AfterEach
//    void clearTable() {
//        eventRepository.deleteAll();
//    }
//}
