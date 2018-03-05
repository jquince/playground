package com.bfwg.service;

import com.bfwg.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void testVowelCount() {
        assertThat(stringStatsService.countVowels(testString),is(65));
    }

    @Test
    public void testWordCount() {
        assertThat(stringStatsService.countWords(testString),is(26));
    }
    @Test
    public void testLineCount() {
        assertThat(stringStatsService.countLines(testString),is(3));
    }
    @Test
    public void testConsonantCount() {
        assertThat(stringStatsService.countConsonants(testString),is(84));
    }
    @Test
    public void testSpaceCount() {
        assertThat(stringStatsService.countSpaces(testString),is(23));
    }
}
