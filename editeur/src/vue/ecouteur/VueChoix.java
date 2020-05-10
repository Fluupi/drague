package editeur.vue.ecouteur;

import ecouteMetier.EcouteurModele;

import editeur.metier.Choix;

/**
 * La classe VueChoix est une vue sur la classe Choix, elle permet à la partie
 * vue du programme d'accéder facilement aux informations et modifications de la
 * classe Choix
 *
 * @author A remplir
 * @version 2.0
 */
public class VueChoix implements EcouteurModele
{
	/**
	 * Objet écouté
	 */
	private Choix choix;

	/*  Constructeur  */
	/**
	 * Constructeur de l'écouteur de la classe Choix
	 *
	 * @param c Objet à écouter
	 */
	public VueChoix(Choix c)
	{
		choix = c;
		choix.ajoutEcouteur(this);
	}

	/*  Getters  */
	/**
	 * Permet de récupérer l'indice du paragraphe suivant
	 *
	 * @return L'indice du paragraphe suivant
	 */
	public int getInd()
	{
		return choix.getInd();
	}

	/**
	 * Permet de récupérer le texte de la réponse
	 *
	 * @return Le texte de la réponse
	 */
	public String getReponse()
	{
		return choix.getReponse();
	}

	/**
	 * @override
	 */
	public void modeleAJour(Object o)
	{
		choix = (Choix) o;
	}
}
