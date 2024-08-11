package project.steps;

import framework.utils.RegExp;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import project.models.Book;
import project.models.Catalog;

public class ApiSteps {
    private static final String patternForId = "bk([\\d]{3})";

    public void assertAscOrderArrayBooks(Catalog catalog) {
        for (int i = 0; i < catalog.getBooks().size() - 1; i++) {
            int idBookFromArray = getIdBookFromArray(catalog, i);
            Assert.assertTrue(getIdBookFromArray(catalog, i + 1) > idBookFromArray);
        }
    }

    private int getIdBookFromArray(Catalog catalog, int numberBook) {
        return RegExp.getNumbers(patternForId, catalog.getBooks().get(numberBook).getId());
    }
}
