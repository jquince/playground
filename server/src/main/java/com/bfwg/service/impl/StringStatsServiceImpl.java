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
        Long vowelsCount = new Long(0);
        Long wordsCount = new Long(0);
        Long linesCount = new Long(1);
        Long consonantsCount = new Long(0);
        Long spacesCount = new Long(0);
        Boolean inWord = false;
        Character curr = null;
        String vowels = "aeiou";
        String testString = input.toLowerCase();
        if(!StringUtils.isEmpty(input)){
            for (int i = 0; i < input.length(); i++) {
                curr = testString.charAt(i);// get current char
                if(curr >= 'a' && curr <= 'z') {//is it a word char
                    if(!inWord){
                        wordsCount++;//starting a word let's count it
                    }
                    if(vowels.indexOf(curr) >= 0){//vowel found
                        vowelsCount++;
                    } else {
                        consonantsCount++;
                    }
                    inWord = true;
                } else {
                    if(curr == ' ') {//space
                        spacesCount++;
                    } else if(curr == '\n'){
                        linesCount++;
                    }
                    //check for word boundary
                    if (curr != '\'') {
                        inWord = false;
                    }
                }
            }
        } else {
            linesCount--;//no string set to zero
        }

        Map<String, String> stats = new HashMap();
        stats.put("vowels", String.valueOf(vowelsCount));
        stats.put("words", String.valueOf(wordsCount));
        stats.put("lines", String.valueOf(linesCount));
        stats.put("consonants", String.valueOf(consonantsCount));
        stats.put("spaces", String.valueOf(spacesCount));
        /*stats.put("vowels", String.valueOf(countVowels(input)));
        stats.put("words", String.valueOf(countWords(input)));
        stats.put("lines", String.valueOf(countLines(input)));
        stats.put("consonants", String.valueOf(countConsonants(input)));
        stats.put("spaces", String.valueOf(countSpaces(input)));*/
        return stats;
    }

    Pattern VOWELS = Pattern.compile("[aeiou]",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
    Pattern CONSONANTS = Pattern.compile("[bcdfghjklmnpqrstvwxyz]",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
    Pattern SPACE = Pattern.compile(" ",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
    Pattern WORDS = Pattern.compile("[^\r\n]\\w+('\\w\\w?)?",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);


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
        return StringUtils.isEmpty(input) ? 0 : countFromMatcher(WORDS.matcher(input));
    }

    @Override
    public int countLines(String input) {
        return StringUtils.isEmpty(input) ? 0 : input.split("\r\n?").length;
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
