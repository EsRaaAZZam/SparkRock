package com.testingauto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage{

    private  final By usernameInput = By.id ("sign-username");
    private  final By passwordInput = By.id ("sign-password");

    private  final By signupButton = By.cssSelector ("#signInModal .btn-primary");

    private  final By closeButton = By.id ("#signInModal .btn-secondary-username");


    public SignUpPage(WebDriver driver) {
        super(driver);
        waitForVisible(usernameInput);
    }


    public SignUpPage  enterUsername (String username){
        type(usernameInput,username);
        return this;
    }
    public SignUpPage  enterPassword (String password){
        type(passwordInput,password);
        return  this;
    }
    public  void close(){
        click(closeButton);
    }

    public  String  submit(){
        click(signupButton);
       return acceptAlert();
    }
}
