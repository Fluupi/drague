package editeur;

import editeur.metier.Editeur;
import editeur.metier.Livre;
import editeur.metier.Paragraphe;

import editeur.vue.Fenetre;
import editeur.vue.ecouteur.VueEditeur;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * La classe Controleur représente le lien entre la partie graphique, que ce
 * soit écrite dans le terminal ou affichée par la partie vue, et la partie
 * métier. Elle s'occupe de faire passer toutes les instructions de l'utilisateur
 * à la partie métier et est l'élément qui est lancé en tout premier quand on
 * lance le programme et qui instancie les parties métier et vue (au besoin)
 *
 * @author A remplir
 * @version 2.0
 */
public class Controleur
{
	/**
	 * Représente la partie métier
	 */
	private Editeur editeur;
	/**
	 * Représente la partie vue
	 */
	private Fenetre frame;

	/**
	 * Permet de mettre en pause certains évènements lors de mises à jour de la
	 * partie vue
	 */
	private boolean estEnModif = false;

	/*  Constructeurs  */
	/**
	 * Constructeur ne prenant en compte que l'argument permettant de choisir
	 * entre la version terminal et la version ihm
	 *
	 * @param ihm Précise si le programme est à lancer avec la partie vue
	 */
 	public Controleur(char os, boolean ihm) throws InterruptedException
 	{
 		this.editeur = new Editeur(os);

 		if(ihm)
		{
			this.frame = new Fenetre(this, new VueEditeur(this.editeur));
		}
 		else
 			boucleModifLivre(true);
 	}

	/**
	 * Constructeur permettant d'ouvrir directement un livre existant
	 *
	 * @param ihm        Précise si le programme est à lancer avec la partie vue
	 * @param nomFichier Nom du fichier à ouvrir, si le fichier est introuvable
	 *                   alors l'éditeur créera un livre avec ce nom de fichier
	 */
 	public Controleur(char os, boolean ihm, String nomFichier)
 	{
 		this.editeur = new Editeur(os, nomFichier);

 		if(ihm)
		{
			this.frame = new Fenetre(this, new VueEditeur(this.editeur));
		}
		else
 			boucleModifLivre(false);
 	}

	/**
	 * Génère une boucle qui permet la lecture, l'édition, et la suppression de
	 * livres
	 *
	 * @param estNouveauLivre Précise si un nouveau livre doit être créé
	 */
	public void boucleModifLivre(boolean estNouveauLivre)
	{
		Scanner sc = new Scanner(System.in);

		if(estNouveauLivre)
		{
			System.out.println("Nom de l'auteur ?");
			String auteur = sc.nextLine();
			System.out.println();

			System.out.println("Titre du livre ?");
			String titre = sc.nextLine();
			System.out.println();

			editeur.setAuteurTitre(auteur, titre);
		}

		String reponse;
		Livre l = editeur.getLivreC();

		System.out.println("Vous lisez \"" + l.getTitre() + "\", de " + l.getAuteur() + "\n");

		do
		{
			System.out.println("(L)ire | (C)hanger de Livre | (S)auvegarde | (N)ouveau Livre");
			System.out.println("(a)jouter paragraphe | (l)ier paragraphes | (s)upprimer paragraphe | /q ");
			reponse = sc.nextLine();
			System.out.println();

			if(reponse.equals("L"))
			{
				Paragraphe p = l.getP(0);
				System.out.println(p.getTexte());
				System.out.println();

				for(int i=0; i<p.getNbChoix(); i++)
					System.out.println((i+1) + /*"("+p.getChoix(i)+")*/" : " + p.getTexteChoix(i));

				while(!p.estFin())
				{
					p = l.getP(p.getChoix(Integer.parseInt(sc.nextLine())-1)-1);
					System.out.print("\n-----------\n\n");

					System.out.println(p.getTexte());
					System.out.println();

					if(!p.estFin())
					{
						for(int i=0; i<p.getNbChoix(); i++)
							System.out.println((i+1) + /*"("+p.getChoix(i)+")"+*/" : " + p.getTexteChoix(i));
					}
				}

				System.out.println("-----------\n----FIN----\n-----------");
			}

			else if(reponse.equals("C"))
			{
				int i=0;

				for(String s : editeur.getTitresLivres())
					System.out.println((i++) + " - " + s);

				System.out.println();

				int numLivre = Integer.parseInt(sc.nextLine());

				editeur.setLivreC(editeur.chargerLivre(editeur.getTitresLivres().get(numLivre)));
				l = editeur.getLivreC();
			}
			else if(reponse.equals("S"))
			{
				editeur.exporterLivreC();
			}
			else if(reponse.equals("N"))
			{
				System.out.println("Creation du nouveau livre");
				System.out.println("Nom de l'auteur ?");
				String auteur = sc.nextLine();
				System.out.println();
				System.out.println("Titre du livre ?");
				String titre = sc.nextLine();
				System.out.println();
				//creation du livre et changement du livre courant
				creerLivre(titre,auteur);
			}
			else if(reponse.equals("a"))
			{
				ajouterParagraphe(sc);
			}
			else if(reponse.equals("l"))
			{
				System.out.println(editeur.livreToString());

				System.out.println("Premier paragraphe ?");

				int premierPara = Integer.parseInt(sc.nextLine())-1;
				int secondPara;

				do
				{
					System.out.print("Paragraphe réponse : un nouveau ? (o/n) => ");
					reponse = sc.nextLine();
				} while (!(reponse.equals("o") | reponse.equals("n")));

				if(reponse.equals("o"))
				{
					secondPara = ajouterParagraphe(sc);
				}
				else
				{
					System.out.println("Second paragraphe ?");
					secondPara  = Integer.parseInt(sc.nextLine());
				}

				System.out.println("Texte Réponse ?");
				String texteRep = sc.nextLine();

				lierParagraphes(premierPara, secondPara, texteRep);
			}
			else if(reponse.equals("s"))
			{
				editeur.livreToString();

				System.out.println("Supprimer quel paragraphe ?");

				int numPara = Integer.parseInt(sc.nextLine());

				editeur.supprimerParagraphe(numPara);

				System.out.println(editeur.livreToString());
			}
		}
		while(!reponse.equals("/q"));
	}

