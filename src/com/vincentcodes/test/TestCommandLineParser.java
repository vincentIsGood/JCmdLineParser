package com.vincentcodes.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vincentcodes.util.commandline.Command;
import com.vincentcodes.util.commandline.CommandLineParser;
import com.vincentcodes.util.commandline.ParserConfig;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
// import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * First time using JUnit test, you should find these reference pages
 * useful...
 * 
 * JAVA-wise  : https://junit.org/junit5/docs/current/user-guide/#overview
 * VSCode-wise: https://code.visualstudio.com/docs/java/java-testing
 * 
 */
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Full test of CommandLineParser")
public class TestCommandLineParser {
    ParserConfig config;
    CommandLineParser parser;

    @BeforeAll
    public void setUpParser(){
        config = new ParserConfig();
        config.addOption("--ok", true);
        config.addOption("-d", false);

        parser = new CommandLineParser(config);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a b c"})
    public void no_options_at_all(String unknownCommand){
        Command cmd = parser.parse(unknownCommand.split(" "));
        assertEquals("a", cmd.getParameter(0));
        assertEquals("b", cmd.getParameter(1));
        assertEquals("c", cmd.getParameter(2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"--ok a b c"})
    public void only_double_dash_in_front(String unknownCommand){
        Command cmd = parser.parse(unknownCommand.split(" "));
        assertTrue(cmd.hasOption("--ok"));
        assertEquals("a", cmd.getParameter(0));
        assertEquals("b", cmd.getParameter(1));
        assertEquals("c", cmd.getParameter(2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-d ../v/i/n/c/e/n/t"})
    public void only_single_dash_in_front(String unknownCommand){
        Command cmd = parser.parse(unknownCommand.split(" "));
        assertTrue(cmd.hasOption("-d"));
        assertEquals("../v/i/n/c/e/n/t", cmd.getOptionValue("-d"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"--ok -d ../v/i/n/c/e/n/t a b c", "--ok a b c -d ../v/i/n/c/e/n/t", "a b c --ok -d ../v/i/n/c/e/n/t"})
    public void both_double_single_dash_included(String unknownCommand){
        Command cmd = parser.parse(unknownCommand.split(" "));
        assertTrue(cmd.hasOption("--ok"));
        assertTrue(cmd.hasOption("-d"));
        assertEquals("../v/i/n/c/e/n/t", cmd.getOptionValue("-d"));
        assertEquals("a", cmd.getParameter(0));
        assertEquals("b", cmd.getParameter(1));
        assertEquals("c", cmd.getParameter(2));
    }
}
