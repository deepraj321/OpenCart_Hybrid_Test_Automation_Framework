package Tests;

import Base.BasePage;
import Pages.*;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;

public class FunctionalTests extends BasePage {
    Login lp;
    ProductPage pp;
    CartPage cp;
    CheckoutPage checkout;

    // Simple counters
    int pass = 0;
    int fail = 0;

    @BeforeClass
    public void setup() {
        init_driver();
        lp = new Login(driver);
        pp = new ProductPage(driver);
        cp = new CartPage(driver);
        checkout = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void testAPI() {
        System.out.println("Executing: API Health Check");
        int code = RestAssured.get(prop.getProperty("url")).getStatusCode();
        Assert.assertEquals(code, 200);
        pass++; // Only increments if Assert passes
    }

    @Test(priority = 2)
    public void testLogin() {
        System.out.println("Executing: Login Validation");
        lp.doLogin(prop.getProperty("email"), prop.getProperty("password"));
        Assert.assertTrue(driver.getTitle().contains("My Account"));
        pass++;
    }

    @Test(priority = 3)
    public void testSearch() {
        System.out.println("Executing: Product Search");
        pp.searchProduct("MacBook");
        Assert.assertTrue(driver.getPageSource().contains("MacBook"));
        pass++;
    }

    @Test(priority = 4)
    public void testCartFailure() {
        System.out.println("Executing: Cart Count Check (Should Fail)");
        // We expect failure here to show the report works
        Assert.assertTrue(cp.getCartSummary().contains("10 item(s)"), "Count Mismatch!");
        pass++;
    }

    @Test(priority = 5)
    public void testCurrency() {
        System.out.println("Executing: Currency Switch");
        pp.switchCurrencyToEuro();
        Assert.assertTrue(cp.getCartSummary().contains("€"));
        pass++;
    }

    @Test(priority = 6)
    public void testCheckout() {
        System.out.println("Executing: Checkout Navigation");
        checkout.goToCheckout();
        Assert.assertTrue(driver.getTitle().contains("Checkout"));
        pass++;
    }

    @AfterMethod
    public void checkStatus(org.testng.ITestResult result) {
        // This is the "Smart" way a 1-2 year guy tracks failures
        if (result.getStatus() == org.testng.ITestResult.FAILURE) {
            fail++;
            System.out.println("STATUS: " + result.getName() + " has FAILED");
        } else {
            System.out.println("STATUS: " + result.getName() + " has PASSED");
        }
    }

    @AfterClass
    public void generateSummary() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("    FINAL TEST SUMMARY");
        System.out.println("=".repeat(30));
        System.out.println("TOTAL PASSED: " + pass);
        System.out.println("TOTAL FAILED: " + fail);
        System.out.println("TOTAL RUN:    " + (pass + fail));
        System.out.println("=".repeat(30));

        if (driver != null) driver.quit();
    }

    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add("testng.xml"); 
        testng.setTestSuites(suites);
        testng.run();
    }
}