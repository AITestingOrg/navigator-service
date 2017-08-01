package aist.generation.models;

/**
 * Created by matthewro on 7/27/17.
 */
public class Edge<E,T extends InnerVertex> {

    private Vertex<E,T> from, to;
    private E info;

    public Edge(Vertex<E, T> from, Vertex<E, T> to, E info) {
        this.from = from;
        this.to = to;
        this.info = info;
    }

    public Vertex<E, T> getFrom() {
        return from;
    }

    public Vertex<E, T> getTo() {
        return to;
    }

    public E getInfo() {
        return info;
    }

    public void setFrom(Vertex<E, T> from) {
        this.from = from;
    }

    public void setTo(Vertex<E, T> to) {
        this.to = to;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public boolean isBetween(Vertex<E,T> from, Vertex<E,T> to) {
        return (this.from == from && this.to == to);
    }
}
