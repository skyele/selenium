import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.jws.WebMethod;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SeleniumTest {
    Selenium selenium;
    WebDriver webDriver;
    @Before
    public void initWebDriver(){
        selenium = new Selenium();
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", true);
        selenium.webDriver = new FirefoxDriver(options);
        String url = "https://search.damai.cn/";
        selenium.webDriver.get(url);
        webDriver = selenium.webDriver;
    }

    @Test
    public void buttonEnableCheck() {
        String className = "btn-search";
        boolean ret = selenium.buttonEnableCheck(selenium.webDriver, className);
        assert(ret == true);
    }

    @Test
    public void buttonDisplayCheck() {
        String className = "btn-search";
        boolean ret = selenium.buttonDisplayCheck(selenium.webDriver, className);
        assert (ret == true);
    }

    @Test
    public void singleSelect() {
        WebElement before = selenium.webDriver.findElement(By.className("factor-selected-city"));
        String target = "上海";
        assert (!before.getText().equals(target));
        selenium.singleSelect(selenium.webDriver, "factor-content-item", target);
        WebElement after = selenium.webDriver.findElement(By.className("factor-selected-city"));
        assert (after.getText().equals(target));
        selenium.singleSelect(selenium.webDriver, "factor-content-item", "演唱会");
        selenium.singleSelect(selenium.webDriver, "factor-content-item", "流行");
        selenium.singleSelect(selenium.webDriver, "factor-content-item", "明天");
    }

    @Test
    public void setTextContent() {
        String content = selenium.setTextContent(selenium.webDriver, "input-search", "天津");
        assert (content.equals("天津"));
        WebElement webElement = webDriver.findElement(By.className("btn-search"));
        webElement.click();
        content = selenium.setTextContent(selenium.webDriver, "input-search", "蔡徐坤");
        assert (content.equals("蔡徐坤"));
        webElement.click();
        content = selenium.setTextContent(selenium.webDriver, "input-search", "周杰伦");
        assert (content.equals("周杰伦"));
        webElement.click();
    }

    @Test
    public void searchAndSelect() {
        String content = selenium.setTextContent(selenium.webDriver, "input-search", "周杰伦");
        assert (content.equals("周杰伦"));
        WebElement webElement = webDriver.findElement(By.className("btn-search"));
        webElement.click();
        WebElement before = selenium.webDriver.findElement(By.className("factor-selected-city"));
        String target = "上海";
        assert (!before.getText().equals(target));
        selenium.singleSelect(selenium.webDriver, "factor-content-item", target);
        WebElement after = selenium.webDriver.findElement(By.className("factor-selected-city"));
        assert (after.getText().equals(target));
    }


    @Test
    public void downDragFrame() {
        List<WebElement> before = selenium.webDriver.findElements(By.className("factor-content-item"));
        List<WebElement> after = selenium.downDragFrame(selenium.webDriver, "factor-more", "factor-content-item");
        assert (before.size() <= after.size());
    }

    @Test
    public void setCookie() {
        selenium.setCookie(selenium.webDriver, "name", "sky");
        Set<Cookie> cookies = selenium.webDriver.manage().getCookies();
        for (Cookie c : cookies){
            if(c.getName() == "name")
                assert (c.getValue().equals("sky"));
        }
//        assert (false);
    }

    @Test
    //6 测试跳转到登录页面，并显示登录按钮
    public void hrefEnabled(){
        WebElement webElement = webDriver.findElement(By.className("user-header"));
        webElement.click();
        System.out.println(webDriver.getCurrentUrl());
        for (String s: webDriver.getWindowHandles())
            webDriver.switchTo().window(s);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(By.id("alibaba-login-iframe"));
        webDriver.switchTo().frame("alibaba-login-box");
        assertEquals(true, webDriver.findElement(By.className("fm-submit")).isDisplayed());
    }
}