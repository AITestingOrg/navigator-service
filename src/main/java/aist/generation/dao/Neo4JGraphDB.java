package dao;

import models.GraphNode;
import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by justinp on 7/6/17.
 */
public class Neo4JGraphDB implements GraphDBAdapter {
    Driver driver;
    Session session;
    @Override
    public void addNode(GraphNode graphNode) {
        loadSession();
        if(graphNode.getParent() != null) {
            session.run("MATCH (p:models.Page {url:{parentUrl}})"
                    + "CREATE (a:models.Page {name: {name}, url: {url}})"
                    + "CREATE (p)-[w:Link]->(a)",
                    parameters("parentUrl", graphNode.getParent().getUrl(), "name", graphNode.getName(), "url", graphNode.getUrl()));
        } else {
            session.run("CREATE (a:models.Page {name: {name}, url: {url}})",
                    parameters("name", graphNode.getName(), "url", graphNode.getUrl()));
        }
        closeSession();
    }

    @Override
    public void addEdge(String from, String to) {
        loadSession();
        session.run("MATCH (f:models.Page {url:{from}})"
                        + "MATCH (t:models.Page {url:{to}})"
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
