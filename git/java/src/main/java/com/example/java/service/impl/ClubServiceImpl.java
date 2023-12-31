package com.example.java.service.impl;

import com.example.java.dto.ClubDto;
import com.example.java.models.Club;
import com.example.java.repository.ClubRepository;
import com.example.java.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.java.mapper.ClubMapper.mapToClub;
import static com.example.java.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClub() {
        List<Club> clubs=clubRepository.findAll();
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club=mapToClub(clubDto);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findByClubId(Long clubId) {
        Club club=clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updeteClub(ClubDto clubDto) {
        Club club=mapToClub(clubDto);
        clubRepository.save(club);

    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs=clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }


}