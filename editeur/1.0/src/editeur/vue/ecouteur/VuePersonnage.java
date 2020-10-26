package editeur.vue.ecouteur;

import ecouteMetier.EcouteurModele;

import editeur.metier.Personnage;

public class VuePersonnage implements EcouteurModele
{
	private Personnage personnage;

	public VuePersonnage(Personnage p)
	{
		personnage = p;
		personnage.ajoutEcouteur(this);
	}

	public void modeleAJour(Object o)
	{
		personnage = (Personnage) o;
	}
}
