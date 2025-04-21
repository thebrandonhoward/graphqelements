package com.thebrandonhoward.graphqelements.domain.models.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Page<T> {
    private List<T> content;
    private String cursor;  // The cursor used for pagination (next page)
}
