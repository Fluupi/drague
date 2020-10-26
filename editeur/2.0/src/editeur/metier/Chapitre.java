package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

public class Chapitre extends AbstractModeleEcoutable
{
    private int id;
    private ArrayList<Enonce> enonces = new ArrayList<Enonce>();

    public Chapitre(int id)
    {
        this.id = id;
    }
}
