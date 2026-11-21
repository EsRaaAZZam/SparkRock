package com.testingauto.pages;

import com.testingauto.pages.BasePage;
import com.testingauto.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    // ===== Locators =====
    private final By productName = By.className("name");
    private final By productPrice = By.className("price-container");
    private final By addToCartLink = By.cssSelector("a[onclick^='addToCart']");
    private final By cartLink = By.id("cartur");

    public ProductPage(WebDriver driver) {
        super(driver);
        waitForVisible(productName);
    }

    // ===== Actions =====
    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    /** Clicks Add to cart and dismisses the confirmation alert. */
    public String addToCart() {
        click(addToCartLink);
        return acceptAlert();
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}