package com.bfwg.service;

import java.util.Map;

public interface StringStatsService {
    int countVowels(String input);
    int countWords(String input);
    int countLines(String input);
    int countConsonants(String input);
    int countSpaces(String input);
    Map<String, String> getStats(String input);
}
