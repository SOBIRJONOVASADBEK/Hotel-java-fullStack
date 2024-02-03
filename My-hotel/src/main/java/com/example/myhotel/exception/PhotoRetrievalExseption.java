package com.example.myhotel.exception;

public class PhotoRetrievalExseption extends RuntimeException {
    public PhotoRetrievalExseption(String error_retrieving_photo) {
        super(error_retrieving_photo);

    }
}
