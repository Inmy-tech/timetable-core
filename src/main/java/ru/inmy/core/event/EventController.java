package ru.inmy.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Bean
    RouterFunction<ServerResponse> eventRoutes() {
        return
                route(GET("/events"),
                        req -> ok().body(
                                eventService.findAll(), Event.class)
                )
                        .and(route(GET("/event/{id}"),
                                req -> ok().body(
                                        eventService.findById(Long.parseLong(req.pathVariable("id"))),
                                        Event.class)
                                )
                        )
                        .and(route(POST("/event"),
                                req -> req.body(toMono(Event.class))
                                        .doOnNext(eventService::save)
                                        .then(ok().build())
                                )
                        )
                        .and(route(DELETE("/event/{id}"),
                                req -> ok().body(
                                        eventService.deleteById(Long.parseLong(req.pathVariable("id"))),
                                        Event.class)
                                )
                        );
    }
}
