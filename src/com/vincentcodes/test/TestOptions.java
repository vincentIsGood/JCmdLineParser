package com.vincentcodes.test;

import com.vincentcodes.util.commandline.annotations.CmdOption;

public class TestOptions {
    @CmdOption(shortForm = "h")
    public boolean help;

    @CmdOption(value = "int-value", parameterDescription = "<value>", description = "give me an int")
    public int intvalue;

    @CmdOption("string-me")
    public String opt2 = "default value";

    @CmdOption(value = "long-one", shortForm = "l")
    public long opt3;
    
    @CmdOption
    public double doubleMe = 123.321;
    
    @CmdOption
    public double finalone = 123.321;

    public boolean ignored = false;
}
