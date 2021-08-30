package com.vincentcodes.test;

import com.vincentcodes.util.commandline.Command;
import com.vincentcodes.util.commandline.CommandLineParser;
import com.vincentcodes.util.commandline.ParserConfig;

public class Test {
    public static void main(String[] args){
        ParserConfig config = new ParserConfig();
        // 2nd param is "isStandalone" which means '-h' will not be accompanied by another value
        config.addOption("-h", true, "", "print help");
        config.addOption("--print", false, "<value>", "print stuff out");

        CommandLineParser parser = new CommandLineParser(config);
        Command result = parser.parse(args);

        if(result.hasOption("--print")){
            System.out.println("Printing value: " + result.getOptionValue("--print"));
        }

        if(result.hasOption("-h")){
            printHelp(config);
        }else{
            printHelp(config);
        }
    }
    private static void printHelp(ParserConfig config){
        System.out.println("java com.vincentcodes.test.Main [options]");
        System.out.println(config.getOptionsHelpString());
    }
}
