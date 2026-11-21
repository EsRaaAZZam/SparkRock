package com.testingauto.pages;

import com.testingauto.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private final By cartRows = By.cssSelector("#tbodyid tr");
    private final By totalPrice = By.id("totalp");
    private final By placeOrderButton = By.cssSelector("button[data-target='#orderModal']");

    public CartPage(WebDriver driver) {
        super(driver);
    }



    public int getTotal() {
        wait.until(d -> !getText(totalPrice).isEmpty());
        return Integer.parseInt(getText(totalPrice));
    }

    public boolean containsProduct(String productName) {
        try {
            wait.until(d -> d.findElements(cartRows).stream()
                    .anyMatch(row -> row.getText().contains(productName)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public OrderPage placeOrder() {
        click(placeOrderButton);
        return new OrderPage(driver);
    }
}