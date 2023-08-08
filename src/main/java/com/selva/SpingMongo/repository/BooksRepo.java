package com.selva.SpingMongo.repository;

import com.selva.SpingMongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepo  extends MongoRepository <Book,String> {

    @Query("{'title':?0}")
    Optional<Book> findBookByTitle(String book);
}