	/*  Getters  */
	/**
	* Permet de récupérer la liste des noms de fichiers de livres trouvés dans
	* le dossier res
	*
	* @return Liste des noms de fichiers de livres
	*/
	public ArrayList<String> getListeTitreLivre()
	{
		return editeur.getTitresLivres();
	}

	/**
	 * Permet de récupérer le livre ouvert
	 *
	 * @return Le livre ouvert
	 */
	public Livre getLivreCourant()
	{
		return editeur.getLivreC();
	}

	/**
	 * Permet de récupérer le paragraphe en lecture
	 *
	 * @return Le paragraphe en lecture
	 */
	public Paragraphe getParagrapheCourant()
	{
		return editeur.getParagrapheCourant();
	}

	/**
	 * Permet de récupérer l'instance de la Fenetre de la partie vu
	 *
	 * @return La fenêtre
	 */
	public Fenetre getFrame()
	{
		return frame;
	}

	/**
	 * Permet de vérifier si une mise à jour de l'interface graphique est en
	 * cours
	 *
	 * @return Vrai si une mise à jour de l'interface est en cours
	 */
	public boolean estEnModif()
	{
		return estEnModif;
	}

	/*  Setters  */
	/**
	 * Permet de modifier le titre du livre ouvert
	 *
	 * @param titre Nouveau titre du livre
	 */
	public void modifierTitre(String titre)
	{
		editeur.modifierTitre(titre);
	}

	/**
	 * Permet de modifier l'auteur du livre ouvert
	 *
	 * @param auteur Nouvel auteur du livre
	 */
	public void modifierAuteur(String auteur)
	{
		editeur.modifierAuteur(auteur);
	}

	/**
	 * Permet de créer un nouveau livre à partir d'un titre et d'un auteur
	 *
	 * @param titre  Titre du nouveau livre
	 * @param auteur Auteur du nouveau livre
	 */
	public void creerLivre(String titre, String auteur)
	{
		editeur.creerLivre(titre, auteur);
	}

	/**
	 * Permet de changer de livre courant, le nouveau étant le livre d'index i
	 *
	 * @param i Nouveau livre courant
	 */
	public void changerLivreCourant(int i)
	{
		editeur.setLivreC(editeur.chargerLivre(editeur.getTitresLivres().get(i)));
	}

	/**
	 * Permet de sauvegarder le livre courant dans le fichier correspondant
	 */
	public void exporterLivreC()
	{
		editeur.exporterLivreC();
	}

