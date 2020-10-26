package editeur.vue.ecouteur;

import ecouteMetier.EcouteurModele;

import editeur.metier.Evenement;

public class VueEvenement implements EcouteurModele
{
	private Evenement evenement;

	public VueEvenement(Evenement e)
	{
		evenement = e;
		evenement.ajoutEcouteur(this);
	}

	public void modeleAJour(Object o)
	{
		evenement = (Evenement) o;
	}
}
