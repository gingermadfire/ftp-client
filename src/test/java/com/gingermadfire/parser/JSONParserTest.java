package com.gingermadfire.parser;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class JSONParserTest {

    private final JSONParser parser = new JSONParser();

    @Test
    public void incorrectFile() {
        Assert.assertEquals(parser.parse("asddfgdg"), Collections.emptyList());
    }

    @Test(groups = "json parser")
    public void emptyJSONFile() {
        Assert.assertEquals(parser.parse(""), Collections.emptyList());
    }

    @Test(expectedExceptions = StringIndexOutOfBoundsException.class, groups = "json parser")
    public void incorrectJSONFile() {
        String json = "{" +
                "\"students\": [" +
                "\"name\": \"Maxim\"" +
                "\"id\": 1" +
                "}";
        parser.parse(json);
    }
}