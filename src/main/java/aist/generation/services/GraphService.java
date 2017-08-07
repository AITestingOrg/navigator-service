package aist.generation.services;

import aist.generation.dao.GraphDBAdapter;
import aist.generation.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by justinp on 7/26/17.
 */
@Service
public class GraphService<E extends InnerEdge,T extends InnerVertex>  implements GraphAdapter<E,T> {

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
        graph.addVertex(innerVertex);
        graphDb.addVertex(innerVertex);
    }

    @Override
    public void addEdge(T from, T to, E innerEdge) {
        graph.addEdge(from, to, innerEdge);
        graphDb.addEdge(from, to, innerEdge);
    }
}
