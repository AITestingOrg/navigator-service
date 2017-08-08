package aist.generation.instruments;

import aist.generation.models.Page;
import aist.generation.services.ClientAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
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
        try {
            //TODO: Find a better way to store the html
            PrintWriter writer = new PrintWriter("/Users/bryant/Desktop/Modular/test.html", "UTF-8");
            writer.append(webDriver.getPageSource());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webDriver.getPageSource();
    }
}
