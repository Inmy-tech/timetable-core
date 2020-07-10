package ru.inmy.core.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

@WebFluxTest
@Import(EventController.class)
@ExtendWith(SpringExtension.class)
public class EventControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EventService eventService;

    @Test
    void findAllEvents() {
        LocalDateTime now = LocalDateTime.now();
        doReturn(Flux.just(
                Arrays.asList(
                        new Event(1L, "Todo smth", now),
                        new Event(2L, "Todo smth2", now)
                ))
        )
                .when(eventService).findAll();

        this.webTestClient
                .get()
                .uri("/events")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0][0].eventId").exists()
                .jsonPath("$[0][0].eventId").value(is(1))
                .jsonPath("$[0][0].eventName").exists()
                .jsonPath("$[0][0].eventName").value(is("Todo smth"))
                .jsonPath("$[0][0].eventDateTime").exists()
                .jsonPath("$[0][1].eventId").exists()
                .jsonPath("$[0][1].eventId").value(is(2))
                .jsonPath("$[0][1].eventName").exists()
                .jsonPath("$[0][1].eventName").value(is("Todo smth2"))
                .jsonPath("$[0][1].eventDateTime").exists();
    }

    @Test
    void findEventById() {
        LocalDateTime now = LocalDateTime.now();
        doReturn(Mono.just(new Event(1L, "Todo smth", now)))
                .when(eventService).findById(1L);

        this.webTestClient
                .get()
                .uri("/event/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("@.eventId").exists()
                .jsonPath("@.eventId").value(is(1))
                .jsonPath("@.eventName").exists()
                .jsonPath("@.eventName").value(is("Todo smth"))
                .jsonPath("@.eventDateTime").exists();
    }

    @Test
    void deleteEventById() {
        doReturn(Mono.empty()).when(eventService).deleteById(1L);

        this.webTestClient
                .delete()
                .uri("/event/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().isEmpty();
    }

    @Test
    void saveEvent() {
        LocalDateTime now = LocalDateTime.now();
        doReturn(Mono.empty())
                .when(eventService).save(new Event(null, "Todo smth", now));

        this.webTestClient
                .post()
                .uri("/event")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}
