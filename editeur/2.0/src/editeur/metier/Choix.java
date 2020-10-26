package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

/**
 * La classe Choix représente une possibilité de réponse à un Enonce
 *
 * @author A remplir
 * @version 2.0
 */
public class Choix extends AbstractModeleEcoutable
{
	/**
	 * Indice de l'Enonce vers lequel ce Choix mène dans la liste des enonces
	 * contenue dans Livre
	 */
	private int    indiceChoix;
	/**
	 * Texte de la réponse
	 */
	private String texteChoix;
	/**
	 * Effets appliqués lors de l'application de ce Choix
	 */
	private String effets;

	/*  Constructeur  */
	/**
	 * Constructeur de la classe Choix, prenant en arguments l'indice de l'enonce
	 * suivant et le texte de la réponse
	 *
	 * @param ic Indice de l'enonce suivant
	 * @param tc Texte de la réponse
	 */
	public Choix(int ic, String tc)
	{
		indiceChoix = ic;
		texteChoix  = tc;
	}

	/*  Getters  */
	/**
	 * Permet de récupérer l'indice de l'enonce suivant
	 *
	 * @return L'indice de l'enonce suivant
	 */
	public int getInd()
	{
		return indiceChoix;
	}

	/**
	 * Permet de récupérer le texte de la réponse
	 *
	 * @return Le texte de la réponse
	 */
	public String getReponse()
	{
		return texteChoix;
	}

	/*  Setters  */
	/**
	 * Permet de modifier l'indice de l'enonce suivant
	 *
	 * @param newInd Indice du nouvel enonce suivant
	 */
	public void setInd(int newInd)
	{
		indiceChoix = newInd;
		signalChgt();
	}

	/**
	 * Permet de modifier le texte de la réponse
	 *
	 * @param newReponse Nouveau texte de la réponse
	 */
	public void setReponse(String newReponse)
	{
		texteChoix = newReponse;
		signalChgt();
	}
}
