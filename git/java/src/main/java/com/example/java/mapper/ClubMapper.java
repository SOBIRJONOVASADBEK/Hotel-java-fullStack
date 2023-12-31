package com.example.java.mapper;

import com.example.java.dto.ClubDto;
import com.example.java.models.Club;

public class ClubMapper {
    public static Club mapToClub(ClubDto clubDto){
        Club club=Club.builder().
                id(clubDto.getId())
                .content(clubDto.getContent())
                .createOn(clubDto.getCreateOn())
                .photoUrl(clubDto.getPhotoUrl())
                .title(clubDto.getTitle())
                .updateOn(clubDto.getUpdateOn())
                .build();
        return club;
    }

    public static ClubDto mapToClubDto(Club club){
        ClubDto clubDto=ClubDto.builder()
                .id(club.getId())
                .content(club.getContent())
                .createOn(club.getCreateOn())
                .photoUrl(club.getPhotoUrl())
                .updateOn(club.getUpdateOn())
                .title(club.getTitle())
                .build();
        return clubDto;
    }


}
