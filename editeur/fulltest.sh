if [[ ($# -eq 1) && ("$1" -eq "-c") ]]
then
	./compile.sh jar
fi
cd exec/
./lancer.sh o
