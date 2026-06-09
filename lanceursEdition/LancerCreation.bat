@echo off
cls

cd ../srcEdition

echo Compilation...
javac @compile.list -d ../class
cd ../class

echo Execution...
java srcEdition.AppliCreation