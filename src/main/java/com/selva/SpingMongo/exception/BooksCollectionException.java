package com.selva.SpingMongo.exception;

public class BooksCollectionException extends Exception {


    public BooksCollectionException(String message) {
        super(message);
    }
    public static  String NotFoundException(String id) {
        return " Book with " + id + " not found";

    }

    public static  String BookAlreadyExist() {
        return " Book with given title already exist";

    }

}
