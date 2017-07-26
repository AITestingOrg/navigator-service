package models;

import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Point;



public class Page {
    private String url;
    private PageType pagetype;
    private List <WebElement> elements;
    private List<String> urlsVisited;
    private String title;
    private List<Point> coordinates;

    public Page(String url, PageType type){
        this.url = url;
        this.pagetype = type;
        this.elements = new ArrayList<WebElement>();
        this.urlsVisited = new ArrayList<String>();
        this.title = "";
        this.coordinates = new ArrayList<Point>();
    }

    public void printList(){
        for(int i = 0; i < elements.size(); i++){
            System.out.println(elements.get(i).getText());
        }
    }

    public void setList(List<WebElement> list) {
        for (WebElement element: list) {
            if (!(element.getLocation().getX() == 0 & element.getLocation().getY() == 0)) {
                this.elements.add(element);
                this.coordinates.add(element.getLocation());
                System.out.println("Element: " + element.getText());
                System.out.println("Coordinates: ");
                System.out.println("X: " + element.getLocation().x);
                System.out.println("Y: " + element.getLocation().y + "\n");
            }
        }
    }

    public String getUrl() { return this.url; }

    public void visit(String visited){ this.urlsVisited.add(visited); }

    public boolean hasVisited(String url){ return urlsVisited.contains(url); }

    public void addTitle(String title){ this.title = title; }
}
