package aist.generation.services;

import aist.generation.models.InnerEdge;
import aist.generation.models.InnerVertex;
import org.springframework.stereotype.Service;

/**
 * Created by matthewro on 8/7/17.
 */
@Service
public interface GraphAdapter<E extends InnerEdge, T extends InnerVertex> {
    void setRoot(T root);
    void addVertex(T innerVertex);
    void addEdge(T from, T to, E innerEdge);
}
