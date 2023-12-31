package com.example.java.service.impl;

import com.example.java.dto.EventDto;
import com.example.java.models.Club;
import com.example.java.models.Event;
import com.example.java.repository.ClubRepository;
import com.example.java.repository.EventRepository;
import com.example.java.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.java.mapper.EventMapper.mapToEvent;
import static com.example.java.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club=clubRepository.findById(clubId).get();
        Event event=mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public EventDto findByEvent(Long eventId) {
        Event event=eventRepository.getById(eventId);
        EventDto eventDto=mapToEventDto(event);
        return eventDto;
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event=mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }


}
