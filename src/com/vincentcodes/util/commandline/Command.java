package com.vincentcodes.util.commandline;

import java.util.List;
import java.util.Map;

public class Command {
    private String name;
    private Map<String, String> optionPairs;
    private List<String> parameters;

    public Command(String name, Map<String, String> optionPairs, List<String> parameters){
        this.name = name;
        this.optionPairs = optionPairs;
        this.parameters = parameters;
    }

    public String getName(){
        return name;
    }

    public boolean hasOption(String option){
        return optionPairs.containsKey(option);
    }

    /**
     * The parameters does not include the ones already 
     * paired to an option. 
     * @param num Starting from 0.
     */
    public String getParameter(int index){
        return parameters.get(index);
    }

    /**
     * @param option The option includes the dashes (eg. "-d", "--password")
     * @return String | null
     */
    public String getOptionValue(String option){
        return optionPairs.get(option);
    }

    public Map<String, String> getOptionPairs(){
        return optionPairs;
    }

    public List<String> getParameters(){
        return parameters;
    }
}
