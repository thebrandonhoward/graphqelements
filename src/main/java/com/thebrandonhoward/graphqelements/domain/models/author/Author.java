package com.thebrandonhoward.graphqelements.domain.models.author;

import com.thebrandonhoward.graphqelements.domain.models.review.Review;

import java.util.Arrays;
import java.util.List;

public record Author (String id, String firstName, String lastName, Review review) {

    static final List<Author> authors = Arrays.asList(
            new Author("author-1", "Joshua", "Bloch", null),
            new Author("author-2", "Douglas", "Adams", null),
            new Author("author-3", "Bill", "Bryson", null)
    );

    public static Author getById(String id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}