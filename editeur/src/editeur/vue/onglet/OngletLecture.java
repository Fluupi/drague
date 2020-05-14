package editeur.vue.onglet;

import editeur.Controleur;

import editeur.vue.Fenetre;

import editeur.vue.ecouteur.VueEditeur;
import editeur.vue.ecouteur.VueParagraphe;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * La classe OngletLecture représente un onglet de la partie vue de l'application
 * editeur.
 * Elle affiche le texte du paragraphe courant et propose ses réponses sous forme
 * de boutons pour proposer une expérience de lecture facilitée
 *
 * @author A remplir
 * @version 2.0
 */
public class OngletLecture extends JPanel implements ActionListener
{
	/**
	 * Controleur, permet toutes les modifications sur la partie métier
	 */
	private Controleur ctrl;

	/**
	 * Vue de l'éditeur, permettant d'accéder à toutes les informations nécessaires
	 * à la lecture d'un livre
	 */
	private VueEditeur editeur;

	/**
	 * Vue du Paragraphe en lecture
	 */
	private VueParagraphe paragrapheCourant;

	/**
	 * Bouton permettant de reprendre la lecture de 0
	 */
	private JButton btnRelance = new JButton("Recommencer");

	/**
	 * Liste de boutons représentant les différents choix du Paragraphe courant
	 */
	private ArrayList<JButton> boutons = new ArrayList<JButton>();

	/**
	 * Panel contenant soit le bouton de relance, soit les boutons réponse
	 */
	private JPanel reponses;

	/**
	 * Aire de texte pour afficher le texte du Paragraphe
	 */
	private JTextArea texte;

	/*  Constructeur  */
	/**
	 * Constructeur mettant en place la structure et l'esthétique statiques de
	 * l'onglet
	 *
	 * @param ctrl Controleur
	 * @param e    VueEditeur
	 */
	public OngletLecture(Controleur ctrl, VueEditeur e)
	{
		this.ctrl = ctrl;
		this.editeur = e;

		/*********************************************************************/
		/*                       Découpage de l'onglet                       */
		/*********************************************************************/
		setLayout(new GridLayout(2,1));

		JPanel panH = new JPanel(new BorderLayout());
		reponses    = new JPanel();

		add(panH);
		add(reponses);

		//----------        Détail de panH        ----------\\
		texte             = new JTextArea(5,5);
		JPanel panLbChoix = new JPanel();

		panH.add(texte);
		panH.add(panLbChoix, BorderLayout.SOUTH);


		JLabel lbChoix = new JLabel("F A I T E S   V O T R E   C H O I X");
		panLbChoix.add(lbChoix); //On met lbChoix dans un JPanel pour le centrer



		/*********************************************************************/
		/*                            Esthétique                             */
		/*********************************************************************/
		setBorder(BorderFactory.createEmptyBorder(30,90,150,90));

		//On souhaite un retour à ligne automatique et que les mots ne soient
		//pas coupés
		texte.setLineWrap(true);
		texte.setWrapStyleWord(true);
		texte.setEditable(false);
		texte.setFont(new Font("TimesRoman",Font.PLAIN,25));
		texte.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
		texte.setPreferredSize(new Dimension(1000,200));

		lbChoix.setFont(Fenetre.FONT_LB);

		/*********************************************************************/
		/*                            Listeners                              */
		/*********************************************************************/
		btnRelance.addActionListener(this);
	}

	/**
	 * Permet de mettre à jour les structures et l'esthétique dynamiques de
	 * l'onglet
	 */
	public void majOnglet()
	{
		//nettoyage de l'onglet
		boutons.clear();
		reponses.removeAll();

		//récupération des données nécessaires pour la mise à jour
		paragrapheCourant = editeur.getParagrapheC();

		//mise à jour de l'interface
		texte.setText(paragrapheCourant.getTexte());

		reponses.setLayout(new GridLayout((paragrapheCourant.getNbChoix()>2?2:1), paragrapheCourant.getNbChoix()/2));

		if(paragrapheCourant.getNbChoix()>0)
		{
			for(int i=0; i<paragrapheCourant.getNbChoix(); i++)
			{
				boutons.add(new JButton(paragrapheCourant.getTexteChoix(i)));
				reponses.add(boutons.get(i));
				boutons.get(i).addActionListener(this);
			}
		}
		else
		{
			reponses.add(btnRelance);
		}

		//application de la mise à jour
		updateUI();
	}

	/**
	 * @override
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnRelance)
		{
			//En cas de relecture du livre, on revient au premier paragraphe
			//de ce dernier et on met l'onglet à jour
			ctrl.retourPremierParagraphe();
			majOnglet();
		}
		else
		{
			//Lors du choix d'une réponse
			for(int i=0; i<boutons.size(); i++)
			{
				//On vérifie quelle réponse a été choisie
				if(e.getSource()==boutons.get(i))
				{
					//On passe au paragraphe indiqué par la réponse
					//et on met l'onglet à jour
					ctrl.changerParagrapheCourant(paragrapheCourant.getChoix(i)-1);
					majOnglet();
					break;
				}
			}
		}

	}
}
