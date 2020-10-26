package editeur.metier;

import java.util.ArrayList;

import ecouteMetier.AbstractModeleEcoutable;

/**
 * La classe Enonce représente un enonce de livre, elle permet toutes les
 * intéractions avec son texte et peut être liée à d'autres enonces à l'aide
 * de la classe Choix et ainsi permet toutes les intéractions avec cette même
 * classe
 *
 * @author A remplir
 * @version 2.0
 */
public class Enonce extends AbstractModeleEcoutable
{
	/**
	 * Réponses de l'Enonce
	 */
	private ArrayList<Choix> choix = new ArrayList<Choix>();
	/**
	 * Texte de l'Enonce
	 */
	private String   texte;
	/**
	 * ID de l'Enonce
	 */
	private int id;

	/*  Constructeurs  */
	/**
	 * Permet de créer un Enonce à partir de son texte
	 *
	 * @param texte
	 */
	public Enonce(String texte)
	{
		this.texte = texte;
	}

	// /**
	//  * Permet de créer un Enonce en ajoutant directement sa liste de réponses
	//  *
	//  * @param suivants Index d
	//  * @param texteS
	//  * @param texte
	//  */
	// public Enonce(int[] suivants, String[] texteS, String texte)
	// {
	// 	this(texte);
	//
	// 	for(int i=0; i<suivants.length; i++)
	// 		choix.add(new Choix(suivants[i], texteS[i]));
	// }

	/**
	 * Permet la génération d'un Enonce à partir de sa description venant
	 * directement d'un fichier .ldveh
	 *
	 * @param desc description de l'Enonce
	 * @return Le enonce correspondant à la description fournie
	 */
	// public static Enonce importer(String desc) {
	// 	//--numP--numSuivants,]texteS|]texte
	//
	// 	if(desc.charAt(0)==']')
	// 		return new Enonce(desc.substring(1));
	//
	// 	String[] description = desc.split("]");
	// 	String[] numChoix    = description[0].split(",");
	// 	String[] texteS      = description[1].split("\\|");
	//
	// 	String texte = description[2];
	// 	int[] suivants = new int[numChoix.length];
	//
	// 	for(int i=0; i<numChoix.length; i++)
	// 		suivants[i]=Integer.parseInt(numChoix[i]);
	//
	// 	return new Enonce(suivants, texteS, texte);
	// }

	/*  Getters  */
	/**
	 * Permet de récupérer le texte de l'Enonce
	 *
	 * @return Le texte de l'enonce
	 */
	public String getTexte()
	{
		return texte;
	}

	/**
	 * Permet de vérifier si le Enonce est une fin de Livre
	 *
	 * @return True si le Enonce est une fin de Livre
	 */
	public boolean estFin()
	{
		return choix.isEmpty();
	}

	/**
	 * Permet de récupérer la liste des Choix de l'Enonce
	 *
	 * @return La liste des Choix
	 */
	public ArrayList<Choix> getListeChoix()
	{
		return choix;
	}

	/**
	 * Permet de récupérer l'index de l'enonce cible de la réponse d'index i
	 *
	 * @param i Index de la réponse
	 * @return Index de l'enonce cible
	 */
	public int getChoix(int i)
	{
		return choix.get(i).getInd();
	}

	/**
	 * Permet de récupérer le texte de la réponse d'index i
	 *
	 * @param i Index de la réponse
	 * @return Texte de la réponse
	 */
	public String getTexteChoix(int i)
	{
		return choix.get(i).getReponse();
	}

	/**
	 * Permet de récupérer la taille de la liste de Choix
	 *
	 * @return La taille
	 */
	public int getNbChoix()
	{
		return choix.size();
	}

	/*  Setters  */
	/**
	 * Permet de modifier le texte de l'enonce
	 *
	 * @param texte Nouveau texte de l'enonce
	 */
	public void setTexte(String texte)
	{
		this.texte = texte;
	}

	/**
	 * Permet d'ajouter un choix si la liste n'est pas déjà trop remplie
 	 *
 	 * @param choix      Index de l'enonce cible
 	 * @param numChoix   Index de la réponse ciblée
 	 * @param texteReponse Texte de la réponse
	 */
	public void ajouterChoix(int choix, int numChoix, String texteReponse)
	{
		if(this.choix.size() <= 4)
		{
			if(numChoix == -1)
				this.choix.add(new Choix(choix, texteReponse));
			else
				this.choix.add(numChoix, new Choix(choix, texteReponse));
		}
	}

	/**
	 * Permet de modifier un choix
 	 *
 	 * @param numReponse   Index de la réponse ciblée
 	 * @param numPara      Index de l'nouveau enonce cible
 	 * @param texteReponse Nouveau texte de la réponse
	 */
	public void setChoix(int numReponse, int numPara, String texteReponse)
	{
		choix.get(numReponse).setInd(numPara);
		choix.get(numReponse).setReponse(texteReponse);
		signalChgt();
	}

	/**
	 * Permet de supprimer un choix lié à un certain enonce
	 *
	 * @param numPara Index de l'enonce lié
	 */
	public void supprimerLien(int numPara)
	{
		for(Choix c : choix)
			if (c.getInd()==numPara)
			{
				System.out.println("remove : "+c.getInd());
				choix.remove(c);
				break;
			}
	}

	/**
	 * @override
	 */
	// public String toString()
	// {
	// 	//numSuivants,]texteS|]texte
	// 	String numChoix = "";
	// 	String texteReponse = "";
	//
	// 	if(choix.size()>0)
	// 	{
	// 		for(int i=0; i<choix.size(); i++)
	// 		{
	// 			numChoix   += (i!=0?",":"") + choix.get(i).getInd();
	// 			texteReponse += (i!=0?"|":"") + choix.get(i).getReponse();
	// 		}
	// 	}
	//
	// 	return numChoix+"]"+texteReponse+"]"+texte;
	// }

}
