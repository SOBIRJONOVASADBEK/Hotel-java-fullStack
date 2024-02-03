package com.example.myhotel.repository;

import com.example.myhotel.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedRoomRepository extends JpaRepository<BookedRoom,Long> {
}
