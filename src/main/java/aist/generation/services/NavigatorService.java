package aist.generation.services;

import aist.generation.instruments.InstrumentAdapter;
import aist.generation.models.Page;
import aist.generation.models.PageType;
import aist.generation.models.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by justinp on 7/26/17.
 */
@Service
public class NavigatorService {
    /**
     * todo: make this a URL later
     */

    @Value("${aist.generation.url}")
    private String rootURL;

    @Autowired
    private GraphAdapter<Process, Page> graphAdapter;

    private UrlService urlService;

    @Autowired
    private InstrumentAdapter instrumentAdapter;

    @Autowired
    private Intelligence intelligence;

    private Set<String> visitedURLs;

    public NavigatorService() {
        this.visitedURLs = new HashSet<>();
    }

    public void run() {
        try {
            System.out.println("Starting...");
            System.out.println("ROOT URL: " + rootURL);
//        Initializes the root page, adds it to the queue and graph service
            Page rootPage = navigate(rootURL);
            rootPage.setPageType(classify(rootPage));
            Queue<Page> pageQueue = new LinkedList<>();
            pageQueue.add(rootPage);
            graphAdapter.setRoot(rootPage);
            urlService = new UrlService(rootURL);

//        Gets the page at the top of the queue when nonempty
            while (!pageQueue.isEmpty()) {
                Page currentPage = pageQueue.poll();
                currentPage.setPageType(classify(currentPage));
                System.out.println("Currently at: " + currentPage.getUrl());
                currentPage.getChildUrls().forEach(url -> {
                    System.out.println("child url: " + url);
//                Checks if the page has been visited before and checks if it is a valid page
                    if (!visitedURLs.contains(url) && validate(url)) {
//                    Instantiate the page we are visiting
                        Page toVisit = navigate(url);
                        toVisit.setPageType(classify(toVisit));
//                    Adds the new page to the graph, visited urls, and to the queue
                        addVertex(toVisit);
                        addEdge(currentPage, toVisit, new Process());
                        visitedURLs.add(toVisit.getUrl());
                        pageQueue.add(toVisit);
                    }
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
            afterRun();
        }

        afterRun();
    }

    private void afterRun() {
        instrumentAdapter.quit();
    }

    private Page navigate(String url) {
        return instrumentAdapter.get(url);
    }

    private PageType classify(Page page) { return intelligence.classify(page); }

    private boolean validate(String url) {
        return urlService.validate(url);
    }

    private void addVertex(Page page) {
        graphAdapter.addVertex(page);
    }

    private void addEdge(Page from, Page to, Process process) {
        graphAdapter.addEdge(from, to, process);
    }
}