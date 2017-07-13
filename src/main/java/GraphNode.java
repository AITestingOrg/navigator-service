/**
 * Created by justinp on 7/6/17.
 */
public class GraphNode {
    private GraphNode parent;
    private String name;
    private String url;

    public GraphNode(GraphNode parent, String name, String url) {
        this.parent = parent;
        this.name = name;
        this.url = url;
    }

    public GraphNode getParent() {
        return parent;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
