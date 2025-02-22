package com.thebrandonhoward.graphqelements.domain.models.book;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerQuery {
    private List<Book> booksForSale = new ArrayList();
}
