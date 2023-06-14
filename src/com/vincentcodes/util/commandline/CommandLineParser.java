package com.vincentcodes.util.commandline;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The command terms are as follows
 * https://stackoverflow.com/questions/36495669/difference-between-terms-option-argument-and-parameter
 */
public class CommandLineParser {
    public static boolean SUPPRESS_WARN = true;
    private ParserConfig config;

    public CommandLineParser(ParserConfig config){
        this.config = config;
    }

    /**
     * @param args from main(String[] args))
     */
    public Command parse(String[] args){
        ArrayList<String> params = new ArrayList<>();
        HashMap<String, String> optionPairs = new HashMap<>();
        
        for(int i = 0; i < args.length; i++){
            String arg = args[i];
            if(isRepeatedOption(optionPairs, arg)){
                throw new RuntimeException("Duplicate option: " + arg);
            }
            if(config.hasOption(arg) && !config.optionIsStandalone(arg)){
                if(i+1 < args.length){
                    String value = args[++i];
                    if(!SUPPRESS_WARN && value.startsWith("-")){
                        System.out.println(String.format("[!] Taking a dashed value for '%s'. (ie. %s=%s)", arg, arg, value));
                    }
                    optionPairs.put(arg, value);
                }else{
                    throw new RuntimeException("Option '" + arg + "' needs a parameter");
                }
            }else if(config.hasOption(arg)){
                optionPairs.put(arg, null);
            }else if((arg.startsWith("-") || arg.startsWith("--"))){
                throw new RuntimeException("Invalid option: " + arg);
            }else{
                params.add(arg);
            }
        }

        return new Command("command", optionPairs, params, config);
    }

    private boolean isRepeatedOption(HashMap<String, String> optionPairs, String option){
        if(config.hasOption(option)){
            return optionPairs.containsKey(option);
        }
        return false;
    }
}
