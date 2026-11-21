package com.testingauto.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    protected WebElement waitForVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected  void  type (By locator  , String text){
        WebElement element =waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click (By locator){
         wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public  String acceptAlert(){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }
    protected  boolean isDisplayed(By locator){
        try{
            return waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return  false;
        }
    }

    protected  String getText (By locator){
        return waitForVisible(locator).getText().trim();
    }


}
