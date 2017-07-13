public class Edge {
    private Page from;
    private Page to;
    private Process process;

    public Edge(Page from, Page to, Process process){
        this.from = from;
        this.to = to;
        this.process = process;
    }
}
