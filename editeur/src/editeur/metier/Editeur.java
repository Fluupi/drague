package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * La classe Editeur rassemble toutes les méthodes permettant de créer, supprimer,
 * modifier et consulter un Livre
 *
 * @author A remplir
 * @version 2.0
 */
public class Editeur extends AbstractModeleEcoutable
{
	/**
	 * Chemin vers dossier des ressources (emplacement des fichiers de livres)
	 */
	public static final String CHEMIN_RES = "../res/";

	/**
	 * Noms de fichier des livres présents dans le dossier res
	 */
	private ArrayList<String> livres = new ArrayList<String>();

	/**
	 * Livre en lecture
	 */
	private Livre      livreCourant;

	/**
	 * Paragraphe sélectionné du livreCourant
	 */
	private Paragraphe paragrapheCourant = null;

	/*  Constructeurs  */
	/**
	 * Constructeur utilisant un nom de fichier de livre pour en ouvrir un
	 * en particulier si le fichier correspondant existe dans le dossier res
	 *
	 * @param nomFichier Nom du fichier à ouvrir
	 */
	public Editeur(String nomFichier)
	{
		chargerTitresLivres();

		if(livres.contains(nomFichier))
			livreCourant = new Livre(nomFichier);
		else
			creerLivre(nomFichier, "auteur");

		paragrapheCourant = livreCourant.getP(0);
	}

	/**
	 * Constructeur ouvrant le premier livre présent dans le dossier res, si le
	 * dossier est vide, crée un livre et l'ouvre
	 */
	public Editeur()
	{
		chargerTitresLivres();

		if(livres.size()!=0)
			livreCourant = new Livre(livres.get(0));
		else
			creerLivre("titre", "auteur");

		paragrapheCourant = livreCourant.getP(0);
	}

	/*  Chargement et Modification de fichiers  */
	/**
	 * Chargement des noms de fichiers de livres présents dans le dossier res
	 */
	public void chargerTitresLivres()
	{
		File[] repertoire = new File(Editeur.CHEMIN_RES).listFiles();
		livres.clear();

		for(int i=0; i<repertoire.length; i++)
			if(Pattern.matches("[a-zA-Z0-9_]+.ldveh", repertoire[i].getName()))
				livres.add(
					repertoire[i].getName().substring(0, repertoire[i].getName().length()-6)
							);
	}

	/**
	 * Exportation du livre courant par sauvegarde de son contenu dans un/son fichier
	 */
	public void exporterLivreC()
	{
		//Définition de la structure XML
		Element racine = new Element("livre");

		Element titre = new Element("titre");
		titre.setText(livreCourant.getTitre());
		racine.addContent(titre);

		Element auteur = new Element("auteur");
		auteur.setText(livreCourant.getAuteur());
		racine.addContent(auteur);

		Element paragraphes = new Element("paragraphes");

		for(int i=0; i<livreCourant.getTaille(); i++)
		{
			Paragraphe p = livreCourant.getP(i);

			Element paragraphe = new Element("paragraphe");
			paragraphe.setAttribute(new Attribute("num",""+(i+1)));
			paragraphe.setAttribute(new Attribute("lieu","N/A"));

			Element texteParagraphe = new Element("texte");
			texteParagraphe.setText(p.getTexte());
			paragraphe.addContent(texteParagraphe);

			if(p.getNbChoix()>0)
			{
				Element choix;
				Element texteChoix;
				Element numChoix;

				for(int j=0; j<p.getNbChoix(); j++)
				{
					choix = new Element("choix");

					choix.setAttribute(new Attribute("cible",""+p.getChoix(j)));
					choix.setText(p.getTexteChoix(j));

					paragraphe.addContent(choix);
				}
			}

			paragraphes.addContent(paragraphe);
		}

		racine.addContent(paragraphes);

		Element lieux = new Element("lieux");

		racine.addContent(lieux);

		Element personnages = new Element("personnages");

		racine.addContent(personnages);

		org.jdom2.Document document = new Document(racine);

		//Exportation de la structure
		try
		{
			String dossier = CHEMIN_RES + livreCourant.getNomFichier();
			new File(dossier).mkdir();

			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(dossier+"/livre.xml"));
		}
		catch (IOException e){}
	}

	/**
	 * Crée un nouveau livre à partir d'un titre et d'un auteur et l'ouvre
	 *
	 * @param titre Titre du livre créé
	 * @param auteur Auteur du livre créé
	 */
	public void creerLivre(String titre, String auteur)
	{
		this.livreCourant = new Livre(titre, auteur);

		exporterLivreC();
		chargerTitresLivres();
	}

	/**
	 * Supprime le fichier du livre courant et ouvre un autre livre
	 */
	public void supprimerLivre()
	{
		try
		{
			File f = new File(Editeur.CHEMIN_RES + livreCourant.getNomFichier()+".ldveh");

			if(f.delete())
				System.out.println(Editeur.CHEMIN_RES + livreCourant.getNomFichier()+".ldveh");
		}
		catch(Exception e) { System.out.println("suppression ratée"); }

		chargerTitresLivres();

		if(livres.size()!=0)
			livreCourant = new Livre(livres.get(0));
		else
			creerLivre("titre", "auteur");

		changerParagrapheCourant(0);
		signalChgt();
	}

	/*  Getters  */
	/**
	 * Permet de récupérer les noms des fichiers de livres trouvés dans le
	 * dossier res
	 *
	 * @return Les noms des fichiers de livres trouvés dans le dossier res
	 */
	public ArrayList<String> getTitresLivres()
	{
		return livres;
	}

	/**
	 * Permet de récupérer le livre courant
	 *
	 * @return Le livre courant
	 */
	public Livre getLivreC()
	{
		return livreCourant;
	}

	/**
	 * Permet de récupérer les informations et le contenu d'un livre
	 *
	 * @return La version toString d'un Livre
	 */
	public String livreToString()
	{
		return "" + livreCourant;
	}

	/**
	 * Permet de récupérer le nombre de paragraphes du livre courant
	 *
	 * @return Taille du livre courant
	 */
	public int getTailleLivre()
	{
		return livreCourant.getTaille();
	}

	/**
	 * Permet de récupérer le paragraphe courant
	 *
	 * @return Le paragraphe courant
	 */
	public Paragraphe getParagrapheCourant()
	{
		return paragrapheCourant;
	}

	/*  Setters  */
	/**
	 * Permet de changer auteur et titre du livre courant
	 *
	 * @param newAuteur Nouvel auteur du livre courant
	 * @param newTitre  Nouveau titre du livre courant
	 */
	public void setAuteurTitre(String newAuteur, String newTitre)
	{
		if(!newAuteur.equals(""))
			modifierAuteur(newAuteur);
		if(!newTitre.equals(""))
			modifierTitre(newTitre);
	}

	/**
	 * Modifie le titre du livre courant
	 *
	 * @param titre Nouveau titre du livre courant
	 */
	public void modifierTitre(String titre)
	{
		livreCourant.modifierTitre(titre);
	}

	/**
	 * Modifie l'auteur du livre courant
	 *
	 * @param auteur Nouvel auteur du livre courant
	 */
	public void modifierAuteur(String auteur)
	{
		livreCourant.modifierAuteur(auteur);
	}

	/**
	 * Permet de changer de livre courant
	 *
	 * @param livre Nouveau livre courant
	 */
	public void setLivreC(Livre livre)
	{
		livreCourant = livre;

		changerParagrapheCourant(0);
		signalChgt();
	}

	/**
	 * Permet de créer un paragraphe à partir de son texte
	 *
	 * @param texte Texte du nouveau paragraphe
	 */
	public void creerParagraphe(String texte)
	{
		livreCourant.creerParagraphe(texte);
	}

	/**
	 * Permet de supprimer le paragraphe d'index i
	 *
	 * @param i Index du paragraphe à supprimer
	 */
	public void supprimerParagraphe(int i)
	{
		if(livreCourant.getP(i)==paragrapheCourant)
			supprimerParagrapheC();
		else
			livreCourant.supprimerParagraphe(i);
	}

	/**
	 * Permet de supprimer le paragraphe courant, le paragraphe courant devenant
	 * le premier paragraphe du livre
	 */
	public void supprimerParagrapheC()
	{
		livreCourant.supprimerParagraphe(paragrapheCourant);

		changerParagrapheCourant(0);
	}

	/**
	 * Change de paragraphe courant, le nouveau étant le paragraphe d'index i
	 *
	 * @param i index du nouveau paragraphe courant
	 */
	public void changerParagrapheCourant(int i)
	{
		if(i < livreCourant.getTaille() && i >= 0)
			paragrapheCourant = livreCourant.getP(i);
		else if(livreCourant.getTaille()>=1)
			paragrapheCourant = livreCourant.getP(0);
		else
		{
			creerParagraphe("Nouveau Paragraphe");
			paragrapheCourant = livreCourant.getP(0);
		}

		signalChgt();
	}

	/**
	 * Crée un nouveau lien entre paragraphes à partir des index de 2 paragraphes
	 * et d'un texte de réponse
	 *
	 * @param numPara1 Index du paragraphe auquel il faut ajouter une réponse
	 * @param numPara2 Index du paragraphe cible de la réponse
	 * @param texteS   Texte de la réponse
	 */
	public void lierParagraphes(int numPara1, int numPara2, String texteS)
	{
		livreCourant.creerLien(numPara1, numPara2, texteS);
	}

	/**
	 * Transforme un lien entre paragraphes à partir des index de 2 paragraphes,
	 * d'un index de réponse, et d'un texte de réponse
	 *
	 * @param numPara1   Index du paragraphe dont il faut changer une réponse
	 * @param numReponse Index de la réponse à modifier
	 * @param numPara2   Index du nouveau paragraphe cible de la réponse
	 * @param texteS     Nouveau texte de la réponse
	 */
	public void setLienParagraphes(int numPara1, int numReponse, int numPara2, String texteS)
	{
		livreCourant.setLien(numPara1, numReponse, numPara2, texteS);
	}

	/**
	 * Supprime un lien entre paragraphes à partir des index des 2 paragraphes
	 *
	 * @param numPara1 Index du paragraphe dont il faut supprimer la réponse
	 * @param numPara2 Index du paragraphe cible de le réponse
	 */
	public void supprimerLien(int numPara1, int numPara2)
	{
		livreCourant.supprimerLien(numPara1,numPara2);
	}

	/**
	 * Permet de changer le texte du paragraphe courant
	 *
	 * @param texte Nouveau texte du paragraphe courant
	 */
	public void setTexteCourant(String texte)
	{
		paragrapheCourant.setTexte(texte);
	}
}
