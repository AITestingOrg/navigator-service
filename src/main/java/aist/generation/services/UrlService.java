package aist.generation.services;

import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by justinp on 7/26/17.
 */
public class UrlService {

    private URL rootURL;
    private String hostName;

    public UrlService(String rootURLString) {
        this.rootURL = getURL(rootURLString);
        this.hostName = rootURL.getHost();
    }

    public boolean validate(String urlString) {
        return generateURL(urlString).map(url -> url.getHost().contains(hostName)).orElse(false);
    }

    private Optional<URL> generateURL(String urlString) {
        try {
            return Optional.of(new URIBuilder(urlString).build().toURL());
        } catch (URISyntaxException e) {
            System.out.println("ERROR: Invalid URL");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("ERROR: Malformed URL");
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public URL getURL(String urlString) {
        return generateURL(urlString).orElse(null);
    }
}
