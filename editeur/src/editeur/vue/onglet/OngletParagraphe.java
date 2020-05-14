package editeur.vue.onglet;

import editeur.Controleur;

import editeur.vue.Fenetre;
import editeur.vue.ecouteur.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import javax.swing.border.Border;

/**
 * La classe OngletParagraphe représente un onglet de la partie vue de l'application
 * editeur.
 * Elle permet de
 *
 * @author A remplir
 * @version 2.0
 */
public class OngletParagraphe extends JPanel implements ActionListener, ListSelectionListener
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
	 * Vue du livre ouvert
	 */
	private VueLivre livre;

	/**
	 * Vue du Paragraphe en lecture
	 */
	private VueParagraphe p;

	//JPanels constituant l'onglet
	private JPanel panHD;
	private JPanel panGauche;
	private JPanel panHG;
	private JPanel panBG;

	//JButton dédiés à la modération du paragraphe courant et des réponses
	private JButton btnSauvegarder;
	private JButton btnNouveauParagraphe;
	private JButton btnSupprimerParagraphe;
	private JButton btnAjouterReponse;
	private JButton btnModifierReponse;
	private JButton btnSupprimerReponse;

	/**
	 * Zone du texte du paragraphe courant
	 */
	private JTextArea taTxt;

	/**
	 * JLabel d'annonce du texte du paragraphe courant et indicateur d'index
	 * courant
	 */
	private JLabel lbParagrapheCourant;

	/**
	 * Listes des paragraphes et des réponses du paragraphe courant
	 */
	private JList listeReponses;
	private JList listeParagraphes;


	/*  Constructeur  */
	/**
	 * Constructeur mettant en place la structure et l'esthétique statiques de
	 * l'onglet
	 *
	 * @param ctrl Controleur
	 * @param e    VueEditeur
	 */
	public OngletParagraphe(Controleur c, VueEditeur e)
	{
		this.ctrl = c;
		this.editeur = e;
		this.livre = editeur.getLivre();

		/*********************************************************************/
		/*                       Découpage de l'onglet                       */
		/*********************************************************************/

		setLayout(new GridLayout(1,2));

		panGauche = new JPanel(new GridLayout(2,1));
		panHD     = new JPanel(new BorderLayout());

		add(panGauche);
		add(panHD);

		//----------      Détail de panGauche    ----------\\
		panHG = new JPanel(new BorderLayout());
		panBG = new JPanel(new BorderLayout());

		panGauche.add(panHG);
		panGauche.add(panBG);

		//-----     Détail de panHG     -----\\
		JPanel panParagC  = new JPanel();
		taTxt             = new JTextArea(20,30);
		JPanel panBtnParag = new JPanel();

		panHG.add(panParagC  , BorderLayout.NORTH);
		panHG.add(taTxt);
		panHG.add(panBtnParag, BorderLayout.SOUTH);

		//Détail panParagC
		lbParagrapheCourant = new JLabel();
		panParagC.add(lbParagrapheCourant);

		//Détail panBtnParag
		btnSauvegarder         = new JButton("Sauvegarder");
		btnNouveauParagraphe   = new JButton("Nouveau");
		btnSupprimerParagraphe = new JButton("Supprimer");

		panBtnParag.add(btnSauvegarder);
		panBtnParag.add(btnNouveauParagraphe);
		panBtnParag.add(btnSupprimerParagraphe);

		//-----     Détail de panBG     -----\\
		JPanel panBtnReponse = new JPanel();
		listeReponses        = new JList();
		JPanel panLbListeRep = new JPanel();

		panBG.add(panLbListeRep, BorderLayout.NORTH);
		panBG.add(listeReponses);
		panBG.add(panBtnReponse, BorderLayout.SOUTH);

		//Détail panLbListeRep
		JLabel lbListeReponse = new JLabel("R é p o n s e s");
		panLbListeRep.add(lbListeReponse);

		//Détail panBtnReponse
		btnAjouterReponse   = new JButton("Ajouter");
		btnModifierReponse  = new JButton("Modifier");
		btnSupprimerReponse = new JButton("Supprimer");

		panBtnReponse.add(btnAjouterReponse);
		panBtnReponse.add(btnModifierReponse);
		panBtnReponse.add(btnSupprimerReponse);

		//----------      Détail de panHD        ----------\\
		JPanel panLbListeparag = new JPanel();
		listeParagraphes       = new JList();

		panHD.add(panLbListeparag, BorderLayout.NORTH);
		panHD.add(listeParagraphes);

		JLabel lbListeparag = new JLabel("P a r a g r a p h e s");
		panLbListeparag.add(lbListeparag);

		/*********************************************************************/
		/*                            Esthétique                             */
		/*********************************************************************/

		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		//label paragraphe courant
		panParagC.setBackground(Fenetre.BACKGROUND_COLOR);
		lbParagrapheCourant.setForeground(Color.WHITE);
		lbParagrapheCourant.setFont(Fenetre.FONT_LB);

		//zone de saisi du texte du paragraphe
		taTxt.setFont(Fenetre.FONT_TXT);

		//On souhaite un retour à ligne automatique
		//en cas de dépassement de largeur de texte
		//et on veut éviter que les mots soient coupés
		taTxt.setLineWrap(true);
		taTxt.setWrapStyleWord(true);

		//liste des bouton du paragraphe courant
		btnSauvegarder.setPreferredSize(Fenetre.BTN_DIM);
		btnSauvegarder.setBackground(Fenetre.COLOR_BTN);
		btnSauvegarder.setForeground(Color.WHITE);

		btnNouveauParagraphe.setPreferredSize(Fenetre.BTN_DIM);
		btnNouveauParagraphe.setBackground(Fenetre.COLOR_BTN);
		btnNouveauParagraphe.setForeground(Color.WHITE);

		btnSupprimerParagraphe.setPreferredSize(Fenetre.BTN_DIM);
		btnSupprimerParagraphe.setBackground(Fenetre.COLOR_BTN);
		btnSupprimerParagraphe.setForeground(Color.WHITE);

		//label liste des reponse
		panLbListeRep.setBackground(Fenetre.BACKGROUND_COLOR);
		lbListeReponse.setForeground(Color.WHITE);
		lbListeReponse.setFont(Fenetre.FONT_LB);

		//Label liste paragraphe
		panLbListeparag.setBackground(Fenetre.BACKGROUND_COLOR);
		lbListeparag.setForeground(Color.WHITE);
		lbListeparag.setFont(Fenetre.FONT_LB);

		//les boutons des reponses
		btnAjouterReponse.setPreferredSize(Fenetre.BTN_DIM);
		btnAjouterReponse.setBackground(Fenetre.COLOR_BTN);
		btnAjouterReponse.setForeground(Color.WHITE);

		btnModifierReponse.setPreferredSize(Fenetre.BTN_DIM);
		btnModifierReponse.setBackground(Fenetre.COLOR_BTN);
		btnModifierReponse.setForeground(Color.WHITE);

		btnSupprimerReponse.setPreferredSize(Fenetre.BTN_DIM);
		btnSupprimerReponse.setBackground(Fenetre.COLOR_BTN);
		btnSupprimerReponse.setForeground(Color.WHITE);

		//bordures panels
		Border borderLine = BorderFactory.createLineBorder(Color.GRAY,2);

		panHG.setBorder(borderLine);
		panBG.setBorder(borderLine);
		panHD.setBorder(borderLine);

		/*********************************************************************/
		/*                            Listeners                              */
		/*********************************************************************/
		listeParagraphes.addListSelectionListener(this);

		btnAjouterReponse.addActionListener(this);
		btnModifierReponse.addActionListener(this);
		btnSupprimerReponse.addActionListener(this);
		btnNouveauParagraphe.addActionListener(this);
		btnSauvegarder.addActionListener(this);
		btnSupprimerParagraphe.addActionListener(this);
	}

	/**
	*	méthode de l'interface ActionListener pour gérer les clicks
	*/
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.btnAjouterReponse)
		{
			if(p.getNbChoix() >= 4)
				JOptionPane.showMessageDialog(null, "Un paragraphe peut avoir maximum 4 réponses");
			else
				new PopupReponse(ctrl,true);
		}
		else if(e.getSource()==this.btnModifierReponse)
		{
			if(listeReponses.getSelectedIndex()==-1)
				JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné de réponse");
			else
				new PopupReponse(ctrl,false);
		}
		else if(e.getSource()==this.btnSupprimerReponse)
		{
			ctrl.supprimerLien(livre.indexOf(p), p.getChoix(listeReponses.getSelectedIndex()));
		}
		else if(e.getSource()==this.btnNouveauParagraphe)
		{
			new PopupNvParagraphe(ctrl);
		}
		else if(e.getSource()==this.btnSauvegarder)
		{
			ctrl.setTexteCourant(taTxt.getText());
			ctrl.exporterLivreC();
		}
		else if(e.getSource()==this.btnSupprimerParagraphe)
		{
			ctrl.supprimerParagrapheC();
			ctrl.retourPremierParagraphe();
		}

		ctrl.majUI();
	}

	/**
	* @override
	*/
	public void valueChanged(ListSelectionEvent evt)
	{
        if (!evt.getValueIsAdjusting() && !ctrl.estEnModif())
		{
			ctrl.changerParagrapheCourant(listeParagraphes.getSelectedIndex());
			ctrl.majUI();
		}
	}

	/**
	 * Permet de mettre à jour les structures et l'esthétique dynamiques de
	 * l'onglet
	 */
	public void majOnglet()
	{
		//Récupération des données nécessaires pour la mise à jour
		p = editeur.getParagrapheC();
		livre = editeur.getLivre();

		String numPara = "P a r a g r a p h e   c o u r a n t - ";

		if(listeParagraphes.getSelectedIndex()==-1)
			numPara += "A u c u n";
		else
			numPara += listeParagraphes.getSelectedIndex();

		//réponses
		int nbChoix = p.getNbChoix();
		String[] lstReponse = new String[nbChoix];

		for(int i=0; i<nbChoix; i++)
			lstReponse[i]=p.getTexteChoix(i) + " => (" + (p.getChoix(i)-1) + ")";


		//paragraphes
		int nbPara = livre.getNbParagraphes();
		String[] lstPara = new String[nbPara];

		for(int i=0; i<nbPara; i++)
			lstPara[i]="(" + i + ") " + livre.get(i).getTexte();

		//Mise à jour de l'interface
		taTxt.setText(p.getTexte());

		lbParagrapheCourant.setText(numPara);

		listeReponses.setListData(lstReponse);
		listeParagraphes.setListData(lstPara);

		//Application de la mise à jour
		updateUI();
	}

	/**
	 * La classe PopupNvParagraphe représente un popup de la partie vue de l'application
	 * editeur.
	 * Elle permet de renseigner les informations nécessaires à la création d'un
	 * paragraphe
	 *
	 * @author A remplir
	 * @version 2.0
	 */
	private class PopupNvParagraphe extends JDialog implements ActionListener
	{
		/**
		 * Zone du texte du paragraphe courant
		 */
		private JTextArea taParagraphe;

		//JButton de gestion d'issue du popup
		private JButton btnValider;
		private JButton btnAnnuler;

		public PopupNvParagraphe(Controleur c)
		{
			super(c.getFrame(),"Création de paragraphe",true);

			setSize(750,400);
			setLocationRelativeTo(null);
			setResizable(false);

			/*********************************************************************/
			/*                       Découpage de l'onglet                       */
			/*********************************************************************/

			setLayout(new BorderLayout());

			JPanel panH = new JPanel(new BorderLayout());
			JPanel panB = new JPanel();

			add(panH);
			add(panB, BorderLayout.SOUTH);

			//----------      Détail de panH         ----------\\
			JPanel panLbParagraphe = new JPanel();
			taParagraphe           = new JTextArea(5,5);

			panH.add(panLbParagraphe,BorderLayout.NORTH);
			panH.add(taParagraphe);

			//-----  Détail de panLbParagraphe  -----\\
			JLabel lbParagraphe = new JLabel("P a r a g r a p h e");
			panLbParagraphe.add(lbParagraphe);

			//----------      Détail de panB         ----------\\
			btnValider = new JButton("Valider");
			btnAnnuler = new JButton("Annuler");

			panB.add(btnValider);
			panB.add(btnAnnuler);

			/*********************************************************************/
			/*                            Esthétique                             */
			/*********************************************************************/

			Border borderLine = BorderFactory.createLineBorder(Color.WHITE,2);

			//label paragraphe
			panLbParagraphe.setBackground(new Color(60,99,68));
			lbParagraphe.setForeground(Color.WHITE);
			lbParagraphe.setFont(Fenetre.FONT_LB);

			//JtxtArea du paragraphe
			taParagraphe.setBorder(borderLine);
			taParagraphe.setFont(Fenetre.FONT_TXT);
			taParagraphe.setPreferredSize(new Dimension(1000,200));
			taParagraphe.setLineWrap(true); /** On souhaite un retour à ligne automatique : */
			taParagraphe.setWrapStyleWord(true); /** On souhaite que les mots ne soient pas coupés : */

			//liste des boutons
			btnValider.setPreferredSize(Fenetre.BTN_DIM);
			btnValider.setBackground(Fenetre.COLOR_BTN);
			btnValider.setForeground(Color.WHITE);

			btnAnnuler.setPreferredSize(Fenetre.BTN_DIM);
			btnAnnuler.setBackground(Fenetre.COLOR_BTN);
			btnAnnuler.setForeground(Color.WHITE);

			/*********************************************************************/
			/*                            Listeners                              */
			/*********************************************************************/

			btnValider.addActionListener(this);
			btnAnnuler.addActionListener(this);

			setVisible(true);
		}

		/**
		 * @override
		 */
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==this.btnValider)
			{
				//instruction d'ajout du nouveau paragraphe
				if(taParagraphe.getText().length()!=0)
				{
					ctrl.creerParagraphe(taParagraphe.getText());
					majOnglet();
					this.dispose();//fermeture du popup
				}
			}
			else
				this.dispose();
		}
	}

	private class PopupReponse extends JDialog implements ActionListener, ListSelectionListener
	{
		private boolean estCreation;

		private JTextArea taTexteReponse,taParagrapheDepart,taParagrapheDestination;
		private JButton btnValider,btnAnnuler;

		private JList lstParagraphe = new JList();

		public PopupReponse(Controleur c, boolean estCreation)
		{
			super(c.getFrame(), (estCreation?"Création":"Modification") + " de Réponse", true);

			this.estCreation = estCreation;

			setSize(1200,600);
			setLocationRelativeTo(null);

			/*********************************************************************/
			/*                       Découpage de l'onglet                       */
			/*********************************************************************/

			setLayout(new BorderLayout());

			JPanel panHaut = new JPanel(new GridLayout(1,2));
			JPanel panBas  = new JPanel(new GridLayout(1,2));
			JPanel panB  = new JPanel();

			add(panHaut, BorderLayout.NORTH);
			add(panBas);
			add(panB   , BorderLayout.SOUTH);

			//----------      Détail de panHaut      ----------\\
			JPanel panHG = new JPanel(new BorderLayout());
			JPanel panHD = new JPanel(new BorderLayout());

			panHaut.add(panHG);
			panHaut.add(panHD);

			//-----  Détail de panHG  -----\\
			JPanel panLbRep = new JPanel(new FlowLayout(FlowLayout.LEFT));
			taTexteReponse = new JTextArea(5,5);

			panHG.add(panLbRep, BorderLayout.NORTH);
			panHG.add(taTexteReponse);

			//Détail de panLbRep
			JLabel lbReponse = new JLabel("R é p o n s e");
			panLbRep.add(lbReponse);

			//-----  Détail de panHD  -----\\
			JPanel      panLbParag   = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JScrollPane scrlPanParag = new JScrollPane(lstParagraphe);

			panHD.add(panLbParag, BorderLayout.NORTH);
			panHD.add(scrlPanParag);

			//Détail de panLbParag
			JLabel lbParagraphe = new JLabel("P a r a g r a p h e s ");
			panLbParag.add(lbParagraphe);

			//----------      Détail de panBas       ----------\\
			JPanel panHB = new JPanel(new GridLayout(1,2));
			panBas.add(panHB);

			//-----  Détail de panHB  -----\\
			JPanel panParagrapheDepart      = new JPanel(new BorderLayout());
			JPanel panParagrapheDestination = new JPanel(new BorderLayout());

			panHB.add(panParagrapheDepart);
			panHB.add(panParagrapheDestination);

			//Détail de panParagrapheDepart
			JLabel lbParagrapheDepart = new JLabel("Paragraphe de départ");
			taParagrapheDepart   = new JTextArea(5,5);

			panParagrapheDepart.add(lbParagrapheDepart,BorderLayout.NORTH);
			panParagrapheDepart.add(taParagrapheDepart);

			//Détail de panParagrapheDestination
			JLabel lbParagrapheDestination = new JLabel("Paragraphe destination");
			taParagrapheDestination            = new JTextArea(5,5);

			panParagrapheDestination.add(lbParagrapheDestination,BorderLayout.NORTH);
			panParagrapheDestination.add(taParagrapheDestination);

			//----------       Détail de panB        ----------\\
			btnValider = new JButton("V a l i d e r");
			btnAnnuler = new JButton("A n n u l e r");

			panB.add(btnValider);
			panB.add(btnAnnuler);

			/*********************************************************************/
			/*                            Esthétique                             */
			/*********************************************************************/

			//label réponse
			lbReponse.setFont(Fenetre.FONT_LB);
			panLbRep.setBackground(new Color(60,99,68));
			lbReponse.setForeground(Color.WHITE);

			//champ de saisie de la reponse
			taTexteReponse.setFont(Fenetre.FONT_TXT);
			taTexteReponse.setPreferredSize(new Dimension(1000,200));
			taTexteReponse.setLineWrap(true); /** On souhaite un retour à ligne automatique : */
			taTexteReponse.setWrapStyleWord(true); /** On souhaite que les mots ne soient pas coupés : */

			//label liste de paragrphes
			lbParagraphe.setFont(Fenetre.FONT_LB);
			panLbParag.setBackground(new Color(60,99,68));
			lbParagraphe.setForeground(Color.WHITE);

			//liste des paragraphes
			int nbPara = livre.getNbParagraphes();
			String[] lstPara = new String[nbPara];

			for(int i=0; i<nbPara; i++)
	        	lstPara[i]="(" + i + ") " + livre.get(i).getTexte();

			lstParagraphe.setListData(lstPara);
			lstParagraphe.setFont(Fenetre.FONT_TXT);

			// label paragraphe source
			lbParagrapheDepart.setFont(Fenetre.FONT_LB);

			//txteArea paragraphe de départ
			taParagrapheDepart.setFont(Fenetre.FONT_TXT);
			taParagrapheDepart.setPreferredSize(new Dimension(1000,200));

			taParagrapheDepart.setLineWrap(true); /** On souhaite un retour à ligne automatique : */
			taParagrapheDepart.setWrapStyleWord(true); /** On souhaite que les mots ne soient pas coupés : */
			taParagrapheDepart.setEditable(false);

			taParagrapheDepart.setText(p.getTexte());

			//label paragraphe d'arrivée
			lbParagrapheDestination.setFont(Fenetre.FONT_LB);

			//txtArea paragraphe de destination
			taParagrapheDestination.setFont(Fenetre.FONT_TXT);
			taParagrapheDestination.setPreferredSize(new Dimension(1000,200));

			taParagrapheDestination.setLineWrap(true); /** On souhaite un retour à ligne automatique : */
			taParagrapheDestination.setWrapStyleWord(true); /** On souhaite que les mots ne soient pas coupés : */
			taParagrapheDestination.setEditable(false);

			//liste des boutons
			btnValider.setPreferredSize(Fenetre.BTN_DIM);
			btnValider.setBackground(Fenetre.COLOR_BTN);
			btnValider.setForeground(Color.WHITE);

			btnAnnuler.setPreferredSize(Fenetre.BTN_DIM);
			btnAnnuler.setBackground(Fenetre.COLOR_BTN);
			btnAnnuler.setForeground(Color.WHITE);

			//bordures
			Border borderLine = BorderFactory.createLineBorder(Color.GRAY,2);

			taParagrapheDepart.setBorder(borderLine);
			taParagrapheDestination.setBorder(borderLine);
			panHaut.setBorder(borderLine);
			panHG.setBorder(borderLine);
			panHD.setBorder(borderLine);
			panHB.setBorder(borderLine);

			/*********************************************************************/
			/*                            Listeners                              */
			/*********************************************************************/

			this.btnValider.addActionListener(this);
			this.btnAnnuler.addActionListener(this);
			this.lstParagraphe.addListSelectionListener(this);

			/*********************************************************************/
			/*                             Valeurs                               */
			/*********************************************************************/

			if(!estCreation)
			{
				lstParagraphe.setSelectedIndex(p.getChoix(listeReponses.getSelectedIndex()));
				taTexteReponse.setText(p.getTexteChoix(listeReponses.getSelectedIndex()));
				taParagrapheDestination.setText(livre.get(lstParagraphe.getSelectedIndex()).getTexte());
			}

			setVisible(true);
		}

		/**
		 * @override
		 */
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==this.btnValider)
			{
				//instruction de validation
				if(lstParagraphe.getSelectedIndex()!=-1 && taTexteReponse.getText().length()!=0)
				{
					if(estCreation)
						ctrl.lierParagraphes(livre.indexOf(p), lstParagraphe.getSelectedIndex(), taTexteReponse.getText());
					else
						ctrl.setLienParagraphes(livre.indexOf(p), listeReponses.getSelectedIndex(), lstParagraphe.getSelectedIndex(), taTexteReponse.getText());

					this.dispose();
				}

				majOnglet();
			}
			else
				this.dispose();
		}

		/**
		* @override
		*/
		public void valueChanged(ListSelectionEvent e)
		{
			if(!e.getValueIsAdjusting())
				taParagrapheDestination.setText(livre.get(lstParagraphe.getSelectedIndex()).getTexte());
		}
	}
}
