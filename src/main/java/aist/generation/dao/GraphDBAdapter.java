package aist.generation.dao;

import aist.generation.models.InnerVertex;
import aist.generation.models.Vertex;
import aist.generation.oldModels.GraphNode;

/**
 * Created by justinp on 7/6/17.
 */
public interface GraphDBAdapter<E,T extends InnerVertex> {
    public void addVertex(Vertex<E,T> vertex);
    public void addEdge(Vertex<E,T> from, Vertex<E,T> to, E info);
}
