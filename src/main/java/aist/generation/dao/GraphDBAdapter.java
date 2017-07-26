package aist.generation.dao;

import aist.generation.models.GraphNode;

/**
 * Created by justinp on 7/6/17.
 */
public interface GraphDBAdapter {
    public void addNode(GraphNode graphNode);
    public void addEdge(String from, String to);
}
