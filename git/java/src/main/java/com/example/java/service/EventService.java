package com.example.java.service;

import com.example.java.dto.EventDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    EventDto findByEvent(Long eventId);

    void updateEvent(EventDto eventDto);

    void deleteEvent(Long eventId);
}
