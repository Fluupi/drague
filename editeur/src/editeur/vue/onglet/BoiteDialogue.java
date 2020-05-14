package editeur.vue.onglet;

import editeur.Controleur;
import editeur.metier.Paragraphe;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
public class BoiteDialogue extends JDialog implements ActionListener {
	private JButton btnValider = new JButton("Valider");
	private JButton btnAnnuler = new JButton("Annuler");
	private JTextField txtReponse,txtIndiceParagraphe;
	private  DefaultListModel model;
	private JList liste;
	private Controleur c;
	private int indice;
	public BoiteDialogue(JFrame parent,String titre,Controleur c,int indice) {
		super(parent,titre,true);
		this.c=c;
		this.indice=indice;
		this.setBounds(0,0,1300,350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Border lineBorder = BorderFactory.createLineBorder(Color.WHITE,4);
		Insets margin = new Insets(0, 8, 0, 0);
		this.getContentPane().setLayout(new GridLayout(1, 2));
		//font des champs de saisi
		Font fonttxt =new Font("TimesRoman",Font.PLAIN,18);
		Font fontLb = new Font("TimesRoman",Font.BOLD,15);

		//partie gauche
		JPanel panG = new JPanel();
		panG.setBorder(lineBorder);
		panG.setLayout(new BorderLayout());

		JPanel panReponse = new JPanel();
		panReponse.setLayout(new BoxLayout(panReponse, BoxLayout.Y_AXIS ));
		JLabel lbReponse =new JLabel("R E P O N S E  ["+this.indice+"]");
		lbReponse.setFont(fontLb);
		panReponse.add(lbReponse);
		panReponse.add(Box.createVerticalStrut(5));
		this.txtReponse = new JTextField(80);
		txtReponse.setFont(fonttxt);
		txtReponse.setMaximumSize(txtReponse.getPreferredSize());
		panReponse.add(txtReponse);
		txtReponse.setMargin(margin);
		panReponse.add(txtReponse);

		JLabel lbLiaison =new JLabel("NUMERO DU PARAGRAPHE");
		txtIndiceParagraphe = new JTextField(29);
		txtIndiceParagraphe.setMargin(margin);
		txtIndiceParagraphe.setFont(fonttxt);
		txtIndiceParagraphe.setMaximumSize(txtIndiceParagraphe.getPreferredSize());
		lbLiaison.setFont(new Font("TimesRoman",Font.PLAIN,15));
		panReponse.add(Box.createVerticalStrut(10));
		panReponse.add(lbLiaison);
		panReponse.add(txtIndiceParagraphe);
		JPanel panBtn = new JPanel();
		Dimension dimBtn = new Dimension(180,30);
		this.btnValider.setPreferredSize(dimBtn);
		this.btnAnnuler.setPreferredSize(dimBtn);
		this.btnAnnuler.addActionListener(this);
		panBtn.add(this.btnValider);
		panBtn.add(this.btnAnnuler);
		panG.add(panReponse,BorderLayout.NORTH);
		panG.add(panBtn,BorderLayout.SOUTH);

		//partie droite de la boite de dialogue
		JPanel panD = new JPanel();
		panD.setBorder(lineBorder);
		JPanel panListe = new JPanel();
		panListe.setLayout(new BoxLayout(panListe, BoxLayout.Y_AXIS ));
		JLabel lbListe =new JLabel("P A R A G R A P H E S");
		lbListe.setFont(fontLb);
		panListe.add(lbListe);
		this.model = new DefaultListModel();
		this.liste =new JList(model);
		JScrollPane scrolPan = new JScrollPane(liste);
		scrolPan.setPreferredSize(new Dimension(600,260));
		panListe.add(scrolPan);
		panD.add(panListe);

		//ajout du panel gauche et doit dans le panel principal du popup
		this.getContentPane().add(panG);
		this.getContentPane().add(panD);
		majListeParagraphe();
		majTxtReponse();
		this.setVisible(true);
	}
	public JButton getBtnValider() {
		return btnValider;
	}
	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}
	/**
	* Mise à jour de la liste des paragraphes
	*/
	public void majListeParagraphe(){

		int i=1;
		for(Paragraphe p :c.getLivreCourant().getListeParagraphe()){

			this.model.addElement("[ "+ i +" ]  "+p.getTexte());
			//System.out.println(p.getTexte().substring(0, 15)+"...");
			i++;
		}
	}
	/**
	* Mise à jour des champs de saisi
	*/
	public void majTxtReponse(){
		if(this.indice>=0){
			this.txtReponse.setText(c.getParagrapheCourant().getTexteChoix(this.indice));
		}
	}
	/**
	* Méthode de controle des boutons de la boite de dialogue
	*/
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.btnValider){
			//ajouter si l'indice est -1
			//modifier si l'indice est different de -1
		}else{
			//annuler
				this.dispose();
		}
	}

}
