package aist.generation;

import aist.generation.services.NavigatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by justinp on 7/26/17.
 */
@Component
public class App implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        NavigatorService navigatorService = new NavigatorService();
        navigatorService.run();
    }
}
