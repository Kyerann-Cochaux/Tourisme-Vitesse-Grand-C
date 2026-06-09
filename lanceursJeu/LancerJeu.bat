@echo off
cls

cd ../srcJeu

echo Compilation...
javac @compile.list -d ../class
cd ../class

echo Execution...
java source.AppliJeu

cd ../lanceurs
