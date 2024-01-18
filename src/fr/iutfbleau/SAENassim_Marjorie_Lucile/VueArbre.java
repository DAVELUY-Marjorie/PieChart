package fr.iutfbleau.SAENassim_Marjorie_Lucile;
import javax.swing.*;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.event.*;
import java.sql.SQLException;

public class VueArbre extends JFrame {
	private int idres;
	private JButton bouton = null;

  /**
  * La méthode publique VueArbre est un constructeur de VueArbre.
  * Elle permet la construction d'une nouvelle VueArbre.
  * Représente l'arbre et la ScrollPane.
  *
  * @param description La consigne à réaliser.
  * @param tree Un Arbre .
  * @param res L'int associé à l'idres de la vueArbre.
  */
	public VueArbre(String description, Arbre tree, int res) {
		super();
		this.idres = res;
		this.setSize(300, 300);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());

		tree.setArbreContainer(this);

		JTextArea consigne = new JTextArea(description);
		consigne.setLineWrap(true);
		consigne.setWrapStyleWord(true);

		JScrollPane jsp = new JScrollPane(tree);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		GridBagConstraints gbc = new GridBagConstraints();

		// JTextArea
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0f;
		gbc.weighty = 0f;
		gbc.insets = new Insets(5,5,5,5);
		this.add(consigne, gbc);

		// JTree
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 8;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.1f;
		gbc.weighty = 0.1f;
		gbc.insets = new Insets(5,5,5,5);
		this.add(jsp, gbc);

  }

  /**
  * La méthode publique addBouton ajoute un bouton
  *
  *
  * @param m Le menu auquel on ajoute un bouton listener
  */
	public void addBouton(Menu m){
		this.bouton = new JButton("Valider la sélection");
		this.bouton.addActionListener(new BoutonListener(m, this.idres, this));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weightx = 0f;
		gbc.weighty = 0f;
		gbc.insets = new Insets(5,5,5,5);
		this.add(bouton, gbc);

		this.revalidate();
	}

	public void removeBouton(){
		if(this.bouton != null){
			this.remove(this.bouton);
			this.bouton = null;
			this.revalidate();
		}
	}
}