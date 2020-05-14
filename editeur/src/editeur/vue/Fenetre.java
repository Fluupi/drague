package editeur.vue;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import editeur.Controleur;
import editeur.vue.onglet.*;
import editeur.vue.ecouteur.VueEditeur;

/**
 * La classe Fenetre est le support de la partie vue du programme. Elle permet
 * à l'utilisateur d'accéder à toutes les informations nécessaires à la lecture
 * et à l'écriture, par liaison avec le Controleur, de livres ldveh en
 * le fournissant aux différents onglets.
 *
 * @author A remplir
 * @version 2.0
 */
public class Fenetre extends JFrame implements KeyListener
{
	//Polices d'écriture les plus utilisées dans la partie vue
	public static Font      FONT_LB  = new Font("TimesRoman",Font.BOLD,25);
	public static Font      FONT_TXT = new Font("TimesRoman",Font.PLAIN,21);

	//Couleurs les plus utilisées dans la partie vue
	public static Color BACKGROUND_COLOR = new Color(60,99,68);
	public static Color COLOR_BTN        = new Color(30,80,60);

	//Dimension de la majeure partie des boutons
	public static Dimension BTN_DIM = new Dimension(200,40);

	/**
	 * Controleur, lien avec la partie métier
	 */
	private Controleur ctrl;
	/**
	 * Vue de l'éditeur de la partie métier pour l'accès aux informations requises
	 */
	private VueEditeur editeur;

	//Onglets de la partie vue
	private JTabbedPane tabs = new JTabbedPane();

	private OngletLecture    lecture;
	private OngletLivre      livre;
	private OngletParagraphe paragraphe;

	/*  Constructeur  */
	/**
	 * Constructeur permettant d'instancier la fenêtre et les onglets
	 *
	 * @param ctrl Controleur passé pour les modifications de la partie métier
	 * @param e    Vue de la partie métier pour l'accès facilité aux informations
	 */
	public Fenetre(Controleur ctrl, VueEditeur e)
	{
		this.ctrl    = ctrl;
		this.editeur = e;

		//instanciation des onglets
		lecture    = new OngletLecture(ctrl, editeur);
		paragraphe = new OngletParagraphe(ctrl, editeur);
		livre      = new OngletLivre(ctrl, editeur);

		//creation de la Fenetre
		this.setTitle("Editeur de LDVEH");
		this.setLocation(30,30);
		this.setSize(1500, 900);
		this.setMinimumSize(new Dimension(1500, 900));

		//préparation des onglets
		tabs.addTab("Livre",livre);
		tabs.addTab("Paragraphe",paragraphe);
		tabs.addTab("Lecture",lecture);
		tabs.setUI(new AspectOnglets());

		//ajout des onglets à la fenetre et de l'écoute du clavier
		add(tabs);
		addKeyListener(this);

		// Gestion de la fermeture de l'application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//mise à jour de l'interface et affichage de la fenêtre
		majUI();
		setVisible(true);
	}

    /**
     * @override
     */
    public void keyPressed(KeyEvent evt)
    {
		//récupère l'évènement ctrl+s pour lancer une sauvegarde du livre ouvert
		if(evt.getKeyCode()==KeyEvent.VK_S && evt.isControlDown())
            ctrl.exporterLivreC();
    }

    /**
     * @override
     */
    public void keyReleased(KeyEvent evt) {}

    /**
     * @override
     */
    public void keyTyped(KeyEvent evt) {}

	/**
	 * Mise à jour de l'interface tout entière
	 */
	public void majUI()
	{
		setTitle("Editeur de LDVEH - " + editeur.getTitreL());

		lecture.majOnglet();
		livre.majOnglet();
		paragraphe.majOnglet();

		requestFocus();
	}

	/**
	 * La classe AspectOnglets définit l'aspect des onglets
	 *
	 * @author A remplir
	 * @version 1.0
	 */
	 private class AspectOnglets extends javax.swing.plaf.basic.BasicTabbedPaneUI
	{
												//classique inactif , actif
		private final Color[] COULEURS_ONGLETS = {Color.LIGHT_GRAY  , Color.CYAN
												, Color.RED};
		                                        //chgm important
		/*  Constructeur  */
		/**
		 * Constructeur permettant d'instancier l'aspect
		 */
		public AspectOnglets()
		{
			super();
		}

		/**
		 * Précision sur l'aspect des onglets
		 */
		protected void paintTabBackground(Graphics g, int tabPlacement,
		int tabIndex, int x, int y, int w, int h, boolean isSelected)
		{
			//Pour les onglets de manière générale
	        g.setColor(COULEURS_ONGLETS[0]);
	        g.fillRect(x, y, w, h);

			//condition à changer
			/*if(tabIndex%2==0)
			{
				g.setColor(COULEURS_ONGLETS[2]);
        		g.fillRect(x, y, w, h);
			}*/

			//Pour l'onglet actif
        	if(isSelected)
			{
          		g.setColor(COULEURS_ONGLETS[1]);
          		g.fillRect(x, y, w, h);
        	}
      	}
	}
}
