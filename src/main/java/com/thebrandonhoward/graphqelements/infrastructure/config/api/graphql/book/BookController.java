package com.thebrandonhoward.graphqelements.infrastructure.config.api.graphql.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thebrandonhoward.graphqelements.domain.models.book.CustomerQuery;
import com.thebrandonhoward.graphqelements.domain.models.book.Page;
import com.thebrandonhoward.graphqelements.domain.models.review.Review;
import com.thebrandonhoward.graphqelements.application.services.book.BookService;
import com.thebrandonhoward.graphqelements.application.common.utils.CursorUtil;
import com.thebrandonhoward.graphqelements.domain.models.author.Author;
import com.thebrandonhoward.graphqelements.domain.models.book.Book;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.security.Principal;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;
    private final CursorUtil cursorUtil;
    private final ObjectMapper objectMapper;

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public Book bookById(DataFetchingEnvironment dataFetchingEnvironment, Principal principal, @Argument String id) {
        try {
            log.info(objectMapper.writeValueAsString(principal));
//            log.info(objectMapper.writeValueAsString(dataFetchingEnvironment));
            return Book.getById(id);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

//    @QueryMapping
//    public Page<Book> books(@Argument String after, @Argument int first, @Argument int last, @Argument String before) {
//        // Define the cursor-based pagination logic.
//        List<Book> books;
//        String cursor = null;
//
//        // Logic to query books with cursor-based pagination
//        if (after == null) {
//            books = Book.getAllBooks().stream().limit(first).toList();
//        } else {
//            Long lastKey = Long.parseLong(after); // Convert cursor (last book's id)
//            books = Book.getAllBooks().stream()
//                    .filter(book -> book.key() > lastKey)
//                    .limit(first)
//                    .toList();
//        }
//
//        // If there are more books after the current page, return a cursor.
//        if (books.size() == first) {
//            cursor = String.valueOf(books.get(books.size() - 1).key());
//        }
//
//        return new Page<>(books, cursor);
//    }

    @QueryMapping()
    public CustomerQuery customerQuery() {
        return new CustomerQuery();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public Connection<Book> books(@Argument Integer first, @Argument String after, @Argument Integer last, @Argument String before) {
        List<Edge<Book>> bookEdgeList = Book.getAllBooks()
                .stream()
                .map(book -> new DefaultEdge<>(book, cursorUtil.from(book.id())))
                .collect(Collectors.toUnmodifiableList());

        ConnectionCursor firstCursorFrom = cursorUtil.getFirstCursorFrom(bookEdgeList);

        ConnectionCursor lastCursorFrom = cursorUtil.getLastCursorFrom(bookEdgeList);

        PageInfo pageInfo = new DefaultPageInfo(firstCursorFrom, lastCursorFrom, before != null, bookEdgeList.size() >= first);

        return new DefaultConnection<>(bookEdgeList, pageInfo);
    }

   //@SubscriptionMapping
    public Flux<Book> bookAdded() {
        return Flux.interval(Duration.ofMinutes(5))
                .map(interval -> publishBook());
    }

    @SchemaMapping(typeName = "CustomerQuery", field = "booksForSale")
    public List<Book> book(CustomerQuery customerQuery) {
        return Book.getAllBooks();
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.author().id());
    }

    @SchemaMapping(typeName = "Author", field = "reviews")
    public List<Review> reviews(@Argument Long filter, Author author) {
        return Review.getInstances()
                .stream()
                .filter( review -> Objects.isNull(filter) || filter.equals(review.getRating()) )
                .sorted(Comparator.comparing(Review::getRating).reversed())
                .toList();
    }

    // Method to simulate adding a new book and publishing it
    public Book publishBook() {
        Book book = bookService.addBook("AddedBook-"+new Random().nextInt(), "AddedAuthor-"+new Random().nextInt());
        log.info("+ {}", book.id());
        return book;
    }

}