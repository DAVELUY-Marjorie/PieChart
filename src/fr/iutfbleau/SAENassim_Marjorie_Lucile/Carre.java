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

import java.util.*;

public class Carre extends JComponent {
	private Color col; // La couleur du carré

  /**
  * La méthode publique Carre est un constructeur de la classe Carre.
  * Elle permet la construction d'un nouveau Carre.
  *
  * @param col La couleur du carré.
  */
	public Carre(Color c){
		this.col = c; 
		this.setPreferredSize(new Dimension(10,10));
	}

  /**
  * La méthode protégée paintComponent permet de dessiner un carré
  * en fonction de sa couleur.
  *
  * @param g Le Graphics fournit pour dessiner le carré.
  */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics g2 = (Graphics) g.create();
		g2.setColor(this.col);
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
	}
}