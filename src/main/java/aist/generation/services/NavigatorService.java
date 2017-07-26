package aist.generation.services;

import aist.generation.instruments.InstrumentAdapter;
import aist.generation.models.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by justinp on 7/26/17.
 */
public class NavigatorService {
    /**
     * todo: make this a URL later
     */
    @Value("${aist.generation.url}")
    private String root;

    @Autowired
    private GraphService graphService;

    @Autowired
    private InstrumentAdapter instrumentAdapter;

    private Set<String> visited;

    public NavigatorService() {
        this.visited = new HashSet<>();
    }

    public void run() {
        Queue<String> pageQueue = new LinkedList<>();
        pageQueue.add(root);
        boolean firstPage = true;
        while (!pageQueue.isEmpty()) {
            Page currentPage = navigate(pageQueue.poll());
            if (firstPage) {
                graphService.setRoot(currentPage);
                firstPage = false;
            }

            currentPage.getChildUrls().stream().forEach(url -> {
                if (!visited.contains(url)) {
                    visited.add(url);
                    pageQueue.add(url);
                }
            });
        }
    }

    private Page navigate(String url) {
        return instrumentAdapter.get(url);
    }
}