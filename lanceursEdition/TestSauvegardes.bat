@echo off
cls

cd ../srcEdition

echo ____Tests____
echo Compilation...
javac @compile.list -d ../class
cd ../class

echo Execution...
java srcEdition.TestSauvegardes