package com.testingauto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage  extends BasePage{

    private  final By usernameInput = By.id ("loginusername");
    private  final By passwordInput = By.id ("loginpassword");
    private  final By loginButton = By.cssSelector ("#logInModal .btn-primary");
    private  final By closeButton = By.cssSelector ("#logInModal .btn-secondary");


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage  enterUsername (String username){
        type(usernameInput,username);
        return this;
    }
    public LoginPage  enterPassword (String password){
        type(passwordInput,password);
        return this;
    }
    public  void close(){
        click(closeButton);
    }

    public  HomePage login(){
        click(loginButton);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("nameofuser")));
        return new HomePage(driver);
    }

    public  String  submitAndgetAlert(){
        click(loginButton);
        return acceptAlert();
    }
}

