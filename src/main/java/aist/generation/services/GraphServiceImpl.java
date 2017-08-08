package aist.generation.services;

import aist.generation.dao.GraphDBAdapter;
import aist.generation.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by justinp on 7/26/17.
 */
@Service
public class GraphServiceImpl<E extends InnerEdge,T extends InnerVertex>  implements GraphService<E,T> {

    @Autowired
    private GraphDBAdapter graphDb;

    private InnerVertex root;

    private Graph<E,T> graph = new Graph<>();

    @Override
    public void setRoot(T root) {
        this.root = root;
        addVertex(root);
    }

    @Override
    public void addVertex(T innerVertex) {
        if (graph.addVertex(innerVertex)) {
            graphDb.addVertex(innerVertex);
        }
    }

    @Override
    public void addEdge(T from, T to, E innerEdge) {
        if (graph.addEdge(from, to, innerEdge)) {
            graphDb.addEdge(from, to, innerEdge);
        }
    }

    @Override
    public T getVertex(T innerVertexStub) {
        Vertex<E,T> v = graph.getVertex(innerVertexStub);
        if (v != null) {
            return v.getInnerVertex();
        } else {
            return null;
        }
    }
}
