@echo off

set jarname=commandlineutil-v2.1.0
set structure=com/vincentcodes/util/commandline/*

:: with Manifest
:: cd classes
:: jar -cvfm %jarname%.jar Manifest.txt %structure%
:: mv %jarname%.jar ..

:: without Manifest
cd classes
jar -cvf %jarname%.jar %structure%
mv %jarname%.jar ..

cd ../src
jar -cvf %jarname%-sources.jar %structure%
mv %jarname%-sources.jar ..

pause