cd bin
ihm=$1
fichierLivre=$2
java -cp ./lib/ecouteMetier.jar:. editeur.Controleur $ihm $fichierLivre
