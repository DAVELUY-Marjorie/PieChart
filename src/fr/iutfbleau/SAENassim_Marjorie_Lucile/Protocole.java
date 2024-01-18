package fr.iutfbleau.SAENassim_Marjorie_Lucile;

import java.sql.SQLException;

public class Protocole{
	private int id;
	private String ref;
	private Menu menu;
	private String desc;
	private Menu action;


  /**
  * La méthode publique Protocole est un constructeur de la classe
  * Protocole.
  * Elle permet la construction d'un nouveau Protocole.
  *
  * @param clef L'id qu'on donne au Protocole.
  * @param alphanum La ref du Protocole.
  * @param depart Le menu initial donné au protocol.
  * @param explication description du protocole.
  * @param arrivee Le menu d'arrivé, l'action finale.
  */
	public Protocole(int clef, String alphanum, Menu depart, String explication, Menu arrivee){
		this.id = clef;
		this.ref = alphanum;
		this.menu = depart;
		this.desc = explication;
		this.action = arrivee;
	}

  /**
  * La méthode publique Protocole est un constructeur de la classe
  * Protocole.
  * Elle permet la construction d'un nouveau Protocole. 
  * Cette méthode fournit les menus de départ et d'arrivé.(surcharge)
  *
  * @param clef L'id qu'on donne au Protocole.
  * @param alphanum La ref du Protocole.
  * @param depart Le menu initial donné au protocol.
  * @param explication description du protocole.
  * @param arrivee Le menu d'arrivé, l'action finale.
  *
  * @throws SQLException
  * @throws ClassNotFoundException
  */
	public Protocole(int clef, String alphanum, int depart, String explication, int arrivee) throws SQLException, ClassNotFoundException{
		this.id = clef;
		this.ref = alphanum;
		if(depart!=0){
			this.menu = Modele.getMenu(depart);
		}
		this.desc = explication;
		this.action = Modele.getMenu(arrivee);
	}


  /**
  * La méthode publique setID permet d'associer une valeur val 
  * à l'id du Protocole.
  *
  * @param val Le nombre à attribuer à l'id du Protocole.
  */
	public void setID(int val){
		this.id = val;
	}

  /**
  * La méthode publique getID permet d'obtenir la valeur de 
  * l'id du Protocole.
  *
  * @return L'id du Protocole.
  */
	public int getID(){
		return this.id;
	}

  /**
  * La méthode publique setRef permet d'associer un string val 
  * à la ref du Protocole.
  *
  * @param val Le String à attribuer à la ref du Protocole.
  */
	public void setRef(String val){
		this.ref = val;
	}

  /**
  * La méthode publique getRef permet d'obtenir le string de 
  * la ref du protocole.
  *
  * @return Le string de la ref du protocole.
  */
	public String getRef(){
		return this.ref;
	}

  /**
  * La méthode publique setMenu permet d'associer un menu de départ au
  * Protocole.
  *
  *
  * @param val L'Id du menu de départ.
  *
  * @throws SQLException
  * @throws ClassNotFoundException
  */
	public void setMenu(int val) throws SQLException, ClassNotFoundException{
		this.menu = Modele.getMenu(val);
	}

  /**
  * La méthode publique setMenu permet d'associer val au menu de
  * départ du Protocole.(surcharge)
  *
  *
  * @param val L'Id du menu de départ.
  */
	public void setMenu(Menu val){
		this.menu = val;
	}

  /**
  * La méthode publique getMenu permet d'obtenir le menu de départ
  * du protocole.
  *
  * @return Le menu de départ du protocole.
  */
	public Menu getMenu(){
		return this.menu;
	}

  /**
  * La méthode publique setDescription permet d'associer un string  
  * val à la description du protocole.
  *
  * @param val Le String à attribuer à la description du protocole.
  */
	public void setDescription(String val){
		this.desc = val;
	}

  /**
  * La méthode publique getDescription permet d'obtenir la description
  * du protocole.
  *
  * @return La description du protocole.
  */
	public String getDescription(){
		return this.desc;
	}

  /**
  * La méthode publique setAction permet d'associer un menu d'arrivée 
  * au Protocole.
  *
  *
  * @param val L'Id du menu d'arrivée.
  *
  * @throws SQLException
  * @throws ClassNotFoundException
  */
	public void setAction(int val) throws SQLException, ClassNotFoundException{
		this.action = Modele.getMenu(val);
	}

  /**
  * La méthode publique setAction permet d'associer val au menu 
  * d'arrivée du Protocole.(surcharge)
  *
  *
  * @param val L'Id du menu d'arrivée.
  */
	public void setAction(Menu val){
		this.action = val;
	}

  /**
  * La méthode publique getAction permet d'obtenir le menu d'
  * arrivée du Protocole.
  *
  * @return Le menu d'arrivée du Protocole.
  */
	public Menu getAction(){
		return this.action;
	}
}