@echo off

echo Compilation...
javac @compile.list -d ../class
cd ../class

echo Execution...
java source.Controleur
cd ../source