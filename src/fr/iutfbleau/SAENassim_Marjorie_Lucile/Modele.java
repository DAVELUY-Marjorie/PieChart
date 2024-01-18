package fr.iutfbleau.SAENassim_Marjorie_Lucile;

import java.sql.*;
import java.util.*;

public class Modele {
  private static final int RESULTAT_ID = 1;

  private static final int COUNT = 1;

  private static final int PROTOCOLE_ID = 1;
  private static final int PROTOCOLE_REF = 2;
  private static final int PROTOCOLE_MENU = 3;
  private static final int PROTOCOLE_DESC = 4;
  private static final int PROTOCOLE_ACTION = 5;

  private static final int MENU_ID = 1;
  private static final int MENU_TITRE = 2;
  private static final int MENU_PARENT = 3;
  private static final int MENU_POS = 4;

  private static Connection connexion = null;


	
	/**
		* Ouvre une connexion.
		* 
		* Elle vérifie que l'accès au Driver fonctionne
		* Puis elle génère une connection 
	  * vers la base de données pereiral
		* @return La connection créée
	  * @throws SQLException
	  * @throws ClassNotFoundException
		*/
		public static Connection openCo() throws SQLException, ClassNotFoundException {

			Connection co;

			try{
				Class.forName("org.mariadb.jdbc.Driver");

			} catch(ClassNotFoundException cnfe){
				throw new ClassNotFoundException("openCo : Erreur au niveau du pilote");
			}

			try{
				co = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/pereiral", "pereiral", "pereiral");

				return co;

			} catch(SQLException sqle){
				Modele.closeCo();
				throw new SQLException("openCo : Echec de la connexion");
			}
		}


	
	/**
	  * Ferme une connexion.
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
		public static void closeCo()throws SQLException, ClassNotFoundException {
			if (Modele.connexion != null) {
				try {
					Modele.connexion.close();
					Modele.connexion = null;
				} catch (SQLException e) {
					throw new SQLException("closeCo : Échec de la fermeture de la connexion");
				}
			}
		}


	
	/**
	  * Ferme la connexion donnée en argument.(surcharge)
	  *
	  * @param co La connection qu'on souhaite fermer.
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
		public static void closeCo(Connection co) throws SQLException, ClassNotFoundException {
			if (co != null) {
				try {
					co.close();
				} catch (SQLException e) {
					throw new SQLException("closeCo : Échec de la fermeture de la connexion");
				}
			}
		}
	
	/**
		* Renvoie le protocole corespondant à la référence passée en argument
		* 
		* @param ref La référence du protocole recheché
		* @return La protocole corespondant, ou null s'il n'y en a pas
	  * @throws SQLException
	  * @throws ClassNotFoundException
		*/
		public static Protocole getProtocole(String ref) throws SQLException, ClassNotFoundException{

			Protocole protocole;
			PreparedStatement stmt;
			ResultSet res;

			if (Modele.connexion == null) {
				Modele.connexion = Modele.openCo();
			}

			try {
				String sql = "SELECT * FROM Protocole WHERE reference = (?)";
				stmt = Modele.connexion.prepareStatement(sql);
				stmt.setString(1, ref); 

				res = stmt.executeQuery();

				if (res.next()) {
					protocole = new Protocole(res.getInt(Modele.PROTOCOLE_ID),  res.getString(Modele.PROTOCOLE_REF), res.getInt(Modele.PROTOCOLE_MENU), res.getString(Modele.PROTOCOLE_DESC), res.getInt(Modele.PROTOCOLE_ACTION));

					Modele.closeCo();

					return protocole;
				}
				else{
					Modele.closeCo(); 
					return null;
				}

			} catch (SQLException e) {
				Modele.closeCo();
				throw new SQLException("getProtocole : Impossibilité de récupérer le protocole ");
			}
		}


	
	/**
	  * Méthode prenant l'id d'un menu pour en récupérer ses fils 
	  * s'il en a.
	  * 
	  * @param parent L'id du menu dont on veut obtenir les fils
	  *
	  * @return Les menus fils du menu parent dans un tableau de Menu.
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
		public static Menu[] getMenuFils(int parent) throws SQLException, ClassNotFoundException {

			PreparedStatement stmt1, stmt2;
			ResultSet res1, res2;
			Menu[] data;
			int nbLignes;

			if (Modele.connexion == null) {
				Modele.connexion = Modele.openCo();
			}

			try {

				String sql = "SELECT * FROM Menu WHERE menuparent = (?) ORDER BY position";
				stmt1 = Modele.connexion.prepareStatement(sql);
				stmt1.setInt(1, parent); 

				res1 = stmt1.executeQuery();

				//On récupère le nombre de lignes
				stmt2 = Modele.connexion.prepareStatement("SELECT count(*) AS nbLignes FROM Menu WHERE menuparent = (?); ");
				stmt2.setInt(1, parent); 
				res2 = stmt2.executeQuery();
				res2.next();
				nbLignes = res2.getInt(Modele.COUNT);

				if(nbLignes > 0){
					data = new Menu[nbLignes];
					int cpt = 0;

					while(res1.next()){
						data[cpt] = new Menu(res1.getInt(Modele.MENU_ID), res1.getString(Modele.MENU_TITRE), res1.getInt(Modele.MENU_PARENT), res1.getInt(Modele.MENU_POS));
						cpt++;
					}

					Modele.closeCo();
					return data;
				}
				else{
					Modele.closeCo();
					return null;
				}

			} catch (SQLException e) {
				Modele.closeCo(); 
				throw new SQLException("getMenuFils : Impossibilité d'obtenir le menu fils");
			}
		}


	
	/**
	  * Méthode pour obtenir le menu ayant 'id' comme id
	  * 
	  * @param id L'id du menu qu'on veut obtenir.
	  *
	  * @return Le menu correspondant à l'id.
	  *
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
		public static Menu getMenu(int id) throws SQLException, ClassNotFoundException {

			PreparedStatement stmt;
			ResultSet res;

			if (Modele.connexion == null) {
				Modele.connexion = Modele.openCo();
			}

			try {
				stmt = Modele.connexion.prepareStatement("SELECT * FROM Menu WHERE id = (?)");
				stmt.setInt(1, id); 

				res = stmt.executeQuery();

				if(res.next()){
					return new Menu(res.getInt(Modele.MENU_ID), res.getString(Modele.MENU_TITRE), res.getInt(Modele.MENU_PARENT), res.getInt(Modele.MENU_POS));
				}

				Modele.closeCo(); 

			} catch (SQLException e) {
				Modele.closeCo();
				throw new SQLException("getMenu : Impossibilité d'obtenir le menu");
			}
			return null;
		}


	
  /**
   * Méthode pour obtenir le nombre de résultats pour un protocole.
   * 
   * @param p Le protocole dont on veut savoir le nombre de tests.
   *
   * @return Le nombre de tests correspondant au protocole.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
    public static int getNbTests(Protocole p) throws SQLException, ClassNotFoundException {

    PreparedStatement stmt;
    ResultSet res;
    int nbtests;

    if (Modele.connexion == null) {
      Modele.connexion = Modele.openCo();
    }

    try {
      stmt = Modele.connexion.prepareStatement("SELECT count(*) AS nbtests FROM Resultat WHERE protocole = (?); ");
      stmt.setInt(1, p.getID());
      res = stmt.executeQuery();
      res.next();
      nbtests = res.getInt(Modele.COUNT);

      Modele.closeCo();

      return nbtests;

    } catch (SQLException e) {
		Modele.closeCo();
      	throw new SQLException("getNbTests : Impossibilité d'obtenir le nombre de résultats pour un protocole");
		
    }
  }



  /**
   * La méthode publique statique getPourcentAction renvoie les pourcentages
   * de choix de chaque action.
   * 
   * @param p Le protocole dont on veut connaître les pourcentages de choix de 
   * chaque action.
   *
   * @return Une AbstractMap de clés en String et de valeurs en Integer valant  
   * les pourcentages de choix de chaque action.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static AbstractMap<Menu, Integer> getPourcentAction(Protocole p) throws SQLException, ClassNotFoundException {
      AbstractMap<Menu, Integer> pourcents = new HashMap<Menu, Integer>();
      PreparedStatement stmt;
      ResultSet res;
      Menu m;
      Integer val;
      Integer nbActions = Modele.getNbTests(p);
      String clef;

      if (Modele.connexion == null) {
          Modele.connexion = Modele.openCo();
      }

      try {
          stmt = Modele.connexion.prepareStatement("SELECT DISTINCT Menu.id, Menu.titre, Menu.menuparent, Menu.position FROM Resultat JOIN Menu ON Resultat.action = Menu.id WHERE Resultat.protocole = (?)");
          stmt.setInt(1, p.getID());
          res = stmt.executeQuery();

        	while (res.next()) {
              	m = new Menu(res.getInt(Modele.MENU_ID), res.getString(Modele.MENU_TITRE), res.getInt(Modele.MENU_PARENT), res.getInt(Modele.MENU_POS));

              	val = (Integer) (Modele.getTimesActions(m, p) * 100) / nbActions;
            	pourcents.put(m, val);
            }

          Modele.closeCo();
          return pourcents;

      } catch (SQLException e) {
			Modele.closeCo();
          	throw new SQLException("getPourcentAction : Impossibilité de récupérer les actions");

      }
  }


	
  	/**
   	* La méthode publique statique getPourcentNbMenus renvoie les pourcentages 
   	* des nombres de menus choisis.
   	* 
   	* @param p Le protocole dont on veut connaître les pourcentages des nombres
   	* de menus choisis.
   	*
   	* @return Une AbstractMap de clés en Integer et de valeurs en Integer valant les 
   	* pourcentages des nombres de menus choisis.
    *
    * @throws SQLException
    * @throws ClassNotFoundException
   	*/
  public static AbstractMap<Integer, Integer> getPourcentNbMenus(Protocole p) throws SQLException, ClassNotFoundException {
      AbstractMap<Integer, Integer> pourcents = new HashMap<Integer, Integer>();
      PreparedStatement stmt;
      ResultSet res;
      Integer nbTest, existe, pourcentage;
      Integer total = Modele.getNbTests(p);

      if (Modele.connexion == null) {
          Modele.connexion = Modele.openCo();
      }

      try {
          stmt = Modele.connexion.prepareStatement("SELECT id FROM Resultat WHERE protocole = (?)");
          stmt.setInt(1, p.getID());
          res = stmt.executeQuery();

          while (res.next()) {
              nbTest = (Integer) Modele.getNbMenus(res.getInt(1));
              existe = (Integer) pourcents.get(nbTest);

              if (existe != null) {
                  existe += 1;
                  pourcents.replace(nbTest, existe);
              	} else {
                  	pourcents.put(nbTest, 1);
              	}
          	}

          	for (Map.Entry<Integer, Integer> entry : pourcents.entrySet()) {
              	pourcentage = entry.getValue();
              	pourcentage = (pourcentage * 100) / total;
              	pourcents.put(entry.getKey(), pourcentage);
          	}
		  
          	Modele.closeCo();
          	return pourcents;

      	} catch (SQLException e) {
		  	Modele.closeCo();
	        throw new SQLException("getPourcentNbMenus : Impossibilité de récupérer les pourcentages de menus choisis");

      	}
  }

	
   
