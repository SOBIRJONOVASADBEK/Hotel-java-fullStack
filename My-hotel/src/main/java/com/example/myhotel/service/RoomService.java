package com.example.myhotel.service;

import com.example.myhotel.exception.InternalServerException;
import com.example.myhotel.exception.ResourceNotFoundException;
import com.example.myhotel.model.Room;
import com.example.myhotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RoomService implements IRoomService{
    private final RoomRepository roomRepository;



    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException {
        Room room=new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if (!file.isEmpty()){
            byte[] photoBytes=file.getBytes();
            Blob photoBlob=new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public byte[] getRoomPhotoByRoomId(Long id) throws SQLException, ResourceNotFoundException {
        Optional<Room> theRoom=roomRepository.findById(id);
         if (theRoom.isEmpty()){
             throw new ResourceNotFoundException("Sorry,Not found Room");
         }
         Blob photoBlob=theRoom.get().getPhoto();
         if (photoBlob !=null){
             return photoBlob.getBytes(1,(int)photoBlob.length());
         }
       return null;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public void delete(Long roomId) {
        Optional<Room> theRoom=roomRepository.findById(roomId);
        if (theRoom.isEmpty()){
            roomRepository.deleteById(roomId);
        }
    }

    @Override
    public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) {
        Room room=roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room not found"));
        if (roomType!=null) room.setRoomType(roomType);
        if (roomPrice!=null) room.setRoomPrice(roomPrice);
        if (photoBytes!=null && photoBytes.length>0){
            try {
                room.setPhoto(new SerialBlob(photoBytes));
            }catch (SQLException e){
                throw new InternalServerException("Error update room!");
            }
        }


        return roomRepository.save(room);
    }

    @Override
    public Room getRoomId(Long roomId) {
        return Optional.of(roomRepository.findById(roomId).get());
    }
}
