package com.thebrandonhoward.graphqelements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private String id;
    private String message;
    private int rating;

    static List<Review> getInstances() {
        return List.of(
                new Review(Integer.toString(1), "Bad", 1)
                ,new Review(Integer.toString(2), "Great", 5)
                ,new Review(Integer.toString(3), "Awesome", 5)
                ,new Review(Integer.toString(4), "OK", 3)
                ,new Review(Integer.toString(5), "Classic", 5));
    }
}
