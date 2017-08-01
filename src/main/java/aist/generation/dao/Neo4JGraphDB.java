package aist.generation.dao;

import aist.generation.models.*;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Value;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by justinp on 7/6/17.
 */
public class Neo4JGraphDB implements GraphDBAdapter {

    @Value("${aist.generation.graphDB.url}")
    private String graphDBUrl;

    @Value("${aist.generation.graphDB.username}")
    private String graphDBUsername;

    @Value("${aist.generation.graphDB.password}")
    private String graphDBPassword;


    private Driver driver;
    private Session session;

    @Override
    public void addVertex(InnerVertex innerVertex) {
        loadSession();
        if(innerVertex != null) {
            session.run("MATCH (p:models.Page {url:{parentUrl}})"
                            + "CREATE (a:models.Page {name: {name}, url: {url}})"
                            + "CREATE (p)-[w:Link]->(a)",
                    parameters("name", innerVertex.getName(),
                            "url", innerVertex.getUrl()));
        } else {
            session.run("CREATE (a:models.Page {name: {name}, url: {url}})",
                    parameters("name", innerVertex.getName(),
                            "url", innerVertex.getUrl()));
        }
        closeSession();
    }

    @Override
    public void addEdge(InnerVertex from, InnerVertex to, InnerEdge innerEdge) {
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
        this.driver = GraphDatabase.driver( graphDBUrl, AuthTokens.basic( "neo4j", "test" ) );
        this.session = driver.session();
    }
}
