package project.utils;

import aquality.selenium.logger.Logger;
import project.enums.BookCost;
import project.models.Book;
import project.models.Catalog;

import java.io.IOException;
import java.util.List;

public class BookSelectUtils {
    private static Logger logger = Logger.getInstance();

    public static Book getMaxOrMinCostBookFromList(Catalog catalog, BookCost bookCost) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        List<Book> books = getBooks(catalog);
        Book bookWithNeededPrice = books.get(0);
        for (Book book : books) {
            double price = getPrice(book);
            if (bookCost.equals(BookCost.MIN)) {
                if (min > price) {
                    min = price;
                    bookWithNeededPrice = book;
                }
            } else if (bookCost.equals(BookCost.MAX)) {
                if (max < price) {
                    max = price;
                    bookWithNeededPrice = book;
                }
            }
            else {
                logger.error("Wrong book cost value");
            }
        }
        return bookWithNeededPrice;
    }

    private static List<Book> getBooks(Catalog catalog) {
        return catalog.getBooks();
    }

    private static Double getPrice(Book book) {
        return book.getPrice();
    }
}

