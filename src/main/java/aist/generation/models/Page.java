package aist.generation.models;

import java.util.Set;

public class Page {
    private final Set<String> childUrls;
    private String url;
    private PageType pagetype;
    private String title;

    protected Page(String url, PageType type, Set<String> childUrls, String title){
        this.url = url;
        this.pagetype = type;
        this.childUrls = childUrls;
        this.title = title;
    }

    public Set<String> getChildUrls() {
        return childUrls;
    }

    public String getUrl() {
        return url;
    }

    public PageType getPagetype() {
        return pagetype;
    }

    public String getTitle() {
        return title;
    }

    public static class PageBuilder {
        private String url;
        private PageType pagetype;
        private Set<String> childUrls;
        private String title;

        public PageBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public PageBuilder setPagetype(PageType pagetype) {
            this.pagetype = pagetype;
            return this;
        }

        public PageBuilder setChildUrls(Set<String> childUrls) {
            this.childUrls = childUrls;
            return this;
        }

        public PageBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Page build() {
            return new Page(url, pagetype, childUrls, title);
        }
    }
}
