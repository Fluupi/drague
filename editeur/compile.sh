if [ "$#" -eq 1 ]
then
	cd src
	jar -cvfm ../exec/Editeur.jar MANIFEST.MF @compile.list
else
	javac -cp bin/lib/ecouteMetier.jar:. -d bin/ @compile.list
fi
