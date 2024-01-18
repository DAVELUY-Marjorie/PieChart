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
import javax.swing.tree.*;
import java.sql.SQLException;

public class Branche extends DefaultMutableTreeNode{
	Menu menu; // le menu représenté par la branche

  /**
  * La méthode publique Branche est un constructeur de la Branche.
  * Elle permet la construction d'une nouvelle Branche.
  *
  * @param m Le menu représenté par la branche
  */
	public Branche(Menu m){
		super(m.getTitre());
		this.menu = m;
	}

  /**
  * La méthode publique getMenu permet d'obtenir le menu de départ
  * de l'arbre.
  *
  * @return Le menu représenté par la branche.
  */
	public Menu getMenu(){
		return this.menu;
	}

}