package alexa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Main {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = getWebDriver();
        AlexaDriver alexaDriver = new AlexaDriver(driver);

        try {
            // Wait for DOM to load fully
            Thread.sleep(2000);

            // Wait until the START button is clickable
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("start-button")));

            // Click the START button
            driver.findElement(By.id("start-button")).click();
            System.out.println("Clicked START button.");

            // Allow time for speech synthesis and recognition
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Do NOT quit here until you're done debugging
    }

    public static WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-speech-dispatcher");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--window-size=1200,800");
        return new ChromeDriver(options);
    }
}
