package ru.inmy.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @MessageMapping("event.find-all")
    public Flux<Event> findAllEvents() {
        return eventService.findAll();
    }

    @MessageMapping("")
    public Flux<Event> findAllEvents2() {
        return eventService.findAll();
    }

    @MessageMapping("event.find.{id}")
    public Mono<Event> findEvent(@DestinationVariable Long id) {
        return eventService.findById(id);
    }

    @MessageMapping("event.delete.{id}")
    public Mono<Void> deleteEvent(@DestinationVariable Long id) {
        return eventService.deleteById(id);
    }

    @MessageMapping("event.save")
    public Mono<Event> saveEvent(@Payload Event event) {
        return eventService.save(event);
    }
}
