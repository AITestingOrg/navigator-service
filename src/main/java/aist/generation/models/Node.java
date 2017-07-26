package aist.generation.models;

import java.util.ArrayList;
import java.util.List;


public class Node {
    private String url;
    private String title;
    private List<Node> parents;
    private List<Node> children;

    public Node(String url, String title, List<Node> parents) {
        this.url = url;
        this.title = title;
        this.parents = parents;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public List<Node> getParents(){ return this.parents; }

    public List<Node> getChildren(){ return this.children; }

    private boolean inParents(Node currentPage, String page){
        if(currentPage != null && currentPage.getCurrentPage().hasVisited(page))
            return true;

        else if(currentPage == null || currentPage.getParents().isEmpty())
            return false;

        else
            for(int i = 0; i < this.parents.size(); i++)
            {
                if(currentPage.getChildren().size() > i && currentPage.getParents().get(i).currentPage.hasVisited(page))
                    return true;

                else if(currentPage.getChildren().size() > i)
                    return inParents(currentPage.getParents().get(i), page);
            }

        return false;
    }

    private boolean inChildren(Node currentPage, String page){
        if(currentPage != null && currentPage.getCurrentPage().hasVisited(page))
            return true;

        else if(currentPage == null || currentPage.getChildren().isEmpty())
            return false;
        
        else
            for(int i = 0; i < this.children.size(); i++)
            {
                if(currentPage.getChildren().size() > i && currentPage.getChildren().get(i).currentPage.hasVisited(page))
                    return true;

                else if(currentPage.getChildren().size() > i)
                    inChildren(currentPage.getChildren().get(i), page);
            }

        return false;
    }

    public boolean inGraph(Node currentPage, String page){
        return inParents(currentPage, page) || inChildren(currentPage, page);
    }
}
