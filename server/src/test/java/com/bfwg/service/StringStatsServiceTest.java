package com.bfwg.service;

import com.bfwg.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
public class StringStatsServiceTest extends AbstractTest {

    @Autowired
    StringStatsService stringStatsService;

    String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n"+
            "Ut vel commodo augue. Morbi quis ullamcorper justo. Aliquam erat volutpat.\n" +
            "Mauris quis facilisis metus, vel vestibulum ante.";

    @Test
    public void testGetStats() {
        Map<String,String> results = stringStatsService.getStats(testString);
        assertThat(results.get("words"),is("26"));
        assertThat(results.get("lines"),is("3"));
        assertThat(results.get("vowels"),is("65"));
        assertThat(results.get("consonants"),is("84"));
        assertThat(results.get("spaces"),is("23"));
    }
/*

    @Test
    public void testVowelCount() {
        assertThat(stringStatsService.countVowels(testString),is(65));
    }
*/

    @Test
    public void testWordCount() {
        Map<String,String> results = stringStatsService.getStats(testString);
        assertThat(results.get("words"),is("26"));
        results = stringStatsService.getStats("hello,world");
        assertThat("comma",results.get("words"),is("2"));
        results = stringStatsService.getStats("hello\tworld");
        assertThat("tab",results.get("words"),is("2"));
        results = stringStatsService.getStats("hello    world");
        assertThat("space",results.get("words"),is("2"));
        results = stringStatsService.getStats("wait a sec\r\nhow many words\r\ndoes this have?");
        assertThat("carriage",results.get("words"),is("9"));
        results = stringStatsService.getStats("This exercise can be quite challenging; take your time.\n" +
                "If you're not careful, it can really do you in. But don't worry.\n" +
                "This fun challenge shows how you, the developer, solve problems.");
        assertThat(results.get("words"),is("32"));

    }
   /* @Test
    public void testLineCount() {
        assertThat(stringStatsService.countLines(testString),is(3));
        assertThat(stringStatsService.countLines("wait a sec\\r\\nhow many words\\r\\ndoes this have?"),is(3));
    }
    @Test
    public void testConsonantCount() {
        assertThat(stringStatsService.countConsonants(testString),is(84));
    }*/
    @Test
    public void testSpaceCount() {
        Map<String,String> results = stringStatsService.getStats("      ");
        assertThat(results.get("spaces"),is("6"));
    }
}
