import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Selenium {
    WebDriver webDriver;
    public static void main(String[] args){
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", true);
        driver = new FirefoxDriver(options);

        String url = "https://search.damai.cn/";
        driver.get(url);

        WebElement saveButton = driver.findElement(By.className("btn-search"));
    }

    public void initWebDriver(){
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", true);
        webDriver = new FirefoxDriver(options);
    }
    public void setUrl(String url){
        webDriver.get(url);
    }
    //1
    boolean buttonEnableCheck(WebDriver webDriver,String className){
        WebElement saveButton = webDriver.findElement(By.className(className));
        return saveButton.isEnabled();
    }
    //1
    boolean buttonDisplayCheck(WebDriver webDriver,String className){
        WebElement saveButton = webDriver.findElement(By.className(className));
        return saveButton.isDisplayed();
    }
    //2 通过遍历所有的城市，找到target，然后click
    void singleSelect(WebDriver webDriver,String className, String target){
        List<WebElement> webElements = webDriver.findElements(By.className(className));
        for(WebElement webElement : webElements){
            String context = webElement.getText();
            if(context.equals(target)){
                webElement.click();
            }
        }
    }
    //3
    String setTextContent(WebDriver webDriver, String className, String content){
        WebElement webElement = webDriver.findElement(By.className(className));
        webElement.clear();
        webElement.sendKeys(content);
        return webElement.getAttribute("value");
    }
    //4
    List<WebElement> downDragFrame(WebDriver webDriver, String className, String elementsClassName){
        WebElement webElement = webDriver.findElement(By.className(className));
        webElement.click();
        return webDriver.findElements(By.className(elementsClassName));
    }
    //5
    void setCookie(WebDriver webDriver, String key, String value){
        Cookie cookie = new Cookie(key, value);
        webDriver.manage().addCookie(cookie);
    }

}
