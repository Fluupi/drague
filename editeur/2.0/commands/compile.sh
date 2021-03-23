cp ../../../lovestory/bin/Histoire.jar ../bin/lib/Histoire.jar
ls
cd ..
ls
javac -cp bin/lib/ecouteMetier.jar:bin/lib/jdom-2.0.6.jar:bin/lib/Histoire.jar:. -d ../bin/ @compile.list

#if [ "$#" -eq 1 ]
#then
#	cd bin
#	./jar.sh
#fi
