package com.testingauto.pages;

import com.testingauto.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends BasePage {

    private final By nameInput = By.id("name");
    private final By countryInput = By.id("country");
    private final By cityInput = By.id("city");
    private final By cardInput = By.id("card");
    private final By monthInput = By.id("month");
    private final By yearInput = By.id("year");
    private final By purchaseButton = By.cssSelector("#orderModal .btn-primary");
    private final By confirmationTitle = By.cssSelector(".sweet-alert h2");
    private final By confirmationDetails = By.cssSelector(".sweet-alert p.lead");
    private final By confirmationOkButton = By.cssSelector(".sweet-alert .confirm");

    public OrderPage(WebDriver driver) {
        super(driver);
        waitForVisible(nameInput);
    }

    public OrderPage fillOrderDetails(String name, String country, String city,
                                       String card, String month, String year) {
        type(nameInput, name);
        type(countryInput, country);
        type(cityInput, city);
        type(cardInput, card);
        type(monthInput, month);
        type(yearInput, year);
        return this;
    }

    public void purchase() {
        click(purchaseButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationTitle));
    }

    public String getConfirmationTitle() {
        return getText(confirmationTitle);
    }

    public String getConfirmationDetails() {
        return getText(confirmationDetails);
    }

    public void closeConfirmation() {
        click(confirmationOkButton);
    }
}