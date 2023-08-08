package com.selva.SpingMongo.controller;

import com.selva.SpingMongo.exception.BooksCollectionException;
import com.selva.SpingMongo.model.Book;
import com.selva.SpingMongo.repository.BooksRepo;
import com.selva.SpingMongo.service.BookService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController

public class BooksController {

    @Autowired
    private BooksRepo booksrepo;

    @Autowired
    private BookService bookservice;

    @GetMapping("/books")
    public ResponseEntity<?> getAllTodos() {
        List<Book> books = bookservice.getAllBooks();
        return new ResponseEntity<>(books, !books.isEmpty() ?HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody Book book)  {
        try {
            bookservice.createBook(book);
            return new ResponseEntity<Book>(book,HttpStatus.OK);

        }
        catch (ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }

        catch (BooksCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getSingleBook(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(bookservice.getSingleBook(id),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBookById(@PathVariable("id") String id,@RequestBody Book book) {

      try {
          bookservice.updateBook(id, book);
          return new ResponseEntity<>("Updated the book with id " + id,HttpStatus.OK);
      }
      catch (ConstraintViolationException e)
      {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
      }

      catch (BooksCollectionException e)
      {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
      }

    }


@DeleteMapping("books/{id}")
    public  ResponseEntity<?> deleteById(@PathVariable("id") String id){

        try {
            bookservice.deleteBook(id);
            return new ResponseEntity<>("Successfully deleted the id " + id, HttpStatus.OK);
        }
        catch (BooksCollectionException e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