	/**
	  *  Méthode pour créer un résultat et renvoyer son id.
	  * 
	  * @param protocole L'id du protocole qu'on veut garder dans le res.
	  *
	  * @return L'id du resultat créé.
	  *
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
		public static int makeResultat(int protocole) throws SQLException, ClassNotFoundException {

			PreparedStatement stmtSel, stmtIns;
			ResultSet res;
			int clef;

			if (Modele.connexion == null) {
				Modele.connexion = Modele.openCo();
			}

			try {

				stmtIns = Modele.connexion.prepareStatement("INSERT INTO `Resultat` (`id`, `protocole`, `action`) VALUES (NULL, (?), NULL);");
				stmtIns.setInt(1, protocole);
				stmtIns.executeUpdate();

				try {
					stmtSel = Modele.connexion.prepareStatement("SELECT `id` FROM `Resultat` r1 WHERE `id` > ALL(SELECT `id` FROM `Resultat` r2 WHERE r2.`id`<> r1.`id`);");
					res = stmtSel.executeQuery();

					if (res.next()) {
						clef = res.getInt(Modele.RESULTAT_ID);

						Modele.closeCo(); 

						return clef;
					}
					else{
						Modele.closeCo();
						throw new SQLException("makeResultat : Le résultat n'existe pas");
					}

				} catch (SQLException e) {
					Modele.closeCo();
					throw new SQLException("makeResultat : Impossibilité de récupérer le résultat");
				}

			} catch (SQLException e) {
				Modele.closeCo();
				throw new SQLException("makeResultat : Le résultat n'a pas été créé");
			}
		}


	
	  /**
	  * Méthode ajoutant une action finale au résultat 
	  * 
	  * @param resultat L'id du resultat qu'on veut modifier
	  *
	  * @param action L'id déterminant l'action finale effectuée.
	  *
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
		public static void addAction(int resultat, int action) throws SQLException, ClassNotFoundException {

			PreparedStatement stmt;

			if (Modele.connexion == null) {
				Modele.connexion = Modele.openCo();
			}

			try {
				stmt = Modele.connexion.prepareStatement("UPDATE `Resultat` SET `action` = (?) WHERE `id` = (?);");
				stmt.setInt(1, action);
				stmt.setInt(2, resultat);
				stmt.executeUpdate();

			} catch (SQLException e) {
				Modele.closeCo(); 
				throw new SQLException("addAction : Impossibilité d'ajouter une action");
			}

			Modele.closeCo(); 
		}


	
	/**
	  *  Méthode pour ajouter un menu qu'on a visité.
	  * 
	  * @param idMenu L'id du menu qu'on a visité.
	  * @param idRes L'id du résultat.
	  * @param ordre Le chiffre donnant l'ordre dans lequel on a
	  * visité les menus.
	  *
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
	public static void addMenuVisite(int idMenu, int idRes, int ordre) throws SQLException, ClassNotFoundException {
			int test = 0;

			PreparedStatement stmt;

			if (Modele.connexion == null) {
				Modele.connexion = Modele.openCo();
			}

			try {
				stmt = Modele.connexion.prepareStatement("INSERT INTO `MenuVisite`(idresultat, idmenu, ordre) VALUES ((?), (?), (?));");

				stmt.setInt(1, idRes);
				stmt.setInt(2, idMenu);
				stmt.setInt(3, ordre);
				stmt.executeUpdate();

				Modele.closeCo();

			} catch (SQLException e) {
				Modele.closeCo(); 
				throw new SQLException("addMenuVisite : Impossibilité d'ajouter le menu visité");
			}
		}  



	
  /**
   * Méthode pour obtenir le nombre de menus visités par un résultat.
   * 
   * @param resultat Le int du résultat dont on veut voir le nombre 
   * de menus visités.
   *
   * @return Le nombre de menus visités par un résultat.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
   public static int getNbMenus(int resultat) throws SQLException, ClassNotFoundException {

    PreparedStatement stmt;
    ResultSet res;
    int nbmenus;

    if (Modele.connexion == null) {
      Modele.connexion = Modele.openCo();
    }

    try {
      stmt = Modele.connexion.prepareStatement("SELECT count(*) AS nbmenus FROM MenuVisite WHERE idresultat = (?); ");
      stmt.setInt(1, resultat);
      res = stmt.executeQuery();
      res.next();
      nbmenus = res.getInt(Modele.COUNT);

      Modele.closeCo();

      return nbmenus;

    } catch (SQLException e) {
		Modele.closeCo();
      	throw new SQLException("getNbMenus : Impossibilité d'obtenir le nombre de menu visité");
    }
  }




	
  /**
   * La méthode publique statique getTimesActions sert à obtenir le nombre 
   * de fois qu'une action a été choisie dans les résultats d'un protocole.
   *
   * @param menu L'action dont on veut obtenir le nombre d'occurrence.
   * @param p    Le protocole dont on veut voir le nombre de fois qu'une action
   * a été choisie.
   *
   * @return Le nombre de fois qu'une action a été choisie dans les résultats 
   * d'un protocole.
   *
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static int getTimesActions(Menu menu, Protocole p) throws SQLException, ClassNotFoundException {

    PreparedStatement stmt;
    ResultSet res;
    int nbactions;
    int idmenu = menu.getID();
    int idprotocole = p.getID();

    if (Modele.connexion == null) {
      Modele.connexion = Modele.openCo();
    }

    try {
      stmt = Modele.connexion.prepareStatement("SELECT count(*) AS nbactions FROM Resultat WHERE protocole = (?) AND action = (?); ");
      stmt.setInt(1, idprotocole);
      stmt.setInt(2, idmenu);
      res = stmt.executeQuery();
      res.next();
      nbactions = res.getInt(Modele.COUNT);

      Modele.closeCo();

      return nbactions;

    } catch (SQLException e) {
		Modele.closeCo();
      	throw new SQLException("getTimesAction : Impossibilité d'obtenir le nombre de fois qu'une action a été choisie");
    }
  }
}