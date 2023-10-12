package com.vincentcodes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.vincentcodes.util.commandline.ArgumentObjectMapper;
import com.vincentcodes.util.commandline.ObjectMapperParseResult;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Full test of ArgumentObjectMapper")
public class TestObjectMapping {

    /**
     * <pre>
     * Explainer:
     * -h -> short form
     * --int-value -> changeable option name from 'opt1' to 'int-value'. Descriptions work correctly.
     * -l -> 
     * 
     * opt2, ignored -> invalid option value (deleted from test input)
     * Note: opt2 is being renamed to 'string-me'
     * </pre>
     * @link TestOptions
     */
    @ParameterizedTest
    @ValueSource(strings = {"-h --int-value 123 -l 12345678 --finalone 123.123"})
    public void new_way_of_parsing(String rawCommand){
        ArgumentObjectMapper.SHOW_SHORT_FORM_DESC = false;
        
        String[] args = rawCommand.split(" ");
        ObjectMapperParseResult<TestOptions> options = ArgumentObjectMapper.parseToObject(args, TestOptions.class);
        if(options.result.help){
            // options.simplePrintHelp(); // also fine
            options.simplePrintHelp("java -jar example.com [options]");
            // return;
        }
        assertEquals(options.result.intvalue, 123);
        assertEquals(options.result.opt2, "default value");
        assertEquals(options.result.opt3, 12345678);
        assertTrue(options.result.doubleMe == 123.321);
        assertTrue(options.result.finalone == 123.123);
    }

    @ParameterizedTest
    @ValueSource(strings = {"--listStr a,bdsads,c --listInt 1,2,3 -h"})
    public void new_way_of_parsing_list_args(String rawCommand){
        ArgumentObjectMapper.SHOW_SHORT_FORM_DESC = false;
        
        String[] args = rawCommand.split(" ");
        ObjectMapperParseResult<TestOptions> options = ArgumentObjectMapper.parseToObject(args, TestOptions.class);
        if(options.result.help){
            options.simplePrintHelp("java -jar example.com [options]");
        }

        System.out.println(Arrays.toString(options.result.listStr));
        System.out.println(Arrays.toString(options.result.listInt));
        assertEquals(options.result.listStr[1], "bdsads");
        assertEquals(options.result.listInt[1], 2);
    }
}
