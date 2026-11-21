
import com.testingauto.pages.CartPage;
import com.testingauto.pages.HomePage;
import com.testingauto.pages.OrderPage;
import com.testingauto.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    private static final String PRODUCT_NAME = "Samsung galaxy s6";

    @Test(description = "TC-13: complete a purchase end-to-end (login -> product -> cart -> order)")
    public void completeAPurchase() {
        String username = registerNewUSer();

        HomePage home = new HomePage(driver).openLogin()
                .enterUsername(username)
                .enterPassword(TEST_PASSWORD)
                .login();

        ProductPage product = home.openProduct(PRODUCT_NAME);
        String addedAlert = product.addToCart();
        Assert.assertEquals(addedAlert, "Product added.");

        CartPage cart = product.goToCart();
        Assert.assertTrue(cart.containsProduct(PRODUCT_NAME));
        int total = cart.getTotal();

        OrderPage order = cart.placeOrder();
        order.fillOrderDetails("Test User", "Egypt", "Cairo", "4111111111111111", "12", "2026");
        order.purchase();

        Assert.assertEquals(order.getConfirmationTitle(), "Thank you for your purchase!");
        Assert.assertTrue(order.getConfirmationDetails().contains("Amount: " + total + " USD"),
                "Expected confirmation to show Amount: " + total + " USD but was: "
                        + order.getConfirmationDetails());

        order.closeConfirmation();
    }
}