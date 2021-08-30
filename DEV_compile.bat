@echo off

:: Do not try to compile the unit tests. It is annoying to deal with.
set first=src/com/vincentcodes/util/commandline/*.java

:: .java files are in encoding UTF-8
javac --release 8 -encoding UTF-8 -d classes -cp ./lib/*;./src/ %first%

pause