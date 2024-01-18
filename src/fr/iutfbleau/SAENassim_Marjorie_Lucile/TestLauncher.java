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

import java.sql.SQLException;

public class TestLauncher implements ActionListener {
	private VueChoix fenetrechoix; // la fenêtre de choix de protocole
	private VueArbre fenetrearbre; // la fenêtre de réalisation du test

	
  /**
  * La méthode publique TestLauncher est un constructeur de la classe
  * TestLauncher.
  * Elle permet la construction d'un nouveau TestLauncher.
  */
	public TestLauncher(){
		this.fenetrechoix = new VueChoix(this);
		this.fenetrechoix.setVisible(true);
	}

	
	/**
	  * La méthode publique actionPerformed ouvre une VueDonnees 
	  * si la référence entrée par l'utilisateur renvoie vers un Protocole
	  * 
	  * 
	  * Elle ouvre une VueDonnes des données du protocole entré si il est valide,
	  * sinon elle ouvre un pop-up qui demande à l'utilisateur d'entrer une nouvelle référence.  
	  * En cas d'erreur au niveau de la base de données, elle ouvre un pop-up 
	  * qui demande à l'utilisateur s'il veut arrêter ou recommencer le programme
	  *
	  * @param e L'événement d'entrée dans le champ de texte
	  */
	public void actionPerformed(ActionEvent e) {
		String text = e.getActionCommand(); // On récupère le texte entré par l'utilisateur

		try{
			// On récupère le protocole par sa référence
			Protocole p = Modele.getProtocole(text);

			// Si le protocole entré existe
			if(p != null){
				// On ferme la fenêtre
				this.fenetrechoix.dispose();

				Menu m = p.getMenu(); // le menu de départ du protocole de test
				int r = Modele.makeResultat(p.getID()); // l'id du résultat créé pour cet excécution du test 
				Arbre t = new Arbre(m, r); // l'arbre contenant les menus du protocole

				// On ouvre une nouvelle fenêtre contenant l'arbre
				this.fenetrearbre = new VueArbre(p.getDescription(), t, r);
				this.fenetrearbre.setVisible(true);
			}

			// Si le protocole entré n'existe pas, on demande àl'utilisateur d'entrer une nouvelle référence
			else{
				JOptionPane.showMessageDialog(this.fenetrechoix, "Référence invalide. Veuillez entrer une référence valide.");
			}
		}

			
		// En cas d'exception, on affiche une VueErreur
		// Si l'utilisateur veut recommencer, on crée un nouveau DataViewLauncher
		// Sinon, le programme s'arrête
			
		catch(SQLException exception){
			int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetrechoix);
			if(reponse == JOptionPane.YES_OPTION){
				this.fenetrechoix.dispose();
				System.out.println("Recommencer");
				TestLauncher c = new TestLauncher();
			}
			else{
				System.out.println("Arreter");
				this.fenetrechoix.dispose();
			}
		}

		catch(ClassNotFoundException exception){
				int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetrechoix);
			if(reponse == JOptionPane.YES_OPTION){
				System.out.println("Recommencer");
				TestLauncher c = new TestLauncher();
			}
			else{
				System.out.println("Arreter");
				this.fenetrechoix.dispose();
			}
		}	
	}
}
