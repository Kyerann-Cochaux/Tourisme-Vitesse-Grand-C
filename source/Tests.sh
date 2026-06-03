clear

echo ____Tests____
echo Compilation...

javac @compile.list -d ../class
cd ../class

echo Execution...
java source.TestMetier

cd ../source