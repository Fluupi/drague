package editeur.vue.onglet;

import editeur.Controleur;

import editeur.vue.Fenetre;

import editeur.vue.ecouteur.VueEditeur;
import editeur.vue.ecouteur.VueLivre;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.Border;

/**
 * La classe OngletLivre représente un onglet de la partie vue de l'application
 * editeur.
 * Elle permet toutes les intéractions avec les livres en dehors des modifications
 * liées aux paragraphes
 *
 * @author A remplir
 * @version 2.0
 */
public class OngletLivre extends JPanel implements ActionListener, ListSelectionListener
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
	 * JTextField stockant le titre du livre
	 */
	private JTextField txtTitre;
	/**
	 * JTextField stockant l'auteur du livre
	 */
	private JTextField txtAuteur;

	/**
	 * JButton permettant la sauvegarde dans son fichier du livre ouvert
	 */
	private JButton btnSauv;
	/**
	 * JButton permettant la création et l'ouverture d'un nouveau livre
	 */
	private JButton btnNouv;
	/**
	 * JButton permettant la suppression du livre ouvert
	 */
	private JButton btnSup;

	/**
	 * JList stockant les noms de fichiers des livres présents dans le dossier res
	 */
	private JList listeLivre;

	/*  Constructeur  */
	/**
	 * Constructeur mettant en place la structure et l'esthétique statiques de
	 * l'onglet
	 *
	 * @param ctrl Controleur
	 * @param e    VueEditeur
	 */
	public OngletLivre(Controleur c, VueEditeur e)
	{
		this.ctrl=c;
		this.editeur = e;

		/*********************************************************************/
		/*                       Découpage de l'onglet                       */
		/*********************************************************************/
		setLayout(new GridLayout(1,2));

		GridBagConstraints gc = new GridBagConstraints();//contraint de gridBag
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(5,5,5,5);

		gc.weightx = 0; //poids des element
		gc.weighty = 0;

		gc.gridwidth=1;

		JPanel panGauche = new JPanel(new BorderLayout());
		JPanel panDroit  = new JPanel(new BorderLayout());

		add(panGauche);
		add(panDroit);

		//----------     Détail de panGauche     ----------\\
		JPanel panG = new JPanel(new GridBagLayout());
		panGauche.add(panG, BorderLayout.NORTH);

		//-----      Détail de panG      -----\\
		//ajout du label titre
		JLabel lbTitre    = new JLabel("T I T R E");
		JPanel panLbTitre = new JPanel();
		panLbTitre.add(lbTitre);
		gc.gridx = 0;gc.gridy = 0;
		panG.add(panLbTitre, gc);

		//ajout du txtField titre
		gc.gridy++;
		txtTitre = new JTextField(30);
		panG.add(txtTitre, gc);

		//ajout label auteur
		JLabel lbAuteur    = new JLabel("A U T E U R");
		JPanel panLbAuteur = new JPanel();
		panLbAuteur.add(lbAuteur);
		gc.gridy++;
		panG.add(panLbAuteur, gc);

		//ajout jtextField auteur
		gc.gridy++;
		txtAuteur = new JTextField(30);
		panG.add(txtAuteur, gc);

		//ajout du bouton sauveagrder
		gc.gridy+=2;
		btnSauv = new JButton("Sauvegarder");
		panG.add(btnSauv, gc);

		//----------     Détail de panDroit     ----------\\
		JPanel panD = new JPanel(new GridBagLayout());
		panDroit.add(panD, BorderLayout.NORTH);

		//ajout de label liste des livres
		JLabel lbListeLivre = new JLabel("L I S T E   D E S   L I V R E S");
		JPanel panLbListeLivre = new JPanel();
		panLbListeLivre.add(lbListeLivre);
		gc.gridy = 0;
		panD.add(panLbListeLivre, gc);

		//ajout du combo liste des livres
		gc.gridy++;
		listeLivre = new JList();
		panD.add(listeLivre, gc);

		//panel bouton
		gc.gridy++;
		JPanel panBtn = new JPanel();
		panD.add(panBtn, gc);

		//-----     Détail de panBtn     -----\\
		btnNouv = new JButton("Nouveau");
		btnSup  = new JButton("Supprimer");

		panBtn.add(btnNouv);
		panBtn.add(btnSup);


		/*********************************************************************/
		/*                            Esthétique                             */
		/*********************************************************************/
		Border borderLine = BorderFactory.createLineBorder(Color.GRAY,2);
		setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		//titre
		lbTitre.setFont(Fenetre.FONT_LB);
		txtTitre.setFont(Fenetre.FONT_TXT);

		//auteur
		lbAuteur.setFont(Fenetre.FONT_LB);
		txtAuteur.setFont(Fenetre.FONT_TXT);

		//bouton sauvegarder
		btnSauv.setPreferredSize(new Dimension(250,40));
		btnSauv.setFont(new Font("TimesRoman",Font.BOLD,15));
		btnSauv.setBackground(Fenetre.COLOR_BTN);
		btnSauv.setForeground(Color.WHITE);

		//liste livre
		lbListeLivre.setFont(Fenetre.FONT_LB);
		listeLivre.setFont(Fenetre.FONT_TXT);

		//bouton nouveau
		btnNouv.setPreferredSize(new Dimension(240,40));
		btnNouv.setBackground(Fenetre.COLOR_BTN);
		btnNouv.setForeground(Color.WHITE);

		//bouton supprimer
		btnSup.setPreferredSize(new Dimension(240,40));
		btnSup.setBackground(Fenetre.COLOR_BTN);
		btnSup.setForeground(Color.WHITE);

		//panels
		panGauche.setBorder(borderLine);
		panDroit.setBorder(borderLine);

		/*********************************************************************/
		/*                            Listeners                              */
		/*********************************************************************/
		listeLivre.addListSelectionListener(this);

		btnNouv.addActionListener(this);
		btnSup.addActionListener(this);
		btnSauv.addActionListener(this);
	}

	/**
	 * Permet de mettre à jour les structures et l'esthétique dynamiques de
	 * l'onglet
	 */
	public void majOnglet()
	{
		//récupération des données nécessaires pour la mise à jour
		livre = editeur.getLivre();

		//mise à jour de l'interface
		txtTitre.setText(""+livre.getTitre());
		txtAuteur.setText(""+livre.getAuteur());
		listeLivre.setListData(ctrl.getListeTitreLivre().toArray());

		//application de la mise à jour
		updateUI();
	}

	/**
	 * @override
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSauv)
		{
			//si c'est bouton sauvegarder
			//modification du titre et auteur du livre
			String titre=txtTitre.getText();
			String auteur=txtAuteur.getText();

			//verifier si l'un des champs n'est pas vide
			if(titre.length()==0 || auteur.length()==0)
			{
				System.out.println("Erreur de modif");
			}
			else
			{
				//sinon modifier
				ctrl.modifierTitre(titre);
				ctrl.modifierAuteur(auteur);
				ctrl.exporterLivreC();

				ctrl.majUI();
			}
		}else if(e.getSource()==btnSup)
		{
			//si c'est le bouton supprimer
			//suppression d'un livre dans
			ctrl.supprimerLivre();
			ctrl.majUI();
		}
		else if(e.getSource()==btnNouv)
		{
			//si c'est le bouton nouveau
			//creation d'un nouveau livre
			//ouvertur d'un popup
			System.out.println("nouveau livre");
			new PopupNvLivre(ctrl);
		}
	}

	/**
	 * @override
	 */
	public void valueChanged(ListSelectionEvent evt)
	{
        if (!evt.getValueIsAdjusting() && !ctrl.estEnModif())
		{
			System.out.println(listeLivre.getSelectedIndex());
			ctrl.changerLivreCourant(listeLivre.getSelectedIndex());
			ctrl.majUI();
		}
	}

	/**
	 * La classe PopupNvLivre représente un popup de la partie vue de l'application
	 * editeur.
	 * Elle permet de renseigner les informations nécessaires à la création d'un
	 * livre
	 *
	 * @author A remplir
	 * @version 2.0
	 */
	private class PopupNvLivre extends JDialog implements ActionListener
	{
		private JTextField txtTitre;
		private JTextField txtAuteur;

		private JButton btnValider;
		private JButton btnAnnuler;

		public PopupNvLivre(Controleur ctrl)
		{
			super(ctrl.getFrame(), "Création de livre", true);

			setSize(500,300);
			setLocationRelativeTo(null);
			setResizable(false);

			/*********************************************************************/
			/*                       Découpage de l'onglet                       */
			/*********************************************************************/

			setLayout(new BorderLayout());

			JPanel panH = new JPanel();
			JPanel panB = new JPanel();

			add(panH);
			add(panB, BorderLayout.SOUTH);

			//----------     Détail de panH     ----------\\
			GridBagConstraints gc = new GridBagConstraints();//contraint de grideBag

			gc.weightx = 0; //poids des element
			gc.weighty = 0;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.insets = new Insets(5,5,5,5);

			//ajout du label titre
			gc.gridx = 0;gc.gridy = 0;
			JLabel lbTitre = new JLabel("T I T R E");
			panH.add(lbTitre, gc);

			//ajout du txtField titre
			gc.gridy++;
			txtTitre = new JTextField(25);
			panH.add(txtTitre, gc);

			//ajout label auteur
			gc.gridy++;
			JLabel lbAuteur = new JLabel("A U T E U R");
			panH.add(lbAuteur, gc);

			//ajout jtextField
			gc.gridy++;
			txtAuteur = new JTextField(25);
			panH.add(txtAuteur, gc);

			//----------     Détail de panB     ----------\\
			btnValider = new JButton("V a l i d e r");
			btnAnnuler = new JButton("A n n u l e r");

			panB.add(btnValider);
			panB.add(btnAnnuler);

			/*********************************************************************/
			/*                            Esthétique                             */
			/*********************************************************************/

			lbTitre.setFont(Fenetre.FONT_LB);
			txtTitre.setFont(Fenetre.FONT_TXT);

			lbAuteur.setFont(Fenetre.FONT_LB);
			txtAuteur.setFont(Fenetre.FONT_TXT);

			btnValider.setPreferredSize(Fenetre.BTN_DIM);
			btnValider.setFont(new Font("TimesRoman",Font.BOLD,15));
			btnValider.setBackground(Fenetre.COLOR_BTN);
			btnValider.setForeground(Color.WHITE);

			btnAnnuler.setPreferredSize(Fenetre.BTN_DIM);
			btnAnnuler.setFont(new Font("TimesRoman",Font.BOLD,15));
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
				//instruction d'ajout d'un nouveau livre
				if(txtTitre.getText().length()!=0 && txtAuteur.getText().length()!=0)
				{
					ctrl.creerLivre(txtTitre.getText(), txtAuteur.getText());
					ctrl.majUI();
					this.dispose();
				}
			}
			else
				this.dispose();
		}
	}
}
