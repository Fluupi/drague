package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Livre représente un livre et ainsi permet toutes les intéractions
 * liées à un livre comme toutes les intéractions avec le titre et l'auteur,
 * celles avec les Paragraphes et leurs Choix
 *
 * @author A remplir
 * @version 2.0
 */
public class Livre extends AbstractModeleEcoutable
{
	/**
	 * Titre du livre
	 */
	private String titre;
	/**
	 * Auteur du livre
	 */
	private String auteur;
	/**
	 * Nom du fichier stockant le livre
	 */
	private String nomFichier;
	/**
	 * Liste des paragraphes du livre
	 */
	private ArrayList<Paragraphe> paragraphes = new ArrayList<Paragraphe>();

	/*  Constructeurs  */
	/**
	 * Constructeur de Livre utilisant uniquement le titre et l'auteur et créant
	 * un premier paragraphe
	 *
	 * @param titre  Titre du livre
	 * @param auteur Auteur du livre
	 */
	public Livre(String titre, String auteur)
	{
		this.titre  = titre;
		this.auteur = auteur;

		paragraphes.add(new Paragraphe("1er paragraphe"));
	}

	/**
	 * Constructeur de Livre utilisant un nom de fichier pour extraire livre
	 * entier à partir du dossier res
	 *
	 * @param nomFichier
	 */
	public Livre(String nomFichier)
	{
		try
		{
			//Définition du fichier à extraire
			Scanner sc = new Scanner(new File(Editeur.CHEMIN_RES+nomFichier+".ldveh"));
			this.nomFichier = nomFichier;

			//Extraction de la première ligne du fichier (nomFichier:titre:auteur)
			String[] desc = sc.nextLine().split(":");
			titre  = desc[1];
			auteur = desc[2].substring(0, desc[2].length()-1);

			//Définition des variables nécessaires à l'extraction des paragraphes
			String ligne;
			String numPara;
			int i;

			//Pour chaque ligne de paragraphe
			while(sc.hasNextLine())
			{
				//On extrait la ligne à partir du début de son contenu
				ligne = sc.nextLine().substring(2);
				numPara = "";

				//Extraction du contenu de la ligne de paragraphe
				for(i=0; i<ligne.length()-2 && !ligne.substring(i, i+2).equals("--"); i++)
					numPara+=ligne.substring(i,i+1);

				//On importe un Paragraphe à partir de cette ligne et on l'ajoute
				//à la liste des paragraphes du Livre
				paragraphes.add(Paragraphe.importer(ligne.substring(i+2)));
			}

			sc.close();
		} //Si le fichier de livre n'est pas trouvé
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/*  Getters  */
	/**
	 * Permet de récupérer le titre du livre
	 *
	 * @return Titre du livre
	 */
	public String getTitre()
	{
		return titre;
	}

	/**
	 * Permet de récupérer l'auteur du livre
	 *
	 * @return Auteur du livre
	 */
	public String getAuteur()
	{
		return auteur;
	}

	/**
	 * Permet de récupérer le nom de fichier du livre
	 *
	 * @return Nom de fichier du livre
	 */
	public String getNomFichier()
	{
		return nomFichier;
	}

	/**
	 * Permet de récupérer le Paragraphe d'index i du livre
	 *
	 * @param i Index du Paragraphe à récupérer
	 * @return Le Paragraphe s'il est dans la liste
	 */
	public Paragraphe getP(int i)
	{
		if(i>=0 && i<paragraphes.size())
			return paragraphes.get(i);

		return null;
	}

	/**
	 * Permet de récupérer la taille de la liste de paragraphes
	 *
	 * @return Taille de la liste de paragraphes
	 */
	public int getTaille()
	{
		return paragraphes.size();
	}

	/**
	 * Permet de récupérer la liste des paraphes du livre
	 *
	 * @return Liste des paragraphes
	 */
	public ArrayList<Paragraphe> getListeParagraphe()
	{
		return paragraphes;
	}

	/*  Setters  */
	/**
	 * Permet de sauvegarder le contenu du livre dans le fichier correspondant
	 * Crée le fichier au besoin
	 */
	public void exporter()
	{
		File f;

		if(nomFichier==null)
		{
			nomFichier = titre.replaceAll(" ", "_") ;
			f = new File(Editeur.CHEMIN_RES+nomFichier+".ldveh");
			if(!f.isFile())
			{
				try
				{
					f.createNewFile();
				}
				catch(IOException e)
				{
					System.out.println("AH (création de fichier)");
				}
			}
		}
		else
		{
			f = new File(Editeur.CHEMIN_RES+nomFichier+".ldveh");
		}

		try
		{
			FileWriter fw = new FileWriter(f,false);
			fw.write(""+this);
			fw.close();
		}
		catch(IOException ioe)
		{
			System.err.println("IOException:" + ioe.getMessage());
		}
	}

	/**
	 * Permet de modifier le titre du livre
	 *
	 * @param newTitre nouveau titre du livre
	 */
	public void modifierTitre(String newTitre)
	{
		this.titre = newTitre;
		signalChgt();
	}

	/**
	 * Permet de modifier l'auteur du livre
	 *
	 * @param newAuteur nouvel auteur du livre
	 */
	public void modifierAuteur(String newAuteur)
	{
		this.auteur = newAuteur;
		signalChgt();
	}

	/**
	 * Permet d'ajouter un nouveau paragraphe à la liste
	 *
	 * @param texte Texte du nouveau paragraphe
	 */
	public void creerParagraphe(String texte)
	{
		paragraphes.add(new Paragraphe(texte));
		signalChgt();
	}

	/**
	 * Permet de supprimer un paragraphe à partir de son index
	 *
	 * @param numPara Index du paragraphe à supprimer
	 */
	public void supprimerParagraphe(int numPara)
	{
		//On supprime toutes les réponses qui ont pour cible le paragraphe à
		//supprimer
		for(int i=0; i<paragraphes.size(); i++)
		{
			supprimerLien(i, numPara);
		}

		//On supprime le paragraphe
		this.paragraphes.remove(numPara);
		signalChgt();
	}

	/**
	 * Permet de supprimer un paragraphe
	 *
	 * @param p Paragraphe à supprimer
	 */
	public void supprimerParagraphe(Paragraphe p)
	{
		if(paragraphes.contains(p))
			supprimerParagraphe(paragraphes.indexOf(p));
	}

	/**
	 * Permet d'ajouter une réponse
	 *
	 * @param numPara1 Index du paragraphe qui doit recevoir une nouvelle réponse
	 * @param numPara2 Index du paragraphe cible de la nouvelle réponse
	 * @param texteS   Texte de la nouvelle réponse
	 */
	public void creerLien(int numPara1, int numPara2, String texteS)
	{
		creerLien(numPara1, numPara2, -1, texteS);
	}

	/**
	 * Permet d'ajouter une réponse à l'index numChoix
	 *
	 * @param numPara1 Index du paragraphe qui doit recevoir une nouvelle réponse
	 * @param numPara2 Index du paragraphe cible de la nouvelle réponse
	 * @param numChoix Numéro d'index où placer la nouvelle réponse
	 * @param texteS   Texte de la nouvelle réponse
	 */
	public void creerLien(int numPara1, int numPara2, int numChoix, String texteS)
	{
		paragraphes.get(numPara1).ajouterChoix(numPara2, numChoix, texteS);
	}

	/**
	 * Permet de modifier une réponse
	 *
	 * @param numPara1 Index du paragraphe qui contient la réponse
	 * @param numPara2 Index du nouveau paragraphe cible de la réponse
	 * @param numChoix Numéro d'index de la réponse
	 * @param texteS   Nouveau texte de la réponse
	 */
	public void setLien(int numPara1, int numReponse, int numPara2, String texteS)
	{
		paragraphes.get(numPara1).setChoix(numReponse, numPara2, texteS);
	}

	/**
	 * Permet de supprimer le lien (réponse) entre 2 paragraphes
	 *
	 * @param numPara1 Index du paragraphe qui contient la réponse
	 * @param numPara2 Index du paragraphe cible de la réponse
	 */
	public void supprimerLien(int numPara1, int numPara2)
	{
		paragraphes.get(numPara1).supprimerLien(numPara2);
	}

	/**
	 * @override
	 */
	public String toString()
	{
		String retour = "[" + nomFichier + ":" + titre + ":" + auteur + "]\n";

		for(Paragraphe p : paragraphes)
		{
			retour += "--"+(paragraphes.indexOf(p)+1)+"--"+p+"\n";
		}

		return retour;
	}
}
