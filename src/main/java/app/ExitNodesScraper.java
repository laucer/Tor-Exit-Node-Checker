package app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import static app.PageParser.parseExitNodesPage;

public class ExitNodesScraper {

    private final HttpClient client;

    public ExitNodesScraper() {
        this.client = HttpClient.newBuilder()
                .version(Version.HTTP_2)
                .build();
    }

    public List<String> getExitNodes() throws IOException, InterruptedException {
        HttpResponse<String> exitNodesPage = client.send(
                getPageRequest(),
                HttpResponse.BodyHandlers.ofString()
        );
        return parseExitNodesPage(exitNodesPage.body());
    }

    private static HttpRequest getPageRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://check.torproject.org/exit-addresses"))
                .timeout(Duration.ofMinutes(1))
                .build();
    }

}
