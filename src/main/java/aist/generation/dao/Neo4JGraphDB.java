package aist.generation.dao;

import aist.generation.models.*;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by justinp on 7/6/17.
 */
@Service
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

        System.out.println("Creating Database Vertex");
        session.run("CREATE(a:Page{name:{name},url:{url},type:{type}})",
                parameters("name", innerVertex.getName(),
                        "url", innerVertex.getUrl(), "type", innerVertex.getPageType()));

        closeSession();
    }

    @Override
    public void addEdge(InnerVertex from, InnerVertex to, InnerEdge innerEdge) {
        loadSession();

        System.out.println("Creating Database Edge");
        session.run("MATCH (f:Page{url:{from}})"
                        + "MATCH (t:Page{url:{to}})"
                        + "CREATE (f)-[w:Link]->(t)",
                parameters("from", from.getUrl(), "to", to.getUrl()));

        closeSession();
    }

    public void clearDatabase(){
        loadSession();
        session.run("MATCH (n)" +
                "OPTIONAL MATCH (n)-[r]-()" +
                "DELETE n,r");
        closeSession();
    }

    private void closeSession() {
        this.session.close();
        this.driver.close();
    }

    private void loadSession() {
        this.driver = GraphDatabase.driver(graphDBUrl, AuthTokens.basic( graphDBUsername, graphDBPassword ) );
        this.session = driver.session();
    }
}
