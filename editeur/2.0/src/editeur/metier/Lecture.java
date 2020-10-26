package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

/**
 * La classe Choix représente une possibilité de réponse à un Enonce
 *
 * @author A remplir
 * @version 2.0
 */
public class Lecture extends AbstractModeleEcoutable
{
    private HashMap<String,Boolean> illustrations = new HashMap<String,Boolean>();
    private int sexePersoPrincipal;
    private int chapitreCourant;
    private int enonceCourant;
    private Personnage crush;

    public Lecture(Histoire h)
    {
        for(int i=0; i<h.getIlluNb(); i++)
        {
            illustrations.put(h.getIllu(i), new Boolean(false));
        }

        chapitreCourant = 1;
        enonceCourant   = 1;
        crush = null;
    }

    public Lecture importer(String lecture)
    {
        
    }
}
