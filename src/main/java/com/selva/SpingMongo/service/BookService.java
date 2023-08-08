package com.selva.SpingMongo.service;

import com.selva.SpingMongo.exception.BooksCollectionException;
import com.selva.SpingMongo.model.Book;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface BookService {

    public void createBook(Book book)  throws ConstraintViolationException, BooksCollectionException;

    public List<Book> getAllBooks();

    public Book getSingleBook(String id) throws  BooksCollectionException;

    public  void updateBook (String id,Book book) throws  BooksCollectionException;

    public void deleteBook (String id) throws BooksCollectionException;
}
