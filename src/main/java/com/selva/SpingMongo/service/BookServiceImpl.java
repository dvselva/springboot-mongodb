package com.selva.SpingMongo.service;

import com.selva.SpingMongo.exception.BooksCollectionException;
import com.selva.SpingMongo.model.Book;
import com.selva.SpingMongo.repository.BooksRepo;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BooksRepo booksRepo;


    @Override
    public void createBook(Book book) throws ConstraintViolationException , BooksCollectionException {

        Optional <Book> bookOptional =  booksRepo.findBookByTitle(book.getTitle());
        if (bookOptional.isPresent()) {
            throw new BooksCollectionException(BooksCollectionException.BookAlreadyExist());
        }
        else
        {
            booksRepo.save(book);
        }


}

    public List<Book> getAllBooks() {
      List<Book> books=  booksRepo.findAll();
        if (!books.isEmpty()) {
            return books;
        }
        else
        {
            return new ArrayList<Book>();
        }
    }

    public Book getSingleBook(String id) throws  BooksCollectionException {
        Optional <Book> optionalBook = booksRepo.findById(id);
        if (optionalBook.isEmpty()) {
            throw  new BooksCollectionException(BooksCollectionException.NotFoundException(id));
        }
        else
        {
            return   optionalBook.get();
        }
    }

    public  void updateBook (String id,Book book) throws  BooksCollectionException {
       Optional <Book> optionalBook =  booksRepo.findById(id);
      Optional <Book>  bookWithSameTitle =  booksRepo.findBookByTitle(book.getTitle());


       if (optionalBook.isPresent()) {

//           if (bookWithSameTitle.isPresent()) {
//               throw new BooksCollectionException(BooksCollectionException.BookAlreadyExist());
//           }
           Book bookToUpdate = optionalBook.get();
           bookToUpdate.setTitle(book.getTitle());
           bookToUpdate.setRating(book.getRating());
           bookToUpdate.setGenres(book.getGenres());
           bookToUpdate.setPages(book.getPages());
           bookToUpdate.setAuthor(book.getAuthor());
           bookToUpdate.setReviews(book.getReviews());
           bookToUpdate.setHscLargeObject(book.getHscLargeObject());
           booksRepo.save(bookToUpdate);
       }
       else {

           throw  new BooksCollectionException(BooksCollectionException.NotFoundException(id));
       }
    }


    public void deleteBook (String id) throws BooksCollectionException {

       Optional<Book> tobeDeleted = booksRepo.findById(id);
       if (tobeDeleted.isEmpty()) {
           throw new BooksCollectionException(BooksCollectionException.NotFoundException(id));
       }
       else
       {
           booksRepo.deleteById(id);

       }


    }


}



