javac -cp bin/lib/ecouteMetier.jar:bin/lib/jdom-2.0.6.jar:. -d bin/ @compile.list

if [ "$#" -eq 1 ]
then
	cd bin
	./jar.sh
fi