	/**
	 * Permet de supprimer le livre courant, avec son fichier
	 */
	public void supprimerLivre()
	{
		editeur.supprimerLivre();
	}

	/**
	 * Permet à la version terminal de créer un nouveau paragraphe pour le livre
	 * ouvert, et renvoie la nouvelle taille de ce dernier
	 *
	 * @param sc Scanner permettant la communication avec l'utilisateur
	 * @return Nouvelle taille du livre
	 */
	public int ajouterParagraphe(Scanner sc)
	{
		String reponse;

		//On s'assure que le texte du paragraphe à créer n'est pas vide
		do
		{
			System.out.println("Texte nouveau paragraphe ? (non vide)");
			reponse = sc.nextLine();
		} while (reponse.equals(""));

		//On crée le nouveau paragraphe et on renvoie la taille
		creerParagraphe(reponse);
		return editeur.getTailleLivre();
	}

	/**
	 * Permet de créer un paragraphe à partir d'un texte
	 *
	 * @param texte Texte du nouveau paragraphe
	 */
	public void creerParagraphe(String texte)
	{
		editeur.creerParagraphe(texte);
	}

	/**
	 * Change de paragraphe courant, le nouveau étant le paragraphe d'index i
	 *
	 * @param i index du nouveau paragraphe courant
	 */
	public void changerParagrapheCourant(int i)
	{
		editeur.changerParagrapheCourant(i);
	}

	/**
	 * Permet de supprimer le paragraphe courant
	 */
	public void supprimerParagrapheC()
	{
		editeur.supprimerParagrapheC();
	}

	/**
	 * Permet de modifier le texte du paragraphe courant
	 *
	 * @param texte Nouveau texte du paragraphe courant
	 */
	public void setTexteCourant(String texte)
	{
		editeur.setTexteCourant(texte);
	}

	/**
	 * Crée un nouveau lien entre paragraphes à partir des index de 2 paragraphes
	 * et d'un texte de réponse
	 *
	 * @param premierPara Index du paragraphe auquel il faut ajouter une réponse
	 * @param secondPara  Index du paragraphe cible de la réponse
	 * @param texteRep    Texte de la réponse
	 */
	public void lierParagraphes(int premierPara, int secondPara, String texteRep)
	{
		editeur.lierParagraphes(premierPara, secondPara, texteRep);
	}

	/**
	 * Transforme un lien entre paragraphes à partir des index de 2 paragraphes,
	 * d'un index de réponse, et d'un texte de réponse
	 *
	 * @param premierPara Index du paragraphe dont il faut changer une réponse
	 * @param numReponse  Index de la réponse à modifier
	 * @param secondPara  Index du nouveau paragraphe cible de la réponse
	 * @param texteRep    Nouveau texte de la réponse
	 */
	public void setLienParagraphes(int premierPara, int numReponse, int secondPara, String texteRep)
	{
		editeur.setLienParagraphes(premierPara, numReponse, secondPara, texteRep);
	}

	/**
	 * Permet de supprimer le paragraphe d'index numPara
	 *
	 * @param numPara Index du paragraphe à supprimer
	 */
	public void supprimerPara(int numPara)
	{
		editeur.supprimerParagraphe(numPara);
	}

	/**
	 * Supprime un lien entre paragraphes à partir des index des 2 paragraphes
	 *
	 * @param numPara1 Index du paragraphe dont il faut supprimer la réponse
	 * @param numPara2 Index du paragraphe cible de le réponse
	 */
	public void supprimerLien(int numPara1, int numPara2)
	{
		editeur.supprimerLien(numPara1,numPara2);
	}

	/**
	 * Permet au paragraphe courant de retourner au premier paragraphe du livre
	 */
	public void retourPremierParagraphe()
	{
		editeur.changerParagrapheCourant(0);
	}

	/**
	 * Permet de lancer la mise à jour de l'interface graphique
	 */
	public void majUI()
	{

		estEnModif = true;
		frame.majUI();
		estEnModif = false;
	}

	/**
	 * Main : méthode permettant de lancer le programme en fonction des arguments
	 * fournis
	 */
	public static void main(String[] args) throws InterruptedException
	{
		if(args.length==3)
			new Controleur(args[0].charAt(0), args[1].equals("o"), args[2]);
		else
			new Controleur(args[0].charAt(0), args[1].equals("o"));
	}

}
