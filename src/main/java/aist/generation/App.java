package aist.generation;

import aist.generation.services.NavigatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by justinp on 7/26/17.
 */
@Component
public class App implements CommandLineRunner {

    @Autowired
    private NavigatorService navigatorService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running...");
        navigatorService.run();
    }
}
