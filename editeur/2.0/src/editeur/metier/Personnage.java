package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

public class Personnage extends AbstractModeleEcoutable
{
	private String  nom;
	private boolean isCrush;
	private ArrayList<String> images = new ArrayList<String>();
	private int affinite;
	private int prefereS;

	public Personnage(String nom, boolean isCrush, int affinite, int prefereS)
	{
		this.nom      = nom;
		this.isCrush  = isCrush;
		this.affinite = affinite;
		this.prefereS = prefereS;
	}

	public Personnage(String nom)
	{
		this(nom, false, 0, 0);
	}

	/*public String toString(){
		return "["+this.nom+"] [["+this.state[0]+"]["+this.state[1]+"]["+this.state[2]+"]] ["+this.pnj+"]";
	}*/

}
