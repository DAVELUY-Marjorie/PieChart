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

public class VueChoix extends JFrame {

  /**
  * La méthode publique VueChoix est un constructeur de la classe VueChoix.
  * Elle permet la construction d'une nouvelle VueChoix.
  * Représente la barre de recherche où entrer une référence protocole.
  *
  * @param al L'ActionListener qu'on ajoute au JTextField.
  */
  public VueChoix(ActionListener al) {

    super();
    this.setTitle("Recherche");
    this.setLayout(new GridBagLayout());
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
    JLabel label = new JLabel("Entrez votre recherche : ");
    JTextField tf = new JTextField(10);
    tf.addActionListener(al);

    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.weightx = 0f;
    gbc.weighty = 0f;
    gbc.insets = new Insets(5, 5, 5, 5);
    this.add(label, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.weightx = 0f;
    gbc.weighty = 0f;
    gbc.insets = new Insets(5, 5, 5, 5);
    this.add(tf, gbc);
  }
}
