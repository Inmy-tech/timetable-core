package ru.inmy.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("events")
public class Event {

    @Id
    private Long eventId;
    private String eventName;
    @Column("event_datetime")  
    private LocalDateTime eventDateTime;
}
