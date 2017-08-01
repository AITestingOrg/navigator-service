package aist.generation.services;

import aist.generation.dao.GraphDBAdapter;
import aist.generation.models.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by justinp on 7/26/17.
 */
public class GraphService<E extends InnerEdge, T extends InnerVertex> {

    @Autowired
    private GraphDBAdapter<E,T> graphDb;

    @Autowired
    private T root;

    private Graph<E, T> graph;

    public GraphService(T root) {
        this.root = root;
        this.graph = new Graph<>();
        this.graph.addVertex(root);
    }

    public void setRoot(T root) {
        this.root = root;
        graph.addVertex(root);
    }

    public void addVertex(T innerVertex) {
        graph.addVertex(innerVertex);
        graphDb.addVertex(innerVertex);
    }

    public void addEdge(T from, T to, E innerEdge) {
        graph.addEdge(from, to, innerEdge);
        graphDb.addEdge(from, to, innerEdge);
    }

}
