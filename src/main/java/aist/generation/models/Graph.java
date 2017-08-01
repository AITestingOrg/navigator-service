package aist.generation.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matthewro on 7/27/17.
 */
public class Graph <E,T extends InnerVertex> {

    private final Map<T, Vertex<E,T>> adjacencyList = new HashMap<>();

    public Graph() {
    }

    public boolean addNode(T node) {
        if (adjacencyList.containsKey(node)) {
            return false;
        }
        adjacencyList.put(node, new Vertex<>(node));
        return true;
    }

    public boolean addEdge(T from, T to, E info) {
        if (!containsNode(from) || !containsNode(to)) {
            return false;
        }

        Vertex<E,T> fromVertex = getVertex(from);
        Vertex<E,T> toVertex = getVertex(to);
        return fromVertex.addEdge(toVertex, info);
    }

    public boolean removeNode(T node) {
        if (!adjacencyList.containsKey(node)) {
            return false;
        }

        final Vertex<E,T> toRemove = getVertex(node);

        adjacencyList.values().forEach(vertex -> vertex.removeEdge(toRemove));

        adjacencyList.remove(node);
        return true;
    }

    public boolean removeEdge(T from, T to) {
        return !(!containsNode(from) || !containsNode(to)) && getVertex(from).removeEdge(getVertex(to));
    }

    public boolean containsNode(T node) {
        return adjacencyList.containsKey(node);
    }

    public boolean containsEdge(T from, T to) {
        return !(!containsNode(from) || !containsNode(to)) && getVertex(from).hasEdge(getVertex(to));
    }

    private Vertex<E,T> getVertex(T node) {
        return adjacencyList.get(node);
    }
}
