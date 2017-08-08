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
    private GraphService<Process, Page> graphService;

    private UrlService urlService;

    @Autowired
    private InstrumentAdapter instrumentAdapter;

    @Autowired
    private Intelligence intelligence;

    public NavigatorService() {
    }

    public void run() {
        try {
//        Initializes the root page, adds it to the queue and graph service
            Page rootPage = navigate(rootURL);
            Queue<Page> pageQueue = new LinkedList<>();
            pageQueue.add(rootPage);
            graphService.setRoot(rootPage);
            urlService = new UrlService(rootURL);
//        Gets the page at the top of the queue when nonempty
            while (!pageQueue.isEmpty()) {
                Page currentPage = pageQueue.poll();
                System.out.println("Currently at: " + currentPage.getUrl());
                currentPage.getChildUrls().forEach(url -> {
                    if (validate(url)) {
                        Page toVisitStub = new Page.PageBuilder().setUrl(url).build();
                        Page toVisit = graphService.getVertex(toVisitStub);
                        if (toVisit == null) {
                            toVisit = navigate(url);
                            addVertex(toVisit);
                            pageQueue.add(toVisit);
                        }
                        addEdge(currentPage, toVisit, new Process());
                    }
                });
            }
        } finally {
            after();
        }
    }

    private void after() {
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
        graphService.addVertex(page);
    }

    private void addEdge(Page from, Page to, Process process) {
        graphService.addEdge(from, to, process);
    }
}