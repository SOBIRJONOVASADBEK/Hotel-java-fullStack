package com.example.myhotel.exception;

public class InvalidBookingRequestException extends RuntimeException{
    public InvalidBookingRequestException(String s) {
        super(s);

    }
}
