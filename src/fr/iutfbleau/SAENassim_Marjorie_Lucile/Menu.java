package fr.iutfbleau.SAENassim_Marjorie_Lucile;

import java.sql.SQLException;

public class Menu{
	private int id;
	private String titre;
	private Menu parent = null;
	private int position;


  /**
  * La méthode publique Menu est un constructeur de la classe Menu.
  * Elle permet la construction d'un nouveau Menu.
  *
  * @param clef L'id qu'on donne au menu.
  * @param nom Le titre du menu.
  * @param menup Le menu parent du menu créé.
  * @param ordre Int de la position.
  */
	public Menu(int clef, String nom, Menu menup, int ordre){
		this.id = clef;
		this.titre = nom;
		this.parent = menup;
		this.position = ordre;
	}

	/**
	  * La méthode publique Menu est un constructeur de la classe Menu.
	  * Elle permet la construction d'un nouveau Menu et
	  * l'attribution d'une valeur au menu parent.(surcharge)
	  *
	  * @param clef L'id qu'on donne au menu.
	  * @param nom Le titre du menu.
	  * @param menup Le menu parent du menu créé.
	  * @param ordre Int de la position.
    *
	  * @throws SQLException
    * @throws ClassNotFoundException
	  */
		public Menu(int clef, String nom, int menup, int ordre) throws SQLException, ClassNotFoundException{
			this.id = clef;
			this.titre = nom;
			if(menup!=0){
				this.parent = Modele.getMenu(menup);
			}
			this.position = ordre;
		}


  /**
  * La méthode publique setID permet d'associer une valeur val 
  * à l'id du menu
  *
  * @param val Le nombre à attribuer à l'id du menu.
  */
	public void setID(int val){
		this.id = val;
	}

  /**
  * La méthode publique getID permet d'obtenir la valeur de 
  * l'id du menu
  *
  * @return L'id du menu.
  */
	public int getID(){
		return this.id;
	}


  /**
  * La méthode publique setTitre permet d'associer un string val 
  * au titre du menu
  *
  * @param val Le String à attribuer au titre du menu.
  */
	public void setTitre(String val){
		this.titre = val;
	}

  /**
  * La méthode publique getTitre permet d'obtenir la valeur de 
  * l'id du menu
  *
  * @return Le string du titre du menu.
  */
	public String getTitre(){
		return this.titre;
	}


  /**
  * La méthode publique setParent permet d'associer un menu val 
  * à l'attribut parent du menu.
  *
  * @param val Le menu à attribuer à l'attribut parent du menu.
  */
	public void setParent(Menu val){
		this.parent = val;
	}

  /**
  * La méthode publique getParent permet d'obtenir le menu parent du 
  * menu actuel.
  *
  * @return Le menu parent du menu actuel.
  */
	public Menu getParent(){
		return this.parent;
	}


  /**
  * La méthode publique setPosition permet d'associer une valeur val 
  * à la position du menu
  *
  * @param val Le nombre à attribuer à la position du menu.
  */
	public void setPosition(int val){
		this.position = val;
	}

  /**
  * La méthode publique getPosition permet d'obtenir la valeur de 
  * la position du menu
  *
  * @return La position du menu.
  */
	public int getPosition(){
		return this.position;
	}


	/**
  * La méthode publique getFullTitre permet un affichage personnalisé du menu parent
  * du menu actuel.
  *
  * @return Une chaîne de caractères désignant le parent du menu.
  */
	public String getFullTitre(){

		if(this.getParent().getParent() != null){
			return this.getParent().getFullTitre() + " > " + this.titre;
		}
		else{
			return this.titre;
		}
	}

  /**
  * La méthode publique toString permet un affichage personnalisé du menu parent
  * du menu actuel.
  *
  * @return Une chaîne de caractères désignant le parent du menu.
  */
	@Override
	public String toString(){
		if(this.getParent().getParent() != null){
			return this.titre + " (" + this.getParent().getTitre().substring(0,2) + ")" ;
		}
		else{
			return this.titre;
		}
	}
}