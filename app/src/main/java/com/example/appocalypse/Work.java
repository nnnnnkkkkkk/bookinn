package com.example.appocalypse;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "work", strict = false)
public class Work {
    @Element(name = "best_book")
    private Books Best_bookObject;

    public Books getBest_book() {
        return Best_bookObject;
    }

    public void setBest_book(Books best_bookObject) {
        this.Best_bookObject = best_bookObject;
    }
}