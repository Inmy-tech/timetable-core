package ru.inmy.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    Flux<Event> findAll() {
        return eventRepository.findAll();
    }

    Mono<Void> deleteById(Long id) {
        return eventRepository.deleteById(id);
    }

    Mono<Event> save(Event event) {
        return eventRepository.save(event);
    }

    Mono<Event> findById(Long id) {
        return eventRepository.findById(id);
    }
}
