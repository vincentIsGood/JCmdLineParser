package com.vincentcodes.util.commandline;

public class Option {
    private String option;
    private boolean isStandalone;
    private String description;
    private String parameterDescription;

    public Option(String option, boolean isStandalone, String parameterDescription, String description) {
        this.option = option;
        this.isStandalone = isStandalone;
        this.parameterDescription = parameterDescription;
        this.description = description;
    }

    public String getOption() {
        return option;
    }

    public boolean isStandalone() {
        return isStandalone;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public String getDescription() {
        return description;
    }

    public int optionParamLength(){
        return option.length() + parameterDescription.length();
    }

    @Override
    public String toString() {
        return String.format("{Option option: %s, isStandalone: %s, paramDesc: %s, desc: %s}", 
            option, isStandalone, parameterDescription, description);
    }
}
