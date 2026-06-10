clear

cd ../srcJeu

echo ____Tests____
echo Compilation...
javac @compile.list -d ../class
cd ../class

echo Execution...
java srcJeu.TestZone