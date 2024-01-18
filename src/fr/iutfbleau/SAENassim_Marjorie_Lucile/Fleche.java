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

public class Fleche extends JPanel {
	private int cote; // le côté vers lequel pointe la flèche.
	// égal à VueDonnees.FIRST_PANEL si la flèche pointe à droite
	// égal à VueDonnees.LAST_PANEL si la flèche pointe à gauche

  /**
  * La méthode publique Fleche est un constructeur de la classe Fleche.
  * Elle permet la construction d'une nouvelle Flèche. 
  *
  * @param ordre L'int qui donne le sens de la fleche.
  */
	public Fleche(int ordre){
		if(ordre!=VueDonnees.FIRST_PANEL && ordre!=VueDonnees.LAST_PANEL){
			throw new IllegalArgumentException("L'ordre doit être égal à VueDonnees.FIRST_PANEL ou VueDonnees.LAST_PANEL");
		}
		this.cote = ordre;
	}

  /**
  * La méthode protégée paintComponent permet de dessiner une flèche
  * en fonction de son sens.
  *
  * @param g Le Graphics fournit pour dessiner la flèche.
  */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Efface la zone de dessin

		Graphics2D g2d = (Graphics2D) g.create();

		// La largeur, 
		int arrowWidth = this.getWidth() - (this.getWidth()/5);
		int arrowHeight = this.getHeight();
		int arrowX = this.getWidth()/5;
		int arrowY = 0;

		Polygon arrow = new Polygon();
		
		// si la flèche pointe à droite
		if(this.cote == VueDonnees.FIRST_PANEL){
			// Dessin d'une flèche vers la droite
			arrow.addPoint(arrowX, arrowY);
			arrow.addPoint(arrowWidth, arrowHeight / 2); 
			arrow.addPoint(arrowX, arrowHeight);
			g2d.setColor(Color.BLACK);
			g2d.fillPolygon(arrow);
		}

		// si la fleche pointe à gauche
		else{
			arrow.addPoint(arrowWidth, arrowY);
			arrow.addPoint(arrowX, arrowHeight / 2); 
			arrow.addPoint(arrowWidth, arrowHeight);
			g2d.setColor(Color.BLACK);
			g2d.fillPolygon(arrow);
		}
	}
}
