package aist.generation.services;

import aist.generation.dao.GraphDBAdapter;
import aist.generation.models.Graph;
import aist.generation.models.Process;
import aist.generation.models.Page;
import aist.generation.oldModels.GraphNode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by justinp on 7/26/17.
 */
public class GraphService {
    @Autowired
    private GraphDBAdapter graphDb;

    private Graph<Process, Page> graph;

    private Page root;

    public GraphService(Page root) {
        this.root = root;
        this.graph = new Graph<>();
        this.graph.addNode(root);
    }

    public void setRoot(Page root) {
        this.root = root;
    }

    public void add(Page from, Page to, Process process) {
        graphDb.addVertex(new GraphNode());
        graphDb.addEdge(from.getUrl(), to.getUrl());
        graph.addEdge(from, to, process);
    }

}
