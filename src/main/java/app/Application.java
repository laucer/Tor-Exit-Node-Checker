package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        TorExitNodeChecker torExitNodeChecker = new TorExitNodeChecker();
        Timer timer = new Timer();
        timer.schedule(torExitNodeChecker, 0, 30000);
        SpringApplication.run(Application.class, args);
    }
}
