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

import java.awt.geom.Arc2D;
import java.util.*;

public class Camembert extends JPanel{
  Color[] colors; // les couleurs de chaque part du camembert
  AbstractMap<?,Integer> pourcents = null; // les proportions de chaque part du camembert et les éléments associés 

  /**
  * La méthode publique Camembert est un constructeur de la classe Camembert.
  * Elle permet la construction d'un nouveau Camembert. 
  *
  * @param dico Les proportions de chaque part du camembert et les éléments associés 
  * @param firstPartValidAnswer true si la première part du camembert représente l'unique bonne réponse
  * 
  * @throws IllegalStateException Si les pourcentages dépassent 100%.
  */
    public Camembert(AbstractMap<?,Integer> dico, boolean firstPartValidAnswer) throws IllegalStateException{
      // Le dictionnare n'est pas vide
      if(dico.isEmpty()){
        throw new NullPointerException("Camembert : dictionnaire vide ");
      }


      // Les parts du dictionnaire ne dépassent pas 100pourcents
      Integer totalPourcent = 0;
      for(Map.Entry m : dico.entrySet()){
        totalPourcent += (Integer) m.getValue();

        if(totalPourcent > 100){
          throw new IllegalStateException("Camembert : les pourcentages dépassent 100%");
        }
      }

      this.pourcents = dico;
      int len = this.pourcents.size(); // le nombre de parts
      this.colors = new Color[len];
      int rgb, col, light; // la valeur rgb d'une couleur 
      // et les ajouts à chaque incrémentpour changer de couleur et de clarté

      if(firstPartValidAnswer){
        rgb = 205 * 16 * 16 * 16 * 16; // la couleur de base est rouge foncé
        col = (128 / (len-1)) * 16 * 16; // on se rapproche peu a peu du orange
        light = (50 / (len-1)) * 16 * 16 * 16 * 16; // on éclaircit peu a peu
        this.colors[0] = Color.GREEN; // la première part est verte
      }
      else{
        rgb = 155;  // la couleur de base est bleu foncé
        col = (212 / (len-1)) * 16 * 16 * 16 * 16;  // on se rapproche peu a peu du magenta
        light = 100 / (len-1); // on éclaircit peu a peu
        this.colors[0] = new Color(rgb);
      }

      for (int i = 1; i < len; i++) {
        rgb += col + light;
        this.colors[i] = new Color(rgb);
      }
    }



  /**
  * La méthode protégée paintComponent permet de dessiner un diagramme circulaire
  * en fonction des pourcentages fournis.
  *
  * @param g Le Graphics fournit pour dessiner le diagramme.
  */
  @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g); // Efface la zone de dessin
      Graphics2D g2d = (Graphics2D) g;

      // la taille, le padding et le centre du camembert
      int size, padding, centerX, centerY;
      boolean horizontal;
      int cpt = 0; 

      // on met le padding sur les cotés ou en haut et en bas
      if (this.getWidth() <= this.getHeight()) {
          size = this.getWidth();
          padding = (this.getHeight() - size) / 2;
          horizontal = false;
      } else {
          size = this.getHeight();
          padding = (this.getWidth() - size) / 2;
          horizontal = true;
      }

      int startAngle = 0; // Angle de départ
      int value; // Valeur de la partie actuelle
      int arcAngle; // Angle selon le %

        // Parcours les données

      for (Map.Entry m : this.pourcents.entrySet()) {
        value = (int) m.getValue();
        arcAngle = (int) (value * 360.0 / 100.0);
        g2d.setColor(this.colors[cpt]); // Couleur de remplissage

        // On dessine un arc de camembert avec du padding sur les cotes
        if (horizontal) {
          g2d.fill(new Arc2D.Double(padding, 0, size, size, startAngle, arcAngle, Arc2D.PIE));
        }

        // Ou un arc avec du padding en haut et en bas
        else {
          g2d.fill(new Arc2D.Double(0, padding, size, size, startAngle, arcAngle, Arc2D.PIE));
        }

        startAngle += arcAngle;

        cpt += 1;
      }

      if(startAngle != 360){
        arcAngle = 360 - startAngle;
        // On dessine un arc de camembert avec du padding sur les cotes
        if (horizontal) {
          g2d.fill(new Arc2D.Double(padding, 0, size, size, startAngle, arcAngle, Arc2D.PIE));
        }

        // Ou un arc avec du padding en haut et en bas
        else {
          g2d.fill(new Arc2D.Double(0, padding, size, size, startAngle, arcAngle, Arc2D.PIE));
        }
      }
    }

  /**
  * La méthode publique getLegende permet d'obtenir la légende
  * du Camembert.
  *
  * @return Une nouvelle légende avec sa couleur et son pourcentage.
  */
  public Legende getLegende(){
    return new Legende(this.colors, this.pourcents);
  }

}