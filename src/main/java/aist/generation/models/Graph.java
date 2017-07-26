package aist.generation.models;

import aist.generation.dao.GraphDBAdapter;
import org.openqa.selenium.WebDriver;
import java.util.LinkedList;

public class Graph {
    private Node head;
    private Node temp;
    private LinkedList<Node> queue;
    private GraphDBAdapter g;

    public Graph(Node node, GraphDBAdapter g){
        this.head = node;
        this.temp = node;
        this.queue = new LinkedList<>();
        this.g = g;
    }

    public Node top(){ return this.temp; }

    public void transition(){ this.temp = this.queue.peek(); }

    public void addToNodes(Node node){ this.queue.add(node); }

    public boolean graphQueueEmpty(){ return this.queue.isEmpty(); }

    public void removeFromQueue(){
        if(this.queue.size() > 0)
            this.queue.remove();
    }

    public void addToGraph(WebDriver driver, String nextUrl, String visit){
        Page newPage = new Page(nextUrl, PageType.NONE);
        newPage.addTitle(driver.getTitle());
        newPage.visit(visit);
        newPage.visit(nextUrl);
        Node node = new Node(newPage);
        this.top().getChildren().add(node);
        GraphNode parent = new GraphNode(null, driver.getTitle(),this.temp.getCurrentPage().getUrl());
        g.addNode(new GraphNode(parent, driver.getTitle(), nextUrl));

        if(nextUrl.contains("http://www.zerorezatlanta.com/"))
            this.addToNodes(node);
    }
}
