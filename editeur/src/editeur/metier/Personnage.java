package editeur.metier;

import ecouteMetier.AbstractModeleEcoutable;

public class Personnage extends AbstractModeleEcoutable {
	private String nom;
	private int[] state =new int[3];
	private boolean pnj;

	public Personnage(String nom)
	{
		this.nom=nom;
		//this.pnj=pnj;
	}

	public Personnage()
	{
		//this("aucun nom",false);
	}

	public String toString(){
		return "["+this.nom+"] [["+this.state[0]+"]["+this.state[1]+"]["+this.state[2]+"]] ["+this.pnj+"]";
	}

}
