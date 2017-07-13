import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by justinp on 7/6/17.
 */
public class Neo4JGraphDB implements GraphDBAdapter {
    Driver driver;
    Session session;
    @Override
    public void addNode(Node node) {
        loadSession();
        if(node.getParent() != null) {
            session.run("MATCH (p:Page {url:{parentUrl}})"
                    + "CREATE (a:Page {name: {name}, url: {url}})"
                    + "CREATE (p)-[w:Link]->(a)",
                    parameters("parentUrl", node.getParent().getUrl(), "name", node.getName(), "url", node.getUrl()));
        } else {
            session.run("CREATE (a:Page {name: {name}, url: {url}})",
                    parameters("name", node.getName(), "url", node.getUrl()));
        }
        closeSession();
    }

    @Override
    public void addEdge(String from, String to) {
        loadSession();
        session.run("MATCH (f:Page {url:{from}})"
                        + "MATCH (t:Page {url:{to}})"
                        + "CREATE (f)-[w:Link]->(t)",
                parameters("from", from, "to", to));
        closeSession();
    }

    private void closeSession() {
        this.session.close();
        this.driver.close();
    }

    private void loadSession() {
        this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "test" ) );
        this.session = driver.session();
    }
}
