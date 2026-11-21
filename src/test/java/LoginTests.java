import com.testingauto.pages.HomePage;
import com.testingauto.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginTests extends BaseTest{
    private  String username;

    @BeforeMethod
     public  void registerNewUser(){
        username=registerNewUSer();
    }

    @Test (description = "TC-04 :Login with valid credentials")
            public  void loginWithValid(){
        HomePage home =new HomePage(driver).openLogin().
                enterUsername(username)
                .enterPassword(TEST_PASSWORD)
                .login();

        Assert.assertTrue(home.isLogoutBtn());
        Assert.assertEquals(home.getWelcomeMessge(),"Welcome "+ username);
    }

    @Test (description = "TC-04 :Login with valid credentials")
    public  void loginWithinValid(){
        LoginPage login =new HomePage(driver).openLogin();
            String alertTex= login.enterUsername(username)
                .enterPassword("dwesdewed3ew")
                        .submitAndgetAlert();

        Assert.assertEquals(alertTex,"Wrong password.");
    }
}
