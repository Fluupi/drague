package editeur.vue.ecouteur;

import ecouteMetier.EcouteurModele;

import editeur.metier.Livre;
import editeur.metier.Paragraphe;

import java.util.ArrayList;

/**
 * La classe VueLivre est une vue sur la classe Livre, elle permet à la partie
 * vue du programme d'accéder facilement aux informations et modifications de la
 * classe Livre
 *
 * @author A remplir
 * @version 2.0
 */

public class VueLivre implements EcouteurModele
{
	/**
	 * Objet écouté
	 */
    private Livre livre;

    private ArrayList<VueParagraphe> paragraphes = new ArrayList<VueParagraphe>();

	/*  Constructeur  */
	/**
	 * Constructeur de l'écouteur de la classe Livre
	 *
	 * @param l Objet à écouter
	 */
    public VueLivre(Livre l)
    {
        livre = l;
        livre.ajoutEcouteur(this);

        for(Paragraphe p : livre.getListeParagraphe())
            paragraphes.add(new VueParagraphe(p));
    }

    /*  Getters  */
	/**
	 * Permet de récupérer l'auteur du livre
	 *
	 * @return Auteur du livre
	 */
    public String getAuteur()
    {
        return livre.getAuteur();
    }

    /**
     * Permet de récupérer le titre du livre
     *
     * @return Titre du livre
     */
    public String getTitre()
    {
        return livre.getTitre();
    }

    /**
     * Permet de récupérer la vue du Paragraphe donné
     *
     * @return Vue du Paragraphe
     */
    public VueParagraphe getVueP(Paragraphe p)
    {
        for(VueParagraphe v : paragraphes)
            if(v.estVueDe(p))
                return v;
        return null;
    }

	/**
	 * Permet de récupérer le nom de fichier du livre
	 *
	 * @return Nom de fichier du livre
	 */
    public String getNomFichier()
    {
        return livre.getNomFichier();
    }

    /**
     * Permet de récupérer l'index de la VueParagraphe donné
     *
     * @return Index de la VueParagraphe
     */
    public int indexOf(VueParagraphe p)
    {
        return paragraphes.indexOf(p);
    }

	/**
	 * Permet de récupérer la taille de la liste de paragraphes
	 *
	 * @return Taille de la liste de paragraphes
	 */
    public int getNbParagraphes()
    {
        return paragraphes.size();
    }

	/**
	 * Permet de récupérer la VueParagraphe d'index i du livre
	 *
	 * @param i Index de la VueParagraphe à récupérer
	 * @return La VueParagraphe s'il est dans la liste
	 */
    public VueParagraphe get(int i)
    {
        return paragraphes.get(i);
    }


    /**
     * @override
     */
    public void modeleAJour(Object o)
    {
        livre = (Livre)o;

        paragraphes.clear();

        for(Paragraphe p : livre.getListeParagraphe())
            paragraphes.add(new VueParagraphe(p));
    }
}
