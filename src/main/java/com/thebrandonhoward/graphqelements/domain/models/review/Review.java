package com.thebrandonhoward.graphqelements.domain.models.review;

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
    private Long rating;

    public static List<Review> getInstances() {
        return List.of(
                new Review(Integer.toString(1), "Bad", 1L)
                ,new Review(Integer.toString(2), "Great", 5L)
                ,new Review(Integer.toString(3), "Awesome", 5L)
                ,new Review(Integer.toString(4), "OK", 3L)
                ,new Review(Integer.toString(5), "Classic", 5L));
    }
}
