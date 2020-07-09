package ru.inmy.core.event;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
}
