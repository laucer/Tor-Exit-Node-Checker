package app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        return parseExitNodesPage(exitNodesPage);
    }

    private static HttpRequest getPageRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://check.torproject.org/exit-addresses"))
                .timeout(Duration.ofMinutes(1))
                .build();
    }

    private List<String> parseExitNodesPage(HttpResponse<String> exitNodesPage) {
        return Pattern.compile("\\d*\\.\\d*\\.\\d*\\.\\d*")
                .matcher(exitNodesPage.body())
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
    }

}
