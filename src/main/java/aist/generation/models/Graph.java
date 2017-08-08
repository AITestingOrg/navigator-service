package aist.generation.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matthewro on 7/27/17.
 */
public class Graph <E extends InnerEdge,T extends InnerVertex> {

    private final Map<T, Vertex<E,T>> adjacencyList = new HashMap<>();

    public Graph() {
    }

    public boolean addVertex(T innerVertex) {
        if (adjacencyList.containsKey(innerVertex)) {
            return false;
        }
        adjacencyList.put(innerVertex, new Vertex<>(innerVertex));
        return true;
    }

    public boolean addEdge(T from, T to, E info) {
        if (!containsVertex(from) || !containsVertex(to)) {
            return false;
        }

        Vertex<E,T> fromVertex = getVertex(from);
        Vertex<E,T> toVertex = getVertex(to);
        return fromVertex.addEdge(toVertex, info);
    }

    public boolean removeVertex(T innerVertex) {
        if (!adjacencyList.containsKey(innerVertex)) {
            return false;
        }

        final Vertex<E,T> toRemove = getVertex(innerVertex);

        adjacencyList.values().forEach(vertex -> vertex.removeEdge(toRemove));

        adjacencyList.remove(innerVertex);
        return true;
    }

    public boolean removeEdge(T from, T to) {
        return !(!containsVertex(from) || !containsVertex(to)) && getVertex(from).removeEdge(getVertex(to));
    }

    public boolean containsVertex(T innerVertex) {
        return adjacencyList.containsKey(innerVertex);
    }

    public boolean containsEdge(T from, T to) {
        return !(!containsVertex(from) || !containsVertex(to)) && getVertex(from).hasEdge(getVertex(to));
    }

    public Vertex<E,T> getVertex(T innerVertex) {
        return adjacencyList.get(innerVertex);
    }
}
