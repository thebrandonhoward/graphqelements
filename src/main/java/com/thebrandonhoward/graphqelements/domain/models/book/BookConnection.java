package com.thebrandonhoward.graphqelements.domain.models.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookConnection {
    private List<Book> books;
    private String cursor;  // The cursor for the next page
}