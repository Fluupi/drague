cd bin
ihm=$1
fichierLivre=$2
java -cp ./lib/ecouteMetier.jar:./lib/jdom-2.0.6.jar. editeur.Controleur $ihm $fichierLivre
