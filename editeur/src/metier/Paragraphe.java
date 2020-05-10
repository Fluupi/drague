package editeur.metier;

import java.util.ArrayList;

import ecouteMetier.AbstractModeleEcoutable;

/**
 * La classe Paragraphe représente un paragraphe de livre, elle permet toutes les
 * intéractions avec son texte et peut être liée à d'autres paragraphes à l'aide
 * de la classe Choix et ainsi permet toutes les intéractions avec cette même
 * classe
 *
 * @author A remplir
 * @version 2.0
 */
public class Paragraphe extends AbstractModeleEcoutable
{
	/**
	 * Réponses du Paragraphe
	 */
	private ArrayList<Choix> choix = new ArrayList<Choix>();
	/**
	 * Texte du Paragraphe
	 */
	private String   texte;

	/*  Constructeurs  */
	/**
	 * Permet de créer un Paragraphe à partir de son texte
	 *
	 * @param texte
	 */
	public Paragraphe(String texte)
	{
		this.texte = texte;
	}

	/**
	 * Permet de créer un Paragraphe en ajoutant directement sa liste de réponses
	 *
	 * @param suivants Index d
	 * @param texteS
	 * @param texte
	 */
	public Paragraphe(int[] suivants, String[] texteS, String texte)
	{
		this(texte);

		for(int i=0; i<suivants.length; i++)
			choix.add(new Choix(suivants[i], texteS[i]));
	}

	/**
	 * Permet la génération d'un Paragraphe à partir de sa description venant
	 * directement d'un fichier .ldveh
	 *
	 * @param desc description du Paragraphe
	 * @return Le paragraphe correspondant à la description fournie
	 */
	public static Paragraphe importer(String desc) {
		//--numP--numSuivants,]texteS|]texte

		if(desc.charAt(0)==']')
			return new Paragraphe(desc.substring(1));

		String[] description = desc.split("]");
		String[] numChoix    = description[0].split(",");
		String[] texteS      = description[1].split("\\|");

		String texte = description[2];
		int[] suivants = new int[numChoix.length];

		for(int i=0; i<numChoix.length; i++)
			suivants[i]=Integer.parseInt(numChoix[i]);

		return new Paragraphe(suivants, texteS, texte);
	}

	/*  Getters  */
	/**
	 * Permet de récupérer le texte du Paragraphe
	 *
	 * @return Le texte du paragraphe
	 */
	public String getTexte()
	{
		return texte;
	}

	/**
	 * Permet de vérifier si le Paragraphe est une fin de Livre
	 *
	 * @return True si le Paragraphe est une fin de Livre
	 */
	public boolean estFin()
	{
		return choix.isEmpty();
	}

	/**
	 * Permet de récupérer la liste des Choix du Paragraphe
	 *
	 * @return La liste des Choix
	 */
	public ArrayList<Choix> getListeChoix()
	{
		return choix;
	}

	/**
	 * Permet de récupérer l'index du paragraphe cible de la réponse d'index i
	 *
	 * @param i Index de la réponse
	 * @return Index du paragraphe cible
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
	 * Permet de modifier le texte du paragraphe
	 *
	 * @param texte Nouveau texte du paragraphe
	 */
	public void setTexte(String texte)
	{
		this.texte = texte;
	}

	/**
	 * Permet d'ajouter un choix si la liste n'est pas déjà trop remplie
 	 *
 	 * @param choix      Index du paragraphe cible
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
 	 * @param numPara      Index du nouveau paragraphe cible
 	 * @param texteReponse Nouveau texte de la réponse
	 */
	public void setChoix(int numReponse, int numPara, String texteReponse)
	{
		choix.get(numReponse).setInd(numPara);
		choix.get(numReponse).setReponse(texteReponse);
		signalChgt();
	}

	/**
	 * Permet de supprimer un choix lié à un certain paragraphe
	 *
	 * @param numPara Index du paragraphe lié
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
	public String toString()
	{
		//numSuivants,]texteS|]texte
		String numChoix = "";
		String texteReponse = "";

		if(choix.size()>0)
		{
			for(int i=0; i<choix.size(); i++)
			{
				numChoix   += (i!=0?",":"") + choix.get(i).getInd();
				texteReponse += (i!=0?"|":"") + choix.get(i).getReponse();
			}
		}

		return numChoix+"]"+texteReponse+"]"+texte;
	}

}
