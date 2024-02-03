package com.example.myhotel.service;

import com.example.myhotel.exception.ResourceNotFoundException;
import com.example.myhotel.model.Room;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public interface IRoomService {

    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException;

    List<String> getAllRoomTypes();

    byte[] getRoomPhotoByRoomId(Long id) throws SQLException, ResourceNotFoundException;

    List<Room> getAllRooms();

    void delete(Long roomId);

    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes);

    Room getRoomId(Long roomId);
}
