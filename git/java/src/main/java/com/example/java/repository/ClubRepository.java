package com.example.java.repository;

import com.example.java.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Long> {
Optional<Club>findByTitle(String url);

@Query("SELECT c from Club c Where c.title LIKE CONCAT('%' , :query,'%')")
    List<Club> searchClubs(String query);


}
