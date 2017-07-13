/**
 * Created by justinp on 7/6/17.
 */
public interface GraphDBAdapter {
    public void addNode(Node node);
    public void addEdge(String from, String to);
}
