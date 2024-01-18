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

public class Legende extends JPanel {
	Color[] colors; // le tableau des couleurs 
	AbstractMap<?,Integer> pourcents; // on affichera les clé du dictionnaire 

  /**
  * La méthode publique Legende est un constructeur de la classe Legende.
  * Elle permet la construction d'une nouvelle Legende.
  *
  * @param colors Un tableau des couleurs à utiliser pour représenter les 
  * différentes catégories.
  * @param pourcents Une AbstractMap contenant les pourcentages de chaque couleur.
  */
	public Legende(Color colors[], AbstractMap<?,Integer> pourcents) {
		this.setLayout(new GridBagLayout());
		int cpt = 0;
		String s;
		JLabel nom; 
		Menu m;
		Carre c;

		// On prépare la mise en page du carré de couleur
		GridBagConstraints colConst = new GridBagConstraints();
		colConst.gridx = 0; 
		colConst.gridy = 0;
		colConst.gridwidth = 1;
		colConst.gridheight = 1;
		colConst.fill = GridBagConstraints.BOTH;
		colConst.anchor = GridBagConstraints.CENTER;
		colConst.weightx = 1.0f;
		colConst.weighty = 1.0f;
		colConst.insets = new Insets(5,5,5,5);


		// On prépare la mise en page du texte
		GridBagConstraints labConst = new GridBagConstraints();
		labConst.gridx = 1;
		labConst.gridy = 0;
		labConst.gridwidth = 1;
		labConst.gridheight = 1;
		labConst.fill = GridBagConstraints.NONE;
		labConst.anchor = GridBagConstraints.WEST;
		labConst.weightx = 0.5f;
		labConst.weighty = 0.5f;
		labConst.insets = new Insets(5,5,5,5);


		// On parcourt les clés du dictionnaire
		for(Object e : pourcents.keySet()){
			c = new Carre(colors[cpt]); // on crée un carré de la prochaine couleur
			s = e.toString();
			nom = new JLabel(s);

			// si la clé est un menu on affiche son chemin complet au survol de la ligne
			try{
				m = (Menu) e;
				nom.setToolTipText(m.getFullTitre());
				c.setToolTipText(m.getFullTitre());
			}
			catch(ClassCastException exception){}

			// on ajoutele carré et le texte à laprochaine ligne
			colConst.gridy = cpt;
			this.add(c, colConst);
			labConst.gridy = cpt;
			this.add(nom, labConst);

			cpt+=1;
		}
	}
}
