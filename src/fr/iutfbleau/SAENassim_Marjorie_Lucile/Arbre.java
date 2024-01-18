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

public class Arbre extends JTree{
	private VueArbre conteneur; // la fenêtre qui contient l'arbre
	private int idres; // id du résultat associé à cette exécution du test

  /**
  * La méthode publique Arbre est un constructeur de la classe Arbre.
  * Elle permet la construction d'un nouvel Arbre. 
  *
  * @param racine Le menu racine de l'arbre.
  * @param idres L'id du résultat associé à cette exécution du test .
  * @throws SQLException
  * @throws ClassNotFoundException
  */
	public Arbre(Menu racine, int idres) throws SQLException, ClassNotFoundException{
		super();
		this.setRootVisible(false);

		// On crée tous les menus de l'arbre
		Branche root = new Branche(racine);

		this.creerArbre(root);

		DefaultTreeModel modele = new DefaultTreeModel(root);
		this.setModel(modele);
		this.setToggleClickCount(1);
		this.addTreeSelectionListener(new ArbreListener(idres));
	}

  /**
  * La méthode publique setArbreContainer permet de définir la fenêtre VueArbre 
  * qui contient l'arbre.
  *
  * @param va La fenêtre qui contient l'arbre.
  */
	public void setArbreContainer(VueArbre va){
		this.conteneur = va;
	}

  /**
  * La méthode publique getArbreContainer permet d'obtenir 
  * la fenêtre qui contient l'arbre
  *
  * @return La fenêtre qui contient l'arbre.
  */
	public VueArbre getArbreContainer(){
		return this.conteneur;
	}

  /**
  * La méthode privée creerArbre permet de créer récursivement 
  * tous les menus d'un arbre à partir d'une
  * Branche racine.
  *
  * @param racine La première branche de l'arbre.
  * @throws SQLException
  * @throws ClassNotFoundException
  */
	private void creerArbre(Branche racine) throws SQLException, ClassNotFoundException{
		// On récupère tous les sous menus de la racine 
	    Menu[] enfants;
	    Menu racinem = racine.getMenu();
	    enfants = Modele.getMenuFils(racinem.getID());
    
		// Si la racine a des sous menus
		if(enfants != null){
			// on ajoute chaque sous menu comme enfant de la branche racine 
		    for (Menu fils : enfants){
				Branche child = new Branche(fils);
		    	racine.add(child);
				// puis on ajoute les sous menus des sous menus
		    	this.creerArbre(child); 
		    }
		}
	}
}