package com.selva.SpingMongo.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="books")

public class Book {

    @Id
    private String id;

    @NotNull(message ="title can not be null")
    private String title;
    @NotNull(message ="author can not be null")
    private String author;
    @NotNull(message ="pages can not be null")
    private Integer pages;


    private String[] genres;

    @NotNull(message ="rating can not be null")
    private Integer rating;
    private Object[] reviews;

    private  Object hscLargeObject;

}
