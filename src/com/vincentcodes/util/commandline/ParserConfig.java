package com.vincentcodes.util.commandline;

import java.util.ArrayList;
import java.util.HashMap;

public class ParserConfig {
    private HashMap<String, Option> optionToOptionMap;
    private ArrayList<String> optionsAddedInOrder;
    private int longestOption = 0;

    public ParserConfig(){
        this.optionToOptionMap = new HashMap<>();
        this.optionsAddedInOrder = new ArrayList<>();
    }

    /**
     * Please include dash "-" in your option as well (if necessary).
     */
    public void addOption(String option, boolean isStandalone){
        addOption(option, isStandalone, "", "");
    }

    public void addOption(String option, boolean isStandalone, String description){
        addOption(option, isStandalone, "", description);
    }

    public void addOption(String option, boolean isStandalone, String parameterDescription, String description){
        Option opt = new Option(option, isStandalone, parameterDescription, description);
        optionToOptionMap.put(option, opt);
        optionsAddedInOrder.add(option);

        if(opt.optionParamLength() > longestOption){
            longestOption = opt.optionParamLength();
        }
    }

    public boolean hasOption(String option){
        Object optionObj = optionToOptionMap.get(option);
        return optionObj == null? false : true;
    }

    public boolean optionIsStandalone(String option){
        return optionToOptionMap.get(option).isStandalone();
    }

    public String getOptionsHelpString(){
        StringBuilder builder = new StringBuilder();
        for(String optionKey : optionsAddedInOrder){
            Option option = optionToOptionMap.get(optionKey);
            builder.append("    ").append(String.format("%-"+(longestOption+2)+"s", optionKey + " " + option.getParameterDescription())).append(option.getDescription()).append("\n");
        }
        return builder.toString();
    }
}
