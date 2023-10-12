package com.vincentcodes.util.commandline;

/**
 * Access {@code result} property to get parsed values for your defined property.
 */
public class ObjectMapperParseResult<T> {
    public T result;
    public ParserConfig parserConfig;

    public ObjectMapperParseResult(T result, ParserConfig parserConfig){
        this.result = result;
        this.parserConfig = parserConfig;
    }

    public void simplePrintHelp(String commandSyntax){
        System.out.println(commandSyntax);
        System.out.println(parserConfig.getOptionsHelpString());
    }
    public void simplePrintHelp(){
        simplePrintHelp("java -jar executable.jar [options]");
    }
}
