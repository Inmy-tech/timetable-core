package ru.inmy.core.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    public EventService eventService;

    @Test
    void findById() {
        LocalDateTime now = LocalDateTime.now();
        doReturn(Mono.just(new Event(1L, "Todo smth", now)))
                .when(eventService).findById(eq(1L));
        Mono<Event> event = eventService.findById(1L);

        StepVerifier
                .create(event)
                .expectNextMatches(e ->
                        e.getEventId() != null &&
                                e.getEventName().equals("Todo smth") &&
                                e.getEventDateTime().equals(now)
                )
                .verifyComplete();
    }

    @Test
    void deleteById() {
        doReturn(Mono.empty()).when(eventService).deleteById(eq(1L));
        Mono<Void> mono = eventService.deleteById(1L);

        StepVerifier
                .create(mono)
                .expectComplete()
                .verify();
    }

    @Test
    void save() {
        LocalDateTime now = LocalDateTime.now();
        doReturn(Mono.just(new Event(1L, "Todo smth", now)))
                .when(eventService).save(eq(new Event(null, "Todo smth", now)));
        Mono<Event> event = eventService.save(new Event(null, "Todo smth", now));

        StepVerifier
                .create(event)
                .expectNextMatches(e ->
                        e.getEventId() != null &&
                                e.getEventName().equals("Todo smth") &&
                                e.getEventDateTime().equals(now)
                )
                .verifyComplete();
    }
}
