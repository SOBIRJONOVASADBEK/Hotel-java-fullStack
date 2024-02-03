package com.example.myhotel.controller;

import com.example.myhotel.exception.InvalidBookingRequestException;
import com.example.myhotel.exception.ResourceNotFoundException;
import com.example.myhotel.model.BookedRoom;
import com.example.myhotel.model.Room;
import com.example.myhotel.response.BookingResponse;
import com.example.myhotel.response.RoomResponse;
import com.example.myhotel.service.IBookingService;
import com.example.myhotel.service.IRoomService;
import com.example.myhotel.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final IBookingService iBookingService;
    private final IRoomService roomService;

    public BookingController(IBookingService iBookingService, IRoomService roomService) {
        this.iBookingService = iBookingService;
        this.roomService = roomService;
    }

    @GetMapping("/all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        List<BookedRoom> bookedRooms=iBookingService.findAll();
        List<BookingResponse> bookingResponses=new ArrayList<>();
        for (BookedRoom bookedRoom:bookedRooms){
            BookingResponse bookingResponse=getBookingResponse(bookedRoom);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.of(java.util.Optional.of(bookingResponses));
    }



    @GetMapping("/confirmation/{confirmationCode}")
  public ResponseEntity<?> getBookingConfirmationCode(@PathVariable String confirmationCode){
        try {

            BookedRoom bookedRoom=iBookingService.findByBookingConfirmationCode(confirmationCode);
            BookingResponse bookingResponse=getBookingResponse(bookedRoom);
            return ResponseEntity.ok(bookingResponse);
        }catch (ResourceNotFoundException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
  }

    private BookingResponse getBookingResponse(BookedRoom bookedRoom) {
        Room theRoom=roomService.getRoomId(bookedRoom.getRoom().getId());
        RoomResponse room=new RoomResponse(theRoom.getId(),theRoom.getRoomType(),theRoom.getRoomPrice());
        return new BookingResponse(bookedRoom.getBookingId(),bookedRoom.getCheckInDate(),bookedRoom.getCheckOutDate(),
                bookedRoom.getBookingConfirmationCode(),bookedRoom.getGuestEmail(),bookedRoom.getNumOfAdults(),
                bookedRoom.getNumOfChildren(),bookedRoom.getTotalNumOfGuest(),bookedRoom.getBookingConfirmationCode(),room);
    }

    @GetMapping("/room/{roomId}/booking")
  public ResponseEntity<?> saveBooking(@PathVariable Long roomId,
                                       @RequestBody BookedRoom bookingRequest){
        try {
          String confirmationCode= iBookingService.saveBooking(roomId,bookingRequest);
          return ResponseEntity.ok("Room books successfully, Your Booking code is:"+confirmationCode);
        }catch (InvalidBookingRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
  }
  @DeleteMapping("/booking/{bokingId}/delete")
  public void cancelBooking(Long bookingId){
        iBookingService.cancelBooking(bookingId);
  }

}
