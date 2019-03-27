package app;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PageParser {

    public static List<String> parseExitNodesPage(String exitNodesPage) {
        return Pattern.compile("\\d*\\.\\d*\\.\\d*\\.\\d*")
                .matcher(exitNodesPage)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
    }

}
