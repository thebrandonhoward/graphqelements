package com.thebrandonhoward.graphqelements;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @QueryMapping
    public List<Book> books() {
        return Book.getAllBooks();
    }

    @SubscriptionMapping
    public Flux<Book> bookAdded() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(interval -> publishBook());
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.author().id());
    }

    @SchemaMapping(typeName = "Author", field = "reviews")
    public List<Review> reviews(@Argument Integer filter, Author author) {
        return Review.getInstances()
                .stream()
                .filter(review -> Objects.isNull(filter) || review.getRating() == filter)
                .sorted(Comparator.comparing(Review::getRating).reversed())
                .toList();
    }

    // Method to simulate adding a new book and publishing it
    public Book publishBook() {
        return bookService.addBook("AddedBook-"+new Random().nextInt(), "AddedAuthor-"+new Random().nextInt());
    }
}