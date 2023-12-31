package com.example.java.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ClubDto {


    private Long id;

    @NotEmpty(message = "Club title should not be empty")
    private String title;

    @NotEmpty(message = "Photo url should not be empty")
    private String photoUrl;

    @NotEmpty(message = "Content should not be empty")
    private String content;

    private LocalDateTime createOn;

    private LocalDateTime updateOn;

    private List<EventDto> events;

}
