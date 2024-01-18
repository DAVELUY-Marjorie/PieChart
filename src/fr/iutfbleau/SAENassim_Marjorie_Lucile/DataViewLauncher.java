package fr.iutfbleau.SAENassim_Marjorie_Lucile;
import javax.swing.*;
import java.sql.SQLException;
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

import java.util.*;

public class DataViewLauncher implements ActionListener {
	private VueChoix fenetrechoix; // la fenêtre de choix de protocole
	private VueDonnees fenetredata; // la fenêtre de vue des données du protocole

  /**
  * La méthode publique DataViewLauncher est un constructeur de la classe
  * DataViewLauncher.
  * Elle permet la construction d'un nouveau DataViewLauncher et lance la création d'une fenetre de choix.
  */
	public DataViewLauncher(){
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
	
				try{
					// On récupère la répartition du nombre de menus sélectionnés
					AbstractMap<Menu, Integer> actions = Modele.getPourcentAction(p);
					// On récupère la répartition du nombre de menus sélectionnés
					AbstractMap<Integer, Integer> nbMenus = Modele.getPourcentNbMenus(p);

					// On ouvre une nouvelle fenêtre et on lui ajoute les données de répartitions
					this.fenetredata = new VueDonnees();
					this.fenetredata.addDiagramme(actions, VueDonnees.FIRST_PANEL, "Actions finales choisies", true);
					this.fenetredata.addDiagramme(nbMenus, VueDonnees.LAST_PANEL, "Nombre de menus seléctionnés", false);
					this.fenetredata.setVisible(true);
	
				}


				// En cas d'exception, on affiche une VueErreur
				// Si l'utilisateur veut recommencer, on crée un nouveau DataViewLauncher
				// Sinon, le programme s'arrête
					
				catch(IllegalStateException exception){
					this.fenetredata.setVisible(true);
					int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetredata);
					
					if(reponse == JOptionPane.YES_OPTION){
						this.fenetredata.dispose();
						System.out.println("Recommencer");
						DataViewLauncher c = new DataViewLauncher();
					}
					else{
						System.out.println("Arreter");
						this.fenetredata.dispose();
					}
				} 
				catch(NullPointerException exception){
					this.fenetredata.setVisible(true);
					int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetredata);

					if(reponse == JOptionPane.YES_OPTION){
						this.fenetredata.dispose();
						System.out.println("Recommencer");
						DataViewLauncher c = new DataViewLauncher();
					}
					else{
						System.out.println("Arreter");
						this.fenetredata.dispose();
					}
				}
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
			this.fenetredata.setVisible(true);
			int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetredata);

			if(reponse == JOptionPane.YES_OPTION){
				this.fenetredata.dispose();
				System.out.println("Recommencer");
				DataViewLauncher c = new DataViewLauncher();
			}
			else{
				System.out.println("Arreter");
				this.fenetredata.dispose();
			}
		}
		catch(ClassNotFoundException exception){
			this.fenetredata.setVisible(true);
			int reponse = VueErreur.afficherErreur(exception.getMessage(), this.fenetredata);

			if(reponse == JOptionPane.YES_OPTION){
				this.fenetredata.dispose();
				System.out.println("Recommencer");
				DataViewLauncher c = new DataViewLauncher();
			}
			else{
				System.out.println("Arreter");
				this.fenetredata.dispose();
			}
		}

	}

}
