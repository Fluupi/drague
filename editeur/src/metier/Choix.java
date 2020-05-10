package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

/**
 * La classe Choix représente une possibilité de réponse à un énoncé de Paragraphe
 *
 * @author A remplir
 * @version 2.0
 */
public class Choix extends AbstractModeleEcoutable
{
	/**
	 * Indice du Paragraphe vers lequel ce Choix mène dans la liste des paragraphes
	 * contenue dans Livre
	 */
	private int    indiceChoix;
	/**
	 * Texte de la réponse
	 */
	private String texteChoix;

	/*  Constructeur  */
	/**
	 * Constructeur de la classe Choix, prenant en arguments l'indice du paragraphe
	 * suivant et le texte de la réponse
	 *
	 * @param ic Indice du paragraphe suivant
	 * @param tc Texte de la réponse
	 */
	public Choix(int ic, String tc)
	{
		indiceChoix = ic;
		texteChoix  = tc;
	}

	/*  Getters  */
	/**
	 * Permet de récupérer l'indice du paragraphe suivant
	 *
	 * @return L'indice du paragraphe suivant
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
	 * Permet de modifier l'indice du paragraphe suivant
	 *
	 * @param newInd Indice du nouveau paragraphe suivant
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
