package ru.inmy.core.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    void create() {
        LocalDateTime now = LocalDateTime.now();
        Event event = new Event(1L, "Todo smth", now);
        assertEquals(1L, event.getEventId());
        assertEquals("Todo smth", event.getEventName());
        assertEquals(now, event.getEventDateTime());
    }
}
