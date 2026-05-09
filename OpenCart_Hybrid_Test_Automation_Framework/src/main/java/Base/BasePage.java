package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BasePage {
    public WebDriver driver;
    public Properties prop;

    public WebDriver init_driver() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
            prop.load(ip);
        } catch (Exception e) {
            System.out.println("Error loading config file. Check path: ./src/test/resources/config.properties");
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("url"));
        return driver;
    }
}