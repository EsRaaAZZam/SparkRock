package com.testingauto.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;


public class HomePage extends  BasePage{


    private  final By signupLink = By.id("signin2");
    private  final By loginLink = By.id("login2");
    private final By welcomeUser= By.id("nameofuser");
    private final By logoutButton= By.id("logout2");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public  SignUpPage openSignUp(){
        click(signupLink);
        return  new SignUpPage(driver);
    }

    public  LoginPage openLogin(){
        click(loginLink);
        return  new LoginPage(driver);
    }
   public  String getWelcomeMessge(){
        return  getText(welcomeUser);

   }

    public  Boolean isLogoutBtn(){
        return  isDisplayed(logoutButton);

    }
}
