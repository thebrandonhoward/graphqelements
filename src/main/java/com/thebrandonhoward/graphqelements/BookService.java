package com.thebrandonhoward.graphqelements;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookService {

    public Book addBook(String title, String author) {
        Book newBook = new Book(generateId(), title, 1, new Author("author-"+new Random().nextInt(7), author, author, null));

        Book.getAllBooks().add(newBook);

        return newBook;
    }

    private String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}
