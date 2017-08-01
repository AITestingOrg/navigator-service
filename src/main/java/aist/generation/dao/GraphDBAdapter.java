package aist.generation.dao;

import aist.generation.models.InnerEdge;
import aist.generation.models.InnerVertex;
import aist.generation.models.Vertex;
import aist.generation.oldModels.GraphNode;

/**
 * Created by justinp on 7/6/17.
 */
public interface GraphDBAdapter<E extends InnerEdge,T extends InnerVertex> {
    public void addVertex(T innerVertex);
    public void addEdge(T from, T to, E innerEdge);
}
