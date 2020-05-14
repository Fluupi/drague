package editeur.vue;

import editeur.Controleur;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LigneBtn extends JPanel
{
    private Controleur c;

    private char   type;
    private String texte;
    private int    numero;

    String[] lstReponse;
    int[]    lstChoix;

    private JButton btnText = new JButton();
    private JList defil;

    public LigneBtn(Controleur c, char type)
    {
        this.c = c;
        this.type = type;

        int nbChoix = c.getParagrapheCourant().getNbChoix();
        setLayout(new BorderLayout());
        add(new JLabel("Listing"), "North");

        lstReponse = new String[nbChoix];
        lstChoix   = new int[nbChoix];

        for(int i=0; i<nbChoix; i++)
        {
            lstReponse[i]=c.getParagrapheCourant().getTexteChoix(i);
            lstChoix[i]=c.getParagrapheCourant().getChoix(i);
        }

        defil = new JList(lstReponse);//liste deroulante
		add(defil);
        //add(new OptionBox(), "South");
/*
        setLayout(new GridLayout(1,2));

        btnText.setText(texte);

        add(btnText);
        add(btnMod);
        add(btnSup);

        btnText.addActionListener(this);
        btnMod.addActionListener(this);
        btnSup.addActionListener(this);*/
    }


}
