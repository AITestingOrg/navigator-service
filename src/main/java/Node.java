/**
 * Created by justinp on 7/6/17.
 */
public class Node {
    private Node parent;
    private String name;
    private String url;

    public Node(Node parent, String name, String url) {
        this.parent = parent;
        this.name = name;
        this.url = url;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
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
