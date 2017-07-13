import java.util.ArrayList;
import java.util.List;


public class Node {
    private Page currentPage;
    private List<Node> parents;
    private List<Node> children;

    public Node(Page page) {
        this.currentPage = page;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public Page getCurrentPage(){ return this.currentPage; }

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
