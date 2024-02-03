package com.example.myhotel.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "chek_in")
    private LocalDate checkInDate;

    @Column(name = "chek_out")
    private LocalDate checkOutDate;

    @Column(name = "Guest_FullName")
    private String guestFulName;

    @Column(name = "Guest_Email")
    private String guestEmail;

    @Column(name = "adults")
    private int numOfChildren;

    @Column(name = "children")
    private int numOfAdults;

    @Column(name = "Total_Guest")
    private int totalNumOfGuest;

    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    public void calculateTotalNumberOfGuest(){
        this.totalNumOfGuest=numOfAdults+numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
