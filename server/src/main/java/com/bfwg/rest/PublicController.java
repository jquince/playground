package com.bfwg.rest;

import com.bfwg.service.StringStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by fan.jin on 2017-05-08.
 */

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class PublicController {


    @Autowired
    StringStatsService stringStatsService;

    @RequestMapping( method = POST, value= "/foo")
    public Map<String, String> getFoo() {
        Map<String, String> fooObj = new HashMap<>();
        fooObj.put("foo", "bar");
        return fooObj;
    }

    @RequestMapping( method = POST, value= "/stringstats")
    public Map<String, String> postStringStats(@RequestBody String input) {
        return stringStatsService.getStats(input);
    }

}
