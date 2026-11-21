package com.testingauto.pages;

import com.testingauto.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    // ===== Locators =====
    private final By cartRows = By.cssSelector("#tbodyid tr");
    private final By totalPrice = By.id("totalp");
    private final By placeOrderButton = By.cssSelector("button[data-target='#orderModal']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ===== Actions =====
    public int getRowCount() {
        return driver.findElements(cartRows).size();
    }

    /** Cart rows load asynchronously; the row the caller expects may not be rendered yet. */
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

    public void deleteProduct(String productName) {
        WebElement row = driver.findElements(cartRows).stream()
                .filter(r -> r.getText().contains(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Product not in cart: " + productName));
        row.findElement(By.linkText("Delete")).click();
        wait.until(d -> !isDisplayed(By.xpath(
                "//tr[td[contains(text(),'" + productName + "')]]")));
    }

    public OrderPage placeOrder() {
        click(placeOrderButton);
        return new OrderPage(driver);
    }
}