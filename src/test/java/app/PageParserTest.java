package app;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PageParserTest {

    @Test
    public void parseExitNodesPage() {
        String page = "ExitNode 0011BD2485AD45D984EC4159C88FC066E5E3300E\n" +
                "Published 2019-03-27 10:15:24\n" +
                "LastStatus 2019-03-27 11:02:34\n" +
                "ExitAddress 162.247.74.201 2019-03-27 11:03:58\n" +
                "ExitNode 003D78825E0B9609EECFF5E4E0529717772E53C7\n" +
                "Published 2019-03-26 21:54:40\n" +
                "LastStatus 2019-03-26 23:02:35\n" +
                "ExitAddress 104.218.63.73 2019-03-26 23:04:50";
        List<String> exitNodes = PageParser.parseExitNodesPage(page);
        assertEquals(exitNodes.size(), 2);
        assertTrue(exitNodes.contains("162.247.74.201"));
        assertTrue(exitNodes.contains("104.218.63.73"));
    }

    @Test
    public void parseExitNodesForEmptyPage() {
        String page = "";
        List<String> exitNodes = PageParser.parseExitNodesPage(page);
        assertEquals(exitNodes.size(), 0);
    }
}