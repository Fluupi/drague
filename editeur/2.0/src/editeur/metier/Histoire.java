package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Histoire représente une histoire et ainsi permet toutes les intéractions
 * liées à un histoire comme toutes les intéractions avec le titre et l'auteur,
 * celles avec les Chapitre
 *
 * @author A remplir
 * @version 2.0
 */
public class Histoire extends AbstractModeleEcoutable
{
	/**
	 * Titre de l'histoire
	 */
	private String titre;
	/**
	 * Auteur de l'histoire
	 */
	private String auteur;
	/**
	 * Nom de l'fichier stockant le histoire
	 */
	private String nomFichier;
	/**
	 *
	 */
	private ArrayList<String> illustrations = new ArrayList<String>();
	/**
	 * Liste des chapitres de l'histoire
	 */
	private ArrayList<Chapitre> chapitres = new ArrayList<Chapitre>();
	/**
	 * Liste des personnages de l'histoire
	 */
	private ArrayList<Personnage> personnages = new ArrayList<Personnage>();
	/**
	 * Liste des lieux de l'histoire
	 */
	private ArrayList<String> lieux = new ArrayList<String>();

	/*  Constructeur  */
	/**
	 * Constructeur de Histoire utilisant uniquement le titre et l'auteur et créant
	 * un premier chapitre
	 *
	 * @param titre  Titre de l'histoire
	 * @param auteur Auteur de l'histoire
	 */
	// public Histoire(String titre, String auteur)
	// {
	// 	this.titre      = titre;
	// 	this.nomFichier = this.titre.replace(" ", "_");
	// 	this.auteur     = auteur;
	//
	// 	chapitres.add(new Chapitre("1er chapitre"));
	// }
	//
	// /*  Getters  */
	// /**
	//  * Permet de récupérer le titre de l'histoire
	//  *
	//  * @return Titre de l'histoire
	//  */
	// public String getTitre()
	// {
	// 	return titre;
	// }
	//
	// /**
	//  * Permet de récupérer l'auteur de l'histoire
	//  *
	//  * @return Auteur de l'histoire
	//  */
	// public String getAuteur()
	// {
	// 	return auteur;
	// }
	//
	// /**
	//  * Permet de récupérer le nom de fichier de l'histoire
	//  *
	//  * @return Nom de fichier de l'histoire
	//  */
	// public String getNomFichier()
	// {
	// 	return nomFichier;
	// }
	//
	// /**
	//  * Permet de récupérer le Chapitre d'index i de l'histoire
	//  *
	//  * @param i Index de l'Chapitre à récupérer
	//  * @return Le Chapitre s'il est dans la liste
	//  */
	// public Chapitre getC(int i)
	// {
	// 	if(i>=0 && i<chapitres.size())
	// 		return chapitres.get(i);
	//
	// 	return null;
	// }
	//
	// /**
	//  * Permet de récupérer la taille de la liste de chapitres
	//  *
	//  * @return Taille de la liste de chapitres
	//  */
	// public int getTaille()
	// {
	// 	return chapitres.size();
	// }
	//
	// /**
	//  * Permet de récupérer la liste des chapitres de l'histoire
	//  *
	//  * @return Liste des chapitres
	//  */
	// public ArrayList<Chapitre> getListeChapitre()
	// {
	// 	return chapitres;
	// }
	//
	// /*  Setters  */
	// /**
	//  * Permet de modifier le titre de l'histoire
	//  *
	//  * @param newTitre nouveau titre de l'histoire
	//  */
	// public void modifierTitre(String newTitre)
	// {
	// 	this.titre = newTitre;
	// 	signalChgt();
	// }
	//
	// /**
	//  * Permet de modifier l'auteur de l'histoire
	//  *
	//  * @param newAuteur nouvel auteur de l'histoire
	//  */
	// public void modifierAuteur(String newAuteur)
	// {
	// 	this.auteur = newAuteur;
	// 	signalChgt();
	// }
	//
	// /**
	//  * Permet d'ajouter un nouveau chapitre à la liste
	//  *
	//  * @param texte Texte de l'nouveau chapitre
	//  */
	// public void creerChapitre(String texte)
	// {
	// 	chapitres.add(new Chapitre(texte));
	// 	signalChgt();
	// }
	//
	// /**
	//  * Permet d'ajouter un nouveau chapitre à la liste à un emplacement précis
	//  *
	//  * @param texte Texte de l'nouveau chapitre
	//  * @param i     Emplacement de l'nouveau chapitre dans la liste
	//  */
	// public void ajouterChapitre(int i, String texte)
	// {
	// 	chapitres.add(i, new Chapitre(texte));
	// 	signalChgt();
	// }
	//
	// /**
	//  * Permet de supprimer un chapitre à partir de son index
	//  *
	//  * @param numPara Index de l'chapitre à supprimer
	//  */
	// public void supprimerChapitre(int numPara)
	// {
	// 	//On supprime toutes les réponses qui ont pour cible le chapitre à
	// 	//supprimer
	// 	for(int i=0; i<chapitres.size(); i++)
	// 	{
	// 		supprimerLien(i, numPara);
	// 	}
	//
	// 	//On supprime le chapitre
	// 	this.chapitres.remove(numPara);
	// 	signalChgt();
	// }
	//
	// /**
	//  * Permet de supprimer un chapitre
	//  *
	//  * @param p Chapitre à supprimer
	//  */
	// public void supprimerChapitre(Chapitre p)
	// {
	// 	if(chapitres.contains(p))
	// 		supprimerChapitre(chapitres.indexOf(p));
	// }
	//
	// /**
	//  * Permet d'ajouter une réponse
	//  *
	//  * @param numPara1 Index de l'chapitre qui doit recevoir une nouvelle réponse
	//  * @param numPara2 Index de l'chapitre cible de la nouvelle réponse
	//  * @param texteS   Texte de la nouvelle réponse
	//  */
	// public void creerLien(int numPara1, int numPara2, String texteS)
	// {
	// 	creerLien(numPara1, numPara2, -1, texteS);
	// }
	//
	// /**
	//  * Permet d'ajouter une réponse à l'index numChoix
	//  *
	//  * @param numPara1 Index de l'chapitre qui doit recevoir une nouvelle réponse
	//  * @param numPara2 Index de l'chapitre cible de la nouvelle réponse
	//  * @param numChoix Numéro d'index où placer la nouvelle réponse
	//  * @param texteS   Texte de la nouvelle réponse
	//  */
	// public void creerLien(int numPara1, int numPara2, int numChoix, String texteS)
	// {
	// 	chapitres.get(numPara1).ajouterChoix(numPara2, numChoix, texteS);
	// }
	//
	// /**
	//  * Permet de modifier une réponse
	//  *
	//  * @param numPara1 Index de l'chapitre qui contient la réponse
	//  * @param numPara2 Index de l'nouveau chapitre cible de la réponse
	//  * @param numChoix Numéro d'index de la réponse
	//  * @param texteS   Nouveau texte de la réponse
	//  */
	// public void setLien(int numPara1, int numReponse, int numPara2, String texteS)
	// {
	// 	chapitres.get(numPara1).setChoix(numReponse, numPara2, texteS);
	// }
	//
	// /**
	//  * Permet de supprimer le lien (réponse) entre 2 chapitres
	//  *
	//  * @param numPara1 Index de l'chapitre qui contient la réponse
	//  * @param numPara2 Index de l'chapitre cible de la réponse
	//  */
	// public void supprimerLien(int numPara1, int numPara2)
	// {
	// 	chapitres.get(numPara1).supprimerLien(numPara2);
	// }
	//
	// /**
	//  * @override
	//  */
	// public String toString()
	// {
	// 	String retour = "[" + nomFichier + ":" + titre + ":" + auteur + "]\n";
	//
	// 	for(Chapitre p : chapitres)
	// 	{
	// 		retour += "--"+(chapitres.indexOf(p)+1)+"--"+p+"\n";
	// 	}
	//
	// 	return retour;
	// }
}
