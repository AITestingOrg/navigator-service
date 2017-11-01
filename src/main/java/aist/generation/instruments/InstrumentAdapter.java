package aist.generation.instruments;

import aist.generation.models.LoginForm;
import aist.generation.models.Page;
import org.springframework.stereotype.Service;

/**
 * Created by justinp on 7/26/17.
 */
@Service
public interface InstrumentAdapter {
    Page get(String url);
    void quit();
    String getPageSource();
    void fillLoginForm(Page page);
    void sendKeys(LoginForm form);
}
