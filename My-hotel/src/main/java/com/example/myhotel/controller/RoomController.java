package com.example.myhotel.controller;

import com.example.myhotel.exception.PhotoRetrievalExseption;
import com.example.myhotel.exception.ResourceNotFoundException;
import com.example.myhotel.model.BookedRoom;
import com.example.myhotel.model.Room;
import com.example.myhotel.response.BookingResponse;
import com.example.myhotel.response.RoomResponse;
import com.example.myhotel.service.BookingService;
import com.example.myhotel.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final IRoomService roomService;
    private final BookingService bookingService;



    @PostMapping("/add/new-room")
    public ResponseEntity<RoomResponse> addNewRoom(
           @RequestParam("photo") MultipartFile photo,
           @RequestParam("room_type") String roomType,
           @RequestParam("room_price") BigDecimal roomPrice) throws SQLException, IOException {
        Room savedRoom=roomService.addNewRoom(photo,roomType,roomPrice);
        RoomResponse response=new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(),savedRoom.getRoomPrice());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/room/types")
    public ResponseEntity<List<String>> getRoomTypes(){
        List<String> getAllRoomTypes=roomService.getAllRoomTypes();
        return ResponseEntity.ok(getAllRoomTypes);
    }

    public ResponseEntity<List<RoomResponse>> getAllRooms() throws SQLException, ResourceNotFoundException {
        List<Room> rooms=roomService.getAllRooms();
        List<RoomResponse>roomResponses=new ArrayList<>();
        for (Room room:rooms){
            byte[] photoBytes=roomService.getRoomPhotoByRoomId(room.getId());
            if (photoBytes != null && photoBytes.length>0){
                String base64Photo= Base64.encodeBase64String(photoBytes);
                RoomResponse roomResponse=getRoomResponse(room);
                roomResponse.setPhoto(base64Photo);
                roomResponses.add(roomResponse);
            }
        }
        return ResponseEntity.ok(roomResponses);
    }
    @GetMapping("/room/{roomId}")
    public ResponseEntity<Optional<RoomResponse>> getRoomById(@PathVariable Long roomId){

        Optional<Room> theRoom=roomService.getRoomId(roomId);
        return theRoom.map(room -> {
            RoomResponse response=getRoomResponse(room);
            return ResponseEntity.ok(Optional.of(response));
        }).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

    private RoomResponse getRoomResponse(Room room) {
        List<BookedRoom> bookings=getAllBookingsRoomId(room.getId());
        List<BookingResponse> bookingInfo=bookings.stream().map(
                booking -> new BookingResponse(booking.getBookingId(),
                        booking.getCheckInDate(),
                        booking.getCheckOutDate(),
                        booking.getBookingConfirmationCode())).toList();
        byte[] photoByte=null;
        Blob photoBlob=room.getPhoto();
        if (photoBlob !=null){
            try {
             photoByte=photoBlob.getBytes(1,(int) photoBlob.length());
            }catch ( SQLException e){
                throw new PhotoRetrievalExseption("Error retrieving photo");
            }
        }
        return new RoomResponse(room.getId(), room.getRoomType(),room.getRoomPrice(),room.isBooked(),photoByte,bookingInfo);
    }
    @DeleteMapping("/delete/room/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId){
        roomService.delete(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long roomId,
                                                   @RequestParam(required = false) String roomType,
                                                   @RequestParam(required = false) BigDecimal roomPrice,
                                                   @RequestParam(required = false) MultipartFile photo) throws IOException, SQLException {
        byte[] photoBytes=photo!=null&&!photo.isEmpty()?
                photo.getBytes():roomService.getRoomPhotoByRoomId(roomId);
        Blob photoBlob=photoBytes !=null  && photoBytes.length>0?
                new SerialBlob(photoBytes):null;
        Room theRoom=roomService.updateRoom(roomId,roomType,roomPrice,photoBytes);
        theRoom.setPhoto(photoBlob);
        RoomResponse response=getRoomResponse(theRoom);
        return ResponseEntity.ok(response);
    }

    private List<BookedRoom> getAllBookingsRoomId(Long id) {
             return bookingService.gatAllBookingsByRoomId(id);
    }



}
