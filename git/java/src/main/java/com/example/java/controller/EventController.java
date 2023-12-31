package com.example.java.controller;

import com.example.java.dto.EventDto;
import com.example.java.models.Event;
import com.example.java.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {
    private final EventService eventService;


    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId")Long clubId, EventDto eventDto, Model model){
        Event event=new Event();
        model.addAttribute("clubId",clubId);
        model.addAttribute("event",event);
        return "events-create";
    }
    @GetMapping("/events/{eventId}")
    public  String viewEvent(@PathVariable("eventId")Long eventId,Model model){
        EventDto eventDto=eventService.findByEvent(eventId);
        model.addAttribute("event",eventDto);
        return "event-detail";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable Long clubId,@ModelAttribute("event") EventDto eventDto,Model model){
        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs"+clubId;
    }
    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId")Long eventId,Model model){
        EventDto eventDto=eventService.findByEvent(eventId);
        model.addAttribute("event",eventDto);
        return "events-edit";
    }
    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId")Long eventId,
                              @Valid @PathVariable("event")EventDto eventDto,
                              BindingResult result,Model model){
        if (result.hasErrors()){
            model.addAttribute("event",eventDto);
            return "event-edit";
        }
        EventDto eventDto1=eventService.findByEvent(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(eventDto1.getClub());
        eventService.updateEvent(eventDto);
        return "/redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId")Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
}
