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
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;

class ArbreListener implements TreeSelectionListener{
	private int ordre = 0; // le nombre de menus déjà parcourus
	private int idres; // l'id du résultat auquel sont associés les menus parcourus
	private ArrayList<TreePath> chemins = new ArrayList<TreePath>(); // liste des menus déjà parcourus

  /**
  * La méthode publique ArbreListener est un constructeur de la classe
  * ArbreListener.
  * Elle permet la construction d'un nouvel ArbreListener.
  *
  * @param res L'id du résultat auquel sont associés les menus parcourus
  */
	public ArbreListener(int res){
		this.idres = res;
	}

  /**
  * La méthode publique valueChanged permet de récupérer les menus de l'arbre
  * parcourus par l'utilisateur.
  * Elle ajoute chaque nouveau menu parcouru à la base de donnée 
  * et affiche un bouton sur la fenêtre si le menu sélectionné est une feuille de l'arbre
  *
  * @param e L'évènement qui a changé la sélection
  */
	public void valueChanged(TreeSelectionEvent e){
		Arbre tree = (Arbre) e.getSource(); // l'arbre sur lequel a eu lieu l'action
		TreePath tp = e.getPath(); // le chemin complet du menu
		Branche b = (Branche) tp.getLastPathComponent(); // le nœud sélectionné
		VueArbre va = tree.getArbreContainer(); // la fenêtre contenant l'arbre
		boolean nouveau; // vrai si le menu n'a pas encore été visité

		try{
			va.removeBouton(); // on enlève le bouton de la fenêtre

			// si le chemin n'est pas dans la liste des chemins visités
			nouveau = (chemins.indexOf(tp) == -1);
			if(nouveau){
				// on ajoute le chemin à la liste des chemins visités
				// et le menu à la table des menus visités
				this.chemins.add(tp);
				Modele.addMenuVisite(b.getMenu().getID(), this.idres, this.ordre);
				this.ordre++;
			}

			// Si le menu sélectionné est une branche
			if(b.getChildCount()==0){
				// On affiche le bouton de sélection finale sur la fenêtre
				tree.getArbreContainer().addBouton(b.getMenu());
			}
		}

		// En cas d'exception, on affiche une VueErreur
		// Si l'utilisateur veut recommencer, on crée un nouveau TestLauncher
		// Sinon, le programme s'arrête
			
		catch(SQLException exception){
			int reponse = VueErreur.afficherErreur(exception.getMessage(), va);
			if(reponse == JOptionPane.YES_OPTION){
				System.out.println("Recommencer");
				TestLauncher c = new TestLauncher();
			}
			else{
				System.out.println("Arreter");
				va.dispose();
			}
		}

		catch(ClassNotFoundException exception){
			int reponse = VueErreur.afficherErreur(exception.getMessage(), va);
			if(reponse == JOptionPane.YES_OPTION){
				System.out.println("Recommencer");
				TestLauncher c = new TestLauncher();
			}
			else{
				System.out.println("Arreter");
				va.dispose();
			}
		}
	}
}