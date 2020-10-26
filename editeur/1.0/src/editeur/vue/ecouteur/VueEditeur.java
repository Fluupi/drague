package editeur.vue.ecouteur;

import ecouteMetier.EcouteurModele;

import editeur.metier.Editeur;
import editeur.metier.Livre;

/**
 * La classe VueEditeur est une vue sur la classe Editeur, elle permet à la partie
 * vue du programme d'accéder facilement aux informations et modifications de la
 * classe Editeur
 *
 * @author A remplir
 * @version 2.0
 */
public class VueEditeur implements EcouteurModele
{
	/**
	 * Objet écouté
	 */
    private Editeur editeur;

    /**
     *
     */
	private VueLivre livreC;
	/**
	 *
	 */
    private VueParagraphe paragrapheC;

	/*  Constructeur  */
	/**
	 * Constructeur de l'écouteur de la classe Editeur
	 *
	 * @param e Objet à écouter
	 */
    public VueEditeur(Editeur e)
    {
        editeur = e;
        editeur.ajoutEcouteur(this);

        livreC = new VueLivre(editeur.getLivreC());
        paragrapheC = livreC.getVueP(editeur.getParagrapheCourant());
    }

    /*  Getters  */
    /**
	 * Permet de récupérer le livre courant
	 *
	 * @return Le livre courant
	 */
    public VueLivre getLivre()
    {
        return livreC;
    }

    /**
	 * Permet de récupérer le titre du livre courant
	 *
	 * @return Le titre du livre courant
	 */
	public String getTitreL()
    {
        return livreC.getTitre();
    }

    /**
     * Permet de récupérer le paragraphe courant
     *
     * @return Le paragraphe courant
     */
    public VueParagraphe getParagrapheC()
    {
        return paragrapheC;
    }

    /**
     * @override
     */
    public void modeleAJour(Object o)
    {
        editeur = (Editeur)o;
        livreC = new VueLivre(editeur.getLivreC());
        paragrapheC = livreC.getVueP(editeur.getParagrapheCourant());
    }
}
