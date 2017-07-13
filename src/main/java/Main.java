import java.sql.SQLException;

/**
 * Created by justinp on 7/6/17.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        GraphDBAdapter g = new Neo4JGraphDB();
        Node home = new Node(null, "Home", "https://github.com");
        g.addNode(home);
        Node about = new Node(home, "About", "https://github.com/test");
        g.addNode(about);
        g.addNode(new Node(home, "Lol", "https://github.com/test2"));
        g.addEdge("https://github.com/test", "https://github.com/test2");

        g.addNode(new Node(about, "Info", "https://github.com/test1"));
        g.addNode(new Node(home, "Test", "https://github.com/test3"));

        g.addNode(new Node(about, "About Us", "https://github.com/test4"));
        g.addNode(new Node(home, "Contact", "https://github.com/test5"));

        g.addNode(new Node(home, "Catalog", "https://github.com/test6"));
        g.addNode(new Node(home, "Testing", "https://github.com/test7"));

        g.addNode(new Node(about, "Location", "https://github.com/test8"));
        g.addNode(new Node(home, "Home2", "https://github.com/test9"));
    }
}
