package editeur.vue.ecouteur;

import ecouteMetier.EcouteurModele;

import editeur.metier.Paragraphe;
import editeur.metier.Choix;

import java.util.ArrayList;

/**
 * La classe VueParagraphe est une vue sur la classe Paragraphe, elle permet à la partie
 * vue du programme d'accéder facilement aux informations et modifications de la
 * classe Paragraphe
 *
 * @author A remplir
 * @version 2.0
 */

public class VueParagraphe implements EcouteurModele
{
	/**
	 * Objet écouté
	 */
    private Paragraphe paragraphe;

    private ArrayList<VueChoix> choix = new ArrayList<VueChoix>();

	/*  Constructeur  */
	/**
	 * Constructeur de l'écouteur de la classe Paragraphe
	 *
	 * @param p Objet à écouter
	 */
    public VueParagraphe(Paragraphe p)
    {
        paragraphe = p;
        paragraphe.ajoutEcouteur(this);

        for(Choix c : paragraphe.getListeChoix())
            choix.add(new VueChoix(c));
    }

    /*  Getters  */
    /**
     * Permet de confirmer si cette VueParagraphe est l'écouteur du Paragraphe donné
     *
     * @param p Paragraphe testé
     * @return Vrai si this est la Vue de p
     */
    public boolean estVueDe(Paragraphe p)
    {
        return p.equals(paragraphe);
    }

	/**
     * Permet de récupérer le texte de la VueParagraphe
     *
     * @return Le texte du paragraphe
     */
    public String getTexte()
    {
        return paragraphe.getTexte();
    }

    /**
	 * Permet de récupérer la taille de la liste de VueChoix
	 *
	 * @return La taille
	 */
    public int getNbChoix()
    {
        return paragraphe.getNbChoix();
    }

	/**
	 * Permet de récupérer l'index du paragraphe cible de la réponse d'index i
	 *
	 * @param i Index de la réponse
	 * @return Index du paragraphe cible
	 */
    public int getChoix(int i)
    {
        return paragraphe.getChoix(i);
    }

	/**
	 * Permet de récupérer le texte de la réponse d'index i
	 *
	 * @param i Index de la réponse
	 * @return Texte de la réponse
	 */
    public String getTexteChoix(int i)
    {
        return paragraphe.getTexteChoix(i);
    }

    /**
     * @override
     */
    public void modeleAJour(Object o)
    {
        paragraphe = (Paragraphe)o;

        choix.clear();

        for(Choix c : paragraphe.getListeChoix())
            choix.add(new VueChoix(c));
    }
}
