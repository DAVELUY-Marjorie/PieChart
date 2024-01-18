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
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VueErreur{

  /**
  * La méthode publique afficherErreur est 
  *
  * @param erreur Le nom de l'erreur
  * @param fenetre La fenêtre où l'on est.
  *
  * @return Un int réponse indiquant s'il faut recommencer ou arrêter.
  */
	public static int afficherErreur(String erreur, JFrame fenetre){

		int reponse;
		String titre = "Une erreur est survenue";
		String[] options = {"Recommencer", "Arrêter"};
		reponse = JOptionPane.showOptionDialog(fenetre, erreur, titre, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);

		return reponse;

	}
}