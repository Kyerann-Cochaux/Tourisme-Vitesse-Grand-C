@echo off
cls

cd ../source

echo Compilation...
javac @compile.list -d ../class
cd ../class

echo Execution...
java source.AppliCreation

cd ../lanceurs
