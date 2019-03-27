package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class TorExitNodeChecker extends TimerTask {

    private static ExitNodesScraper exitNodesScraper = new ExitNodesScraper();
    private static List<String> exitNodes = new ArrayList<>();

    public static boolean isExitNode(String ipAddress) {
        return exitNodes.contains(ipAddress);
    }

    @Override
    public void run() {
        try {
            exitNodes = exitNodesScraper.getExitNodes();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
