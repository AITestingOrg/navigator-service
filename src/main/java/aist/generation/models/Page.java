package aist.generation.models;

import java.util.Set;

public class Page implements InnerVertex {
    private final Set<String> childUrls;
    private String url;
    private PageType pagetype;
    private String Name;
    private String html;

    protected Page(String url, Set<String> childUrls, String Name, String html){
        this.url = url;
        this.childUrls = childUrls;
        this.Name = Name;
        this.html = html;
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

    public void setPageType(PageType pageType) { this.pagetype = pageType; }

    public String getName() {
        return Name;
    }

    public String getPageType() {
        return pagetype.toString();
    }

    public String getHtml() {
        return html;
    }

    public static class PageBuilder {
        private String url;
        private Set<String> childUrls;
        private String title;
        private String html;

        public PageBuilder setUrl(String url) {
            this.url = url;
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

        public PageBuilder setHtml(String html) {
            this.html = html;
            return this;
        }

        public Page build() {
            return new Page(url, childUrls, title, html);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Page)) {
            return false;
        }

        Page page = (Page) obj;
        return page.url.equals(this.url);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + url.hashCode();
        return result;
    }
}
