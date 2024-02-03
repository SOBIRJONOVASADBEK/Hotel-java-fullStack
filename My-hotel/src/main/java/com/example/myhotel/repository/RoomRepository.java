package com.example.myhotel.repository;

import com.example.myhotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query("SELECT r.roomType from Room r")
     List<String> findDistinctRoomTypes() ;


}
