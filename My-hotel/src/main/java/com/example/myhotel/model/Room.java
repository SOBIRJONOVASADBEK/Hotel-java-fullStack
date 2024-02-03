package com.example.myhotel.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomType;

    private BigDecimal roomPrice;

    private boolean isBooked=false;

    @Lob
    private Blob photo;

    @OneToMany(mappedBy ="room",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<BookedRoom> bookings;

    private void addBooking(BookedRoom bookedRoom){
        if (bookings==null){
            bookings=new ArrayList<>();
        }
        bookings.add(bookedRoom);
        bookedRoom.setRoom(this);
        isBooked=true;
        String bookingCode= RandomStringUtils.randomNumeric(10);
        bookedRoom.setBookingConfirmationCode(bookingCode);

    }
}
