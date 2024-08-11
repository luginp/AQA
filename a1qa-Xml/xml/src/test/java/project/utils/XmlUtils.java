package project.utils;

import com.google.common.collect.Ordering;
import framework.utils.XmlParser;
import project.models.Book;
import project.models.Catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlUtils {
    private XmlParser xmlParser = new XmlParser();
    private Catalog catalog = xmlParser.xmlParse();
    public boolean checkIfBooksInTheRightOrder(){
        List<Integer> list = new ArrayList<>();
        for (Book book: catalog.getBooks()){
            list.add(Integer.parseInt(book.getId().substring(2)));
        }
        return Ordering.natural().isOrdered(list);
    }
    private Book findingTheHighestCostBook(){
        List<Double> list = new ArrayList<>();
        Book highestCostBook = new Book();
        for (Book book: catalog.getBooks()){
            list.add(Double.parseDouble(book.getPrice()));
        }
        for (Book book: catalog.getBooks()){
            if (Double.parseDouble(book.getPrice()) == Collections.max(list)){
                highestCostBook = book;
            }
        }
        return highestCostBook;
    }
    private Book findingTheLowestCostBook(){
        List<Double> list = new ArrayList<>();
        Book lowestCostBook = new Book();
        for (Book book: catalog.getBooks()){
            list.add(Double.parseDouble(book.getPrice()));
        }
        for (Book book: catalog.getBooks()){
            if (Double.parseDouble(book.getPrice()) == Collections.min(list)){
                lowestCostBook = book;
            }
        }
        return lowestCostBook;
    }
    public boolean checkingIfHighestCostBookNotTheSameAsLowest(){
    return findingTheHighestCostBook().equals(findingTheLowestCostBook());
    }
}
