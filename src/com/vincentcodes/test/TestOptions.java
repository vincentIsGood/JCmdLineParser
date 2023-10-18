package com.vincentcodes.test;

import com.vincentcodes.util.commandline.annotations.CmdOption;

/**
 * Define custom object as mapping for your command line args.
 */
public class TestOptions {
    /**
     * {@code CmdOption} is required to indicate that this is a 
     * command line option to be parsed
     */
    @CmdOption(shortForm = "h")
    public boolean help;

    @CmdOption(value = "int-value", parameterDescription = "<value>", description = "give me an int")
    public int intvalue;

    @CmdOption("string-me")
    public String opt2 = "default value";

    @CmdOption(value = "long-one", shortForm = "l")
    public long opt3;
    
    /**
     * "--doubleMe" option
     */
    @CmdOption
    public double doubleMe = 123.321;
    
    @CmdOption
    public double finalone = 123.321;

    @CmdOption
    public String[] listStr;

    @CmdOption
    public int[] listInt;

    @CmdOption(shortForm = "options")
    public String optionsfile;

    public boolean ignored = false;
}
