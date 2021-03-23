package editeur;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * La classe Controleur représente le lien entre la partie graphique, que ce
 * soit écrite dans le terminal ou affichée par la partie vue, et la partie
 * métier. Elle s'occupe de faire passer toutes les instructions de l'utilisateur
 * à la partie métier et est l'élément qui est lancé en tout premier quand on
 * lance le programme et qui instancie les parties métier et vue (au besoin)
 *
 * @author A remplir
 * @version 2.0
 */
public class Controleur
{
	/**
	 * Main : méthode permettant de lancer le programme en fonction des arguments
	 * fournis
	 */
	public static void main(String[] args) throws InterruptedException
	{
		if(args.length==3)
			new Controleur(args[0].charAt(0), args[1].equals("o"), args[2]);
		else
			new Controleur(args[0].charAt(0), args[1].equals("o"));
	}

}
