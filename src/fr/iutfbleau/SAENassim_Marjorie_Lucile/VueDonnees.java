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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.*;
import java.util.*;

public class VueDonnees extends JFrame{
	// Panel de début (avec la flèche à droite)
	public static final int FIRST_PANEL = 0;

	// Panel de fin (avec la flèche à gauche)
	public static final int LAST_PANEL = 1;

	// les deux titres possibles de la fenêtre
	private String[] titres = new String[2];

	// le titre actuel de la fenêtre
	private int panel = 0;

  /**
  * La méthode publique VueDonnees est un constructeur de la classe VueDonnees.
  * Elle permet la construction d'une nouvelle VueDonnees.
  * Elle s'occupe de gérer la JFrame où sont présentes les données.
  *
  */
  	public VueDonnees() {
		// Gesion de la JFrame
		super();
		this.setLayout(new CardLayout());
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	}





  /**
   * La méthode addDiagramme pour ajouter un menu qu'on a visité à la bdd.
   * 
   * @param pourcents Une hashmap contenant les différents pourcentages.
   * @param ordre  Détermine l'agencement des composants selon le 
   * diagramme que l'on regarde. Détermine le sens de la flèche
   * aussi.
   *'
   * @param titre Identifie l'onglet et éventuellement définir le titre de la fenêtre principale associée
   *
   * @throws IllegalStateException
   */
	public void addDiagramme(AbstractMap<?,Integer> pourcents, int ordre, String titre, boolean firstPartValidAnswer) throws IllegalStateException, NullPointerException{

		JLabel title = new JLabel("<html>" + titre + "</html>");
		
		JPanel onglet = new JPanel(new GridBagLayout());

		Camembert cheese = new Camembert(pourcents, firstPartValidAnswer);
		
		Legende l = cheese.getLegende();

		JPanel arrow = new JPanel();
		arrow.setLayout(new GridLayout(2,1,0,5));
		arrow.add(new Fleche(ordre));
		arrow.add(new JLabel("Changer"));
		arrow.addMouseListener(new FlecheListener(this));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0f;
		gbc.weighty = 0f;
		gbc.insets = new Insets(10,5,10,5);
		onglet.add(title, gbc);

		gbc.gridx = (ordre == VueDonnees.FIRST_PANEL)?0:1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1.0f;
		gbc.weighty = 1.0f;
		gbc.insets = new Insets(5,5,5,5);
		onglet.add(cheese, gbc);

		gbc.gridx = (ordre == VueDonnees.FIRST_PANEL)?1:2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0f;
		gbc.weighty = 0f;
		gbc.insets = new Insets(5,5,5,20);
		onglet.add(l, gbc);

		gbc.gridx = (ordre == VueDonnees.FIRST_PANEL)?2:0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = (ordre == VueDonnees.FIRST_PANEL)?GridBagConstraints.EAST:GridBagConstraints.WEST;
		gbc.weightx = 0f;
		gbc.weighty = 0f;
		gbc.insets = new Insets(5,5,5,5);
		onglet.add(arrow, gbc);

		this.add(onglet, titre);

		if(ordre == VueDonnees.FIRST_PANEL){
			this.setTitle(titre);
		}
		
		this.titres[ordre] = titre;
	}

	public void next(){
		Container cont = this.getContentPane();
		CardLayout layout = (CardLayout) cont.getLayout();
		layout.next(cont);

		this.panel = (this.panel == VueDonnees.FIRST_PANEL)?VueDonnees.LAST_PANEL:VueDonnees.FIRST_PANEL;
		this.setTitle(this.titres[this.panel]);
	}
}