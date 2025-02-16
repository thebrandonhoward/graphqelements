package com.thebrandonhoward.graphqelements;

import java.util.ArrayList;
import java.util.List;

public record Book (String id, String name, int pageCount, Author author) {

    private static ArrayList<Book> books = new ArrayList<>(List.of(
            new Book("book-1", "Effective Java", 416, Author.getById("author-1")),
            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, Author.getById("author-2")),
            new Book("book-3", "Down Under", 436, Author.getById("author-3")),
            new Book("book-4", "Effective Java2", 416, Author.getById("author-1")),
            new Book("book-5", "Hitchhiker's Guide to the Galaxy2", 208, Author.getById("author-2")),
            new Book("book-6", "Down Under2", 436, Author.getById("author-3"))
    ));

    public static Book getById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Book> getAllBooks() {
        return books;
    }
}
