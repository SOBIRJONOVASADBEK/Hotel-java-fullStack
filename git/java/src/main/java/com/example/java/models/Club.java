package com.example.java.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String photoUrl;

    private String content;

    @CreationTimestamp
    private LocalDateTime createOn;

    @UpdateTimestamp
    private LocalDateTime updateOn;

    @OneToMany(mappedBy = "club",cascade = CascadeType.REMOVE)
    private List<Event> events=new ArrayList<>();
}
