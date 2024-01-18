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
import java.sql.SQLException;

class BoutonListener implements ActionListener{
	private int idres; // l'id du résultat associé au test
	private int idmenu; // l'id de l'action choisie par l'utilisateur
	private JFrame fenetre; // la fenêtre où se déroule le test

  /**
  * La méthode publique BoutonListener est un constructeur de la classe
  * BoutonListener.
  * Elle permet la construction d'un nouveau BoutonListener.
  *
  * @param m L'action choisie par l'utilisateur
  * @param res L'id du résultat associé au test
  * @param fen La fenêtre où se déroule le test
  */
	public BoutonListener(Menu m, int res, JFrame fen){
		this.idmenu = m.getID();
		this.idres = res;
		this.fenetre = fen;
	}

  /**
  * La méthode publique actionPerformed récupère l'action finale choisie par l'utilisateur,
  * l'ajoute à la base de donnée et ferme la fenêtre
  *
  *
  * @param evenement L'évènement de clic sur le bouton
  */
	public void actionPerformed(ActionEvent evenement){
		try{
			// on ajoue l'action finale choisie au résultat du test
			Modele.addAction(this.idres, this.idmenu);
			// on ferme la fenêtre du test
			this.fenetre.dispose();
		}

		// En cas d'exception, on affiche une VueErreur
		// Si l'utilisateur veut recommencer, on crée un nouveau TestLauncher
		// Sinon, le programme s'arrête

		catch(SQLException exception){
			int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetre);
			if(reponse == JOptionPane.YES_OPTION){
				System.out.println("Recommencer");
				TestLauncher c = new TestLauncher();
			}
			else{
				System.out.println("Arreter");
					this.fenetre.dispose();
			}
		}

		catch(ClassNotFoundException exception){
			int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetre);
			if(reponse == JOptionPane.YES_OPTION){
				System.out.println("Recommencer");
				TestLauncher c = new TestLauncher();
			}
			else{
				System.out.println("Arreter");
				this.fenetre.dispose();
			}
		}	
	}

}