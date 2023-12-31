package com.example.java.mapper;

import com.example.java.dto.EventDto;
import com.example.java.models.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto){
        Event event= Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .build();
        return event;
    }
    public static EventDto mapToEventDto(Event event){
        EventDto eventDto= EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .createdOn(event.getCreatedOn())
                .club(event.getClub())
                .build();
        return eventDto;
    }
}
