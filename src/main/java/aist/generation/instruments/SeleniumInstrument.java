package aist.generation.instruments;

import aist.generation.models.LoginForm;
import aist.generation.models.Page;
import aist.generation.services.ClientAdapter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by justinp on 7/26/17.
 */
@Service
public class SeleniumInstrument implements InstrumentAdapter {

    private String driver;

    private String driverPath;

    private WebDriver webDriver;

    @Autowired
    public SeleniumInstrument(
            @Value("${aist.generation.selenium.driver}") String driver,
            @Value("${aist.generation.selenium.driver-path}") String driverPath) {
        System.setProperty(driver, driverPath);
        webDriver = new ChromeDriver();
    }

    /**
     * todo: refactor this, this should have some form of intelligence
     * @param url
     * @return
     */
    @Override
    public Page get(String url) {
        webDriver.get(url);
        Set<String> hyperLinkSet = webDriver.findElements(By.tagName("a")).stream().map(webElement -> webElement.getAttribute("href")).collect(Collectors.toSet());
        String title = webDriver.getTitle();
        return (new Page.PageBuilder())
                .setChildUrls(hyperLinkSet)
                .setTitle(title)
                .setUrl(url)
                .setHtml(getPageSource())
                .build();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    /**
     * Assumes the page given to it has already been classified as a login page
     * @param page
     */
    @Override
    public void fillLoginForm(Page page) {
        //TODO: Read comment below
        /* Write a closest distance algorithm to find most probable boxes if there are multiple elements with the
        words password or login inside the elements */

        ArrayList<WebElement> elements = (ArrayList<WebElement>) webDriver.findElements(By.tagName("input"));
        LoginForm loginForm = new LoginForm();
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        elements.forEach(webElement -> {
            Object attributes = executor.executeScript("var items = {}; " +
                    "for (index = 0; index < arguments[0].attributes.length; ++index) " +
                    "{ items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; " +
                    "return items;", webElement);

            if (loginForm.getLoginBox() == null &&
                    attributes.toString().contains("login")) {
                loginForm.setLoginBox(webElement);
            }

            else if (loginForm.getPasswordBox() == null &&
                    attributes.toString().contains("password")) {
                loginForm.setPasswordBox(webElement);
            }
        });
        sendKeys(loginForm);
    }

    public void sendKeys(LoginForm form) {
        form.getLoginBox().sendKeys();
        form.getPasswordBox().sendKeys();
        form.getPasswordBox().sendKeys(Keys.RETURN);
    }
}
