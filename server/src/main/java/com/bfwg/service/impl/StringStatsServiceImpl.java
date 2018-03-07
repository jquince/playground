package com.bfwg.service.impl;

import com.bfwg.service.StringStatsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StringStatsServiceImpl implements StringStatsService {
    /*
    * Uses regex to count number of matches
    * @param input string to be processed
    * @return A map containing stats about the processed string.
    * */
    @Override
    public Map<String, String> getStats(String input) {
        Map<String, String> stats = new HashMap();
        stats.put("vowels", String.valueOf(countVowels(input)));
        stats.put("words", String.valueOf(countWords(input)));
        stats.put("lines", String.valueOf(countLines(input)));
        stats.put("consonants", String.valueOf(countConsonants(input)));
        stats.put("spaces", String.valueOf(countSpaces(input)));
        return stats;
    }

    Pattern VOWELS = Pattern.compile("[aeiou]",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
    Pattern CONSONANTS = Pattern.compile("[bcdfghjklmnpqrstvwxyz]",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
    Pattern SPACE = Pattern.compile(" ",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);


    /*
    * get the number of matches
    *
    * */
    int countFromMatcher(Matcher m){
        int count = 0;
        while(m.find()){//seems lame but matcher won't give you the count otherwise
            count++;
        }
        return count;
    }

    @Override
    public int countVowels(String input) {
        return StringUtils.isEmpty(input) ? 0 : countFromMatcher(VOWELS.matcher(input));
    }

    @Override
    public int countWords(String input) {
        return StringUtils.isEmpty(input) ? 0 : input.split("[\n\r ]").length;
    }

    @Override
    public int countLines(String input) {
        return StringUtils.isEmpty(input) ? 0 : input.split("\n").length;
    }

    @Override
    public int countConsonants(String input) {
        return StringUtils.isEmpty(input) ? 0 : countFromMatcher(CONSONANTS.matcher(input));
    }

    @Override
    public int countSpaces(String input) {
        return StringUtils.isEmpty(input) ? 0 : countFromMatcher(SPACE.matcher(input));
    }
}
