package aist.generation.models;

import java.util.List;
import java.util.Optional;

/**
 * Created by matthewro on 7/27/17.
 */
public class Vertex<E, T extends InnerVertex> {

    private T innerVertex;
    private List<Edge<E,T>> edges;
    private Vertex parent;

    public Vertex(T innerVertex) {
        this.innerVertex = innerVertex;
    }

    public boolean addEdge(Vertex<E,T> vertex, E info) {
        if (hasEdge(vertex)) {
            return false;
        } else {
            Edge<E,T> edge = new Edge<>(this, vertex, info);
            return edges.add(edge);
        }
    }

    public boolean removeEdge(Vertex<E,T> vertex) {
        Optional<Edge<E,T>> optional = findEdge(vertex);
        return optional.map(edge -> edges.remove(edge)).orElse(false);
    }

    private Optional<Edge<E,T>> findEdge(Vertex<E,T> vertex) {
        return edges.stream()
                .filter(edge -> edge.isBetween(this, vertex))
                .findFirst();
    }

    public boolean hasEdge(Vertex<E,T> to) {
        return findEdge(to).isPresent();
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public T getInnerVertex() {
        return innerVertex;
    }
}
