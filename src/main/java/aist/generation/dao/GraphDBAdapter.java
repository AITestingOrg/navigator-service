package aist.generation.dao;

import aist.generation.models.InnerEdge;
import aist.generation.models.InnerVertex;
import org.springframework.stereotype.Service;

/**
 * Created by justinp on 7/6/17.
 */
@Service
public interface GraphDBAdapter {
    void addVertex(InnerVertex innerVertex);
    void addEdge(InnerVertex from, InnerVertex to, InnerEdge innerEdge);
    void clearDatabase();
}
