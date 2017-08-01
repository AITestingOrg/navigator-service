package aist.generation.services;

import aist.generation.instruments.InstrumentAdapter;
import aist.generation.models.Page;
import aist.generation.models.Process;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import sun.plugin2.message.helper.URLHelper;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by justinp on 7/26/17.
 */
public class NavigatorService {
    /**
     * todo: make this a URL later
     */
    @Value("${aist.generation.url}")
    private String rootURL;

    @Autowired
    private GraphService graphService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private InstrumentAdapter instrumentAdapter;

    private Set<String> visitedURLs;

    public NavigatorService() {
        this.visitedURLs = new HashSet<>();
    }

    public void run() {
//        Initializes the root page, adds it to the queue and graph service
        Page rootPage = navigate(rootURL);
        Queue<Page> pageQueue = new LinkedList<>();
        pageQueue.add(rootPage);
        graphService.setRoot(rootPage);
        urlService = new UrlService(rootURL);

//        Gets the page at the top of the queue when nonempty
        while (!pageQueue.isEmpty()) {
            Page currentPage = pageQueue.poll();
            currentPage.getChildUrls().forEach(url -> {
//                Checks if the page has been visited before and checks if it is a valid page
                if (!visitedURLs.contains(url) && validate(url)) {
//                    Instantiate the page we are visiting
                    Page toVisit = navigate(url);
//                    Adds the new page to the graph, visited urls, and to the queue
                    addToGraph(currentPage, toVisit, new Process());
                    visitedURLs.add(toVisit.getUrl());
                    pageQueue.add(toVisit);
                }
            });
        }
    }

    private Page navigate(String url) {
        return instrumentAdapter.get(url);
    }

    private boolean validate(String url) {
        return urlService.validate(url);
    }

    private void addToGraph(Page from, Page to, Process process) {
        graphService.add(from, to, process);
    }
}