package alexa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AlexaDriver {

    private final WebDriver driver;

    public AlexaDriver(WebDriver driver) {
        this.driver = driver;
        loadClientSideScript();
    }

    public void loadClientSideScript() {
        try {
            driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/index.html");
            sleep(2000);
            System.out.println("Loaded index.html successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String executeScript(String script) {
        try {
            return ((JavascriptExecutor) driver).executeScript(script).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
