package aist.generation.instruments;

import aist.generation.models.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by justinp on 7/26/17.
 */
@Service
public class SeleniumInstrument implements InstrumentAdapter {
    @Value("${aist.generation.selenium.driver}")
    private String driverLabel;
    @Value("${aist.generation.selenium.driver-path}")
    private String driverPath;
    private WebDriver webDriver;

    public SeleniumInstrument() {
//        System.setProperty(driverLabel, driverPath);
//        this.webDriver = new ChromeDriver();
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
                .setPagetype(null)
                .setTitle(title)
                .setUrl(url)
                .build();
    }
}
