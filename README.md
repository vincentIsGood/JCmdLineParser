# JCmdLineParser
This is a java library for parsing command lines. It is used in some of my own projects too. 

## New usage
Create a config file:
```java
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
```

Parse the arguments from command line and use it:
```java
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
```

## Old Usage

The most important class in this library is `CommandLineParser`. Within the class, the `parse(String[])` function expects arguments that comes from `public static void main(String[] args)`. You need to add a `ParserConfig` to the parser, since the parser will use it to check for required options and throw errors on invalid options.

For a full example, please see `src/com/vincentcodes/test/Test.java`.