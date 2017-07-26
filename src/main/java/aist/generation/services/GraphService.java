package aist.generation.services;

import aist.generation.dao.GraphDBAdapter;
import aist.generation.models.Node;
import aist.generation.models.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by justinp on 7/26/17.
 */
public class GraphService {
    @Autowired
    private GraphDBAdapter graphDb;

    private Node root;

    public void setRoot(Page root) {
        this.root = new Node(root.getUrl(), root.getTitle(), new ArrayList<>());
    }
}
