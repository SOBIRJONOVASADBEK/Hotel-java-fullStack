package com.example.java.service;

import com.example.java.dto.ClubDto;
import com.example.java.models.Club;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {

    List<ClubDto> findAllClub();

    Club saveClub(ClubDto clubDto);

    ClubDto findByClubId(Long clubId);

    void updeteClub(ClubDto clubDto);

    void deleteClub(Long clubId);

    List<ClubDto> searchClubs(String query);
}
