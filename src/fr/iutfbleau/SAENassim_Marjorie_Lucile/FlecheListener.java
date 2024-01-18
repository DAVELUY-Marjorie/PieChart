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
import java.awt.event.*;

public class FlecheListener implements MouseListener{
	VueDonnees fen; // la fenêtre contenant la flèche

  /**
  * La méthode publique FlecheListener est un constructeur de la classe
  * FlecheListener.
  * Elle permet la construction d'un nouveau FlecheListener. 
  *
  * @param f La fenêtre contenant la flèche
  */
	public FlecheListener(VueDonnees f){
		this.fen = f;
	}

  /**
  * La méthode publique mouseClicked fait changer d'onglet  
  * la fenêtre où se produit l'événement
  *
  * @param e L'évènement de clic de souris
  */
	public void mouseClicked(MouseEvent e){
		this.fen.next();
	}
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}