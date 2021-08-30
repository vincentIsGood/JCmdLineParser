# JCmdLineParser

This is a java library for parsing command lines. It is used in some of my own projects too. 

## Usage

The most important class in this library is `CommandLineParser`. Within the class, the `parse(String[])` function expects arguments that comes from `public static void main(String[] args)`. You need to add a `ParserConfig` to the parser, since the parser will use it to check for required options and throw errors on invalid options.

For a full example, please see `src/com/vincentcodes/test/Test.java`.