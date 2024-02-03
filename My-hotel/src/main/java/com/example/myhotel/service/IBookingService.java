package com.example.myhotel.service;

import com.example.myhotel.model.BookedRoom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookingService  {
    void cancelBooking(Long bookingId);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> findAll();

}
