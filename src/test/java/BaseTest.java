import com.testingauto.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import  org.testng.annotations.*;

public class BaseTest {
    static Properties prop = new Properties();
    protected WebDriver driver;
     protected static final String Base_URL = System.getProperty("baseUrl", loadConfigURL().getProperty("baseUrl"));
    protected static final String TEST_PASSWORD = System.getProperty("testPassword", loadConfigURL().getProperty("testPassword"));



    private static Properties loadConfigURL(){
        try (InputStream  in = BaseTest.class.getClassLoader().getResourceAsStream("testData.properties")) {
            if (in == null) {
                throw new IllegalAccessException("config not loaded");
            }
            prop.load(in);

        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
         return  prop;

    }


    @BeforeMethod
    public void setUp(){
         ChromeOptions options = new ChromeOptions();
         driver=new ChromeDriver(options);
         driver.get(Base_URL);
    }

    @AfterMethod
    public  void  tearDown(){
         if (driver!= null){
             driver.quit();
         }

    }

    protected  String registerNewUSer()
    {
        String username  = "user_"+ System.currentTimeMillis();
        new HomePage(driver).openSignUp()
                .enterUsername(username)
                .enterPassword (TEST_PASSWORD)
                .submit();

        driver.navigate().refresh();
        return username;
    }
}