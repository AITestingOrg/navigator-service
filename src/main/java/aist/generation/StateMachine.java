//package aist.generation;
//
//import aist.generation.dao.GraphDBAdapter;
//import aist.generation.oldModels.Graph2;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import java.util.List;
//
//
//public class StateMachine {
//
//    static GraphDBAdapter g;
//
//    public static void main(String[] args) {
//
//        boolean first = false;
//
//        while (!graph.graphQueueEmpty() || !first) {
//            while (!exploreLink(driver, graph)) {
//                driver.get(url);
//            }
//
//            graph.removeFromQueue();
//            graph.transition();
//            url = graph.top().getCurrentPage().getUrl();
//            first = true;
//            driver.get(url);
//        }
//
//        driver.quit();
//    }
//
//    public static boolean exploreLink(WebDriver driver, Graph2 graph) {
//        List<WebElement> elements = driver.findElements(By.tagName("a"));
//        String currentUrl = "";
//        String nextUrl = "";
//        String visit = "";
//        int linksentered = 0;
//
//        for (WebElement element : elements) {
//            try {
//                if (element.getText().isEmpty()) {
//                    linksentered++;
//                    continue;
//                }
//
//                if (currentUrl.length() == 0) {
//                    currentUrl = driver.getCurrentUrl();
//                }
//
//                if (!graph.top().inGraph(graph.top(), element.getText())) {
//                    // Trying to account for anchor tags
//                    if (element.getAttribute("href").indexOf('#') == element.getAttribute("href").indexOf('/', 8) + 1) {
//                        linksentered++;
//                        continue;
//                    }
//
//                    visit = element.getText();
//                    element.click();
//                }
//
//                else if (graph.top().inGraph(graph.top(), element.getText())) {
//                    linksentered++;
//                    continue;
//                }
//
//                if (driver.getWindowHandles().size() > 1) {
//                    driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
//                    nextUrl = driver.getCurrentUrl();
//
//                    if (nextUrl != null && !currentUrl.equals(nextUrl)) {
//                        graph.addToGraph(driver, nextUrl, visit);
//                        linksentered++;
//                    }
//
//                    driver.close();
//                    driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
//                    return linksentered >= elements.size();
//                }
//
//                if (graph.top().inGraph(graph.top(), driver.getCurrentUrl())) {
//                    graph.top().getCurrentPage().visit(visit);
//                    linksentered++;
//                    return linksentered >= elements.size();
//                }
//
//                nextUrl = driver.getCurrentUrl();
//
//                if (nextUrl != null && !currentUrl.equals(nextUrl)) {
//                    graph.addToGraph(driver, nextUrl, visit);
//                    linksentered++;
//                    return linksentered >= elements.size();
//                }
//            }
//
//            catch (Exception e) {
//                linksentered++;
//            }
//        }
//
//        return linksentered >= elements.size();
//    }
//}
