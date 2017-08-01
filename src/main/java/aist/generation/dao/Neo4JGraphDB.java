package aist.generation.dao;

import aist.generation.models.Page;
import aist.generation.models.Vertex;
import aist.generation.oldModels.GraphNode;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by justinp on 7/6/17.
 */
public class Neo4JGraphDB implements GraphDBAdapter<Process, Page> {
    private Driver driver;
    private Session session;

    @Override
    public void addVertex(Vertex vertex) {
        loadSession();
        if(vertex.getParent() != null) {
            session.run("MATCH (p:models.Page {url:{parentUrl}})"
                    + "CREATE (a:models.Page {name: {name}, url: {url}})"
                    + "CREATE (p)-[w:Link]->(a)",
                    parameters("parentUrl", vertex.getParent().getInnerVertex().getUrl(),
                            "name", vertex.getInnerVertex().getName(),
                            "url", vertex.getInnerVertex().getUrl()));
        } else {
            session.run("CREATE (a:models.Page {name: {name}, url: {url}})",
                    parameters("name", vertex.getInnerVertex().getName(),
                            "url", vertex.getInnerVertex().getUrl()));
        }
        closeSession();
    }

    @Override
    public void addEdge(Vertex from, Vertex to, Process info) {
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
