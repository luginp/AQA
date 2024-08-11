package project.pageobject.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import project.models.Catalog;
import project.models.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ProjectsForm extends Form {
    private ILabel tableLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//table[@class='table']//tbody"), "Table");
    private String testLinkXpath = ".//tr//td//a[contains(text(),'%s')]";
    private ArrayDeque<IElement> elementList = new ArrayDeque<IElement>();
    private String[] testParameters = new String[7];
    private List<String> date = new ArrayList<>();
    private String projectLinkXpath = "//a[contains(text(),'%s')]";
    private Catalog catalog = new Catalog();

    public ProjectsForm() {
        super(By.xpath("//div[contains(@class, 'panel-heading')][contains(text(), 'Available projects')]"), "Projects form");
    }

    public String getProjectId(String name) {
        ILink projectLink = AqualityServices.getElementFactory().getLink(By.xpath(String.format(projectLinkXpath, name)), String.format("%s link", name));
        return projectLink.getHref().substring(projectLink.getHref().length() - 1);
    }

    public String getProjectName(String name) {
        return AqualityServices.getElementFactory().getLink(By.xpath(String.format(projectLinkXpath, name)), "New project").getText();
    }

    public void navigateToProjectPage(String name) {
        AqualityServices.getElementFactory().getLink(By.xpath(String.format(projectLinkXpath, name)),
                String.format("%s project", name)).click();
    }

    public List<String> getTestsDate() {
        List<Test> tests = new ArrayList<>();
        for (IElement element : tableLabel.findChildElements(By.xpath(".//tr"), ElementType.LABEL)) {
            elementList.addFirst(element);
            Test test = new Test();
            if (element.getText().contains("Test name"))
                elementList.pop();
            for (IElement element1 : elementList) {
                for (int i = 0; i < testParameters.length; i++) {
                    if (i == 0) {
                        test.setName(element1.findChildElements(By.xpath(".//td"), ElementType.LABEL).get(i).getText());
                    }
                    if (i == 1) {
                        test.setMethod(element1.findChildElements(By.xpath(".//td"), ElementType.LABEL).get(i).getText());
                    }
                    if (i == 2) {
                        test.setStatus(element1.findChildElements(By.xpath(".//td"), ElementType.LABEL).get(i).getText().toUpperCase());
                    }
                    if (i == 3) {
                        test.setStartTime(element1.findChildElements(By.xpath(".//td"), ElementType.LABEL).get(i).getText());
                        date.add(element1.findChildElements(By.xpath(".//td"), ElementType.LABEL).get(i).getText());
                    }
                    if (i == 4) {
                        test.setEndTime(element1.findChildElements(By.xpath(".//td"), ElementType.LABEL).get(i).getText());
                    }
                }
                tests.add(test);
                elementList.pop();
            }
        }
        catalog.setTests(tests);
        return date;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public String testIsVisible(String newTestName) {
        return tableLabel.findChildElement(By.xpath(String.format(testLinkXpath, newTestName)), ElementType.LINK).getText();
    }

    public void openNewTestPage(String newTestName) {
        tableLabel.findChildElement(By.xpath(String.format(testLinkXpath, newTestName)), ElementType.LINK).click();
    }
}

