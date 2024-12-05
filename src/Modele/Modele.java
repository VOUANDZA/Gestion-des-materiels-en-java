package Modele;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import controleur.Clients;
import controleur.Location;
import controleur.Materiels;
import controleur.CML;
import controleur.Technicien;
import controleur.User;
import controleur.Users;
import controleur.Location;

public class Modele {
	 
	private static Bdd uneBdd=new Bdd("localhost","gestion_r","root","");
	
	//private static Bdd uneBdd = new Bdd ("localhost", "airfrance_22", "root", "");
	
	public static int countUsers() {
		int nbusers=0;
		String requete ="select count(*) as nb from user ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat= unStat.executeQuery(requete);
			if (unResultat.next())
			{
				nbusers = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
		return nbusers;
	}
	
	public static int countClients() {
		int nbclients=0;
		String requete ="select count(*) as nb from client ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat= unStat.executeQuery(requete);
			if (unResultat.next())
			{
				nbclients = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		} 
		
		return nbclients;
	}
	
	public static int countMateriels() {
		int nbmateriels=0;
		String requete ="select count(*) as nb from materiels ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat= unStat.executeQuery(requete);
			if (unResultat.next())
			{
				nbmateriels = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
		return nbmateriels;
	}
	
	public static int counttechnicien() {
		int nbtechnicien=0;
		String requete ="select count(*) as nb from technicien ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat= unStat.executeQuery(requete);
			if (unResultat.next())
			{
				nbtechnicien = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
		return nbtechnicien;
	}
	
	public static void insertUser (Users unUser)
	{
		String requete ="insert into user values (null, '"
				+ unUser.getNom()+"','" + unUser.getPrenom()+"','"
				+ unUser.getEmail()+"','" + unUser.getMdp()+"','"
				+ unUser.getRole()+"');"; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
	}
	
	public static void updateUser (Users unUser)
	{
		String requete ="update User set nom = '"
				+ unUser.getNom()+"', prenom='" + unUser.getPrenom()+"',email='"
				+ unUser.getEmail()+"',mdp='" + unUser.getMdp()+"',role='"
				+ unUser.getRole()+"'  where iduser="+unUser.getIduser()+";"; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
	}
	
	public static ArrayList<Users> selectAllUsers ()
	{
		ArrayList<Users> lesUsers = new ArrayList<Users>(); 
		String requete = "select * from user ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete);
			//extraction des pilotes : fetchAll en PHP 
			while (desResultats.next()) //tant qu'il un resultat suivant
			{
				Users unUser = new Users (
						desResultats.getInt("iduser"), 
						desResultats.getString("nom"), 
						desResultats.getString("prenom"),
						desResultats.getString("email"), 
						desResultats.getString("mdp"), 
						desResultats.getString("role")
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesUsers.add(unUser);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return lesUsers; 
	}
	
	public static Users selectWhereUser (int iduser)
	{
		Users unUser = null;  
		String requete = "select * from user where iduser =" +iduser+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				 unUser = new Users (
						    unResultat.getInt("iduser"), 
							unResultat.getString("nom"), 
							unResultat.getString("prenom"),
							unResultat.getString("email"), 
							unResultat.getString("mdp"), 
							unResultat.getString("role")
							);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unUser; 
	}
	
	public static void insertTechnicien (Technicien unTechnicien)
	{
		String requete ="insert into technicien values (null, '"
				+ unTechnicien.getNom()+"','" + unTechnicien.getPrenom()+"','"
				+ unTechnicien.getAdresse()+"','" + unTechnicien.getCp()+"','"
				+ unTechnicien.getDiplome()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);}
		}
		public static void updateTechnicien (Technicien unTechnicien)
		{
			String requete ="update Technicien set nom = '"
					+ unTechnicien.getNom()+"','" + unTechnicien.getPrenom()+"','"
					+ unTechnicien.getAdresse()+"','" + unTechnicien.getCp()+"','"
					+ unTechnicien.getDiplome()+"');";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); 
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp) {
				System.out.println("Erreur de requete :"+requete);
			}
			
		}
		
	
	public static ArrayList<Technicien> selectAllTechniciens ()
	{
		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>(); 
		String requete = "select * from technicien ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete);
			//extraction des pilotes : fetchAll en PHP 
			while (desResultats.next()) //tant qu'il un resultat suivant
			{
				Technicien unTechnicien = new Technicien (
						desResultats.getInt("id_tech"), 
						desResultats.getString("nom"), 
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("diplome")
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesTechniciens.add(unTechnicien); 
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return lesTechniciens; 
	}
	
	public static void insertClient (Clients unClient)
	{
		String requete ="insert into client values (null, '"
				+ unClient.getNom()+"','" + unClient.getPrenom()+"','"
				+ unClient.getAdresse()+"','" + unClient.getCp()+"','"
				+ unClient.getTel()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
	}
	
	
	public static void updateClient (Clients unClient)
	{
		String requete ="update Client set nom = '"
				+ unClient.getNom()+"', prenom='" + unClient.getPrenom()+"',adresse='"
				+ unClient.getAdresse()+"',cp='" + unClient.getCp()+"',tel='"
				+ unClient.getTel()+"'  where id_client="+unClient.getId_client()+";"; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}	
	}
	
	
	
	
	
	public static ArrayList<Clients> selectAllClients ()
	{
		ArrayList<Clients> lesClients = new ArrayList<Clients>(); 
		String requete = "select * from client ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete);
			//extraction des pilotes : fetchAll en PHP 
			while (desResultats.next()) //tant qu'il un resultat suivant
			{
				Clients unClient = new Clients (
						desResultats.getInt("id_client"), 
						desResultats.getString("nom"), 
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"), 
						desResultats.getString("tel") 
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesClients.add(unClient); 
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return lesClients; 
	}
	
	
	
	
	
	
	
	
	
	public static void deleteUser (int iduser)
	{
		String requete ="delete from user where iduser = " + iduser; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
	}
	public static void deleteTechnicien (int id_tech)
	{
		String requete ="delete from technicien where id_tech = " + id_tech; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
	}
	public static void deleteClient (int id_client)
	{
		String requete ="delete from client where id_client = " + id_client; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
	}
	

	
	
	/******************** Gestion des users **************/
	public static User selectWhereUser (String email, String mdp)  {
		User unUser = null;  
		String requete = "select * from user where email='"+email
				+"'   and  mdp ='" + mdp + "' ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un user : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				 unUser = new User (
					unResultat.getInt("iduser"), unResultat.getString("nom"), 
					unResultat.getString("prenom"),unResultat.getString("email"), 
					unResultat.getString("mdp"), unResultat.getString("role")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unUser; 
	}
	
	//materiels
	public static void insertMateriel (Materiels unMateriel)
	{
		String requete ="insert into materiels values (null, '"
				+ unMateriel.getNom()+"','" + unMateriel.getMarque()+"','"
				+ unMateriel.getPoids()+"','" + unMateriel.getCapacite()+"','"
				+ unMateriel.getTaille()+"');"; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
	}
	public static void quantite (int quantite) {
		String nombrem="select count(nom) from materiel";
		int nbre=Integer.decode(nombrem);
		if(quantite>nbre) {
			System.out.println("la quantité est superieur à celle materiel demandé");
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(nombrem);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+nombrem);
		}
	}
	
	public static void updateMateriel (Materiels unMateriel)
	{
		String requete ="update Materiel set nom = '"
				+ unMateriel.getNom()+"', marque='" + unMateriel.getMarque()+"',poids='"
				+ unMateriel.getPoids()+"',capacite='" + unMateriel.getCapacite()+"',taille='"
				+ unMateriel.getTaille()+"'  where id_materiels="+unMateriel.getId_materiels()+";"; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
	}
	
	public static ArrayList<Materiels> selectAllMateriels ()
	{
		ArrayList<Materiels> lesMateriels = new ArrayList<Materiels>(); 
		String requete = "select * from materiels ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete);
			//extraction des pilotes : fetchAll en PHP 
			while (desResultats.next()) //tant qu'il un resultat suivant
			{
				Materiels unMateriel = new Materiels (
						desResultats.getInt("id_materiels"), 
						desResultats.getString("nom"), 
						desResultats.getString("marque"),
						desResultats.getString("poids"), 
						desResultats.getString("capacite"), 
						desResultats.getString("taille")
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesMateriels.add(unMateriel);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return lesMateriels; 
	}
	
	public static Materiels selectWhereMateriel (int id_materiels)
	{
		Materiels unMateriel = null;  
		String requete = "select * from materiels where id_materiels =" +id_materiels+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				 unMateriel = new Materiels (
						    unResultat.getInt("id_materiels"), 
							unResultat.getString("nom"), 
							unResultat.getString("marque"),
							unResultat.getString("poids"), 
							unResultat.getString("capacite"), 
							unResultat.getString("taille")
							);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unMateriel; 
	}
	//
	//
	//
	//
	//********************delete materiel*******************
	
	
	public static void deleteMateriel (int id_materiels)
	{
		String requete ="delete from materiels where id_materiels = " + id_materiels; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
	}
	public static Users selectWhereUser(String nom, String prenom, String role) {
		//on vients de shurcharger la methode
		Users unUser = null;  
		String requete = "select * from user where iduser =" +"nom= '"+nom+"' and prenom='"+prenom+"' and role='"+role+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				 unUser = new Users (
						 unResultat.getInt("iduser"), 
						 unResultat.getString("nom"), 
						 unResultat.getString("prenom"),
						 unResultat.getString("email"), 
						 unResultat.getString("mdp"), 
						 unResultat.getString("role")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unUser; 
	}

	public static Materiels selectWhereMateriel(String nom, String marque, String taille) {
		Materiels unMateriel = null;  
		String requete = "select * from materiels where nom= '"+nom+"' and marque='"+marque+"' and taille='"+taille+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				unMateriel = new Materiels (
						 unResultat.getInt("id_materiels"), 
						 unResultat.getString("nom"), 
						 unResultat.getString("marque"),
						 unResultat.getString("poids"), 
						 unResultat.getString("capacite"), 
						 unResultat.getString("taille")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unMateriel;
	}

	public static Technicien selectWhereTechnicien(String nom, String prenom, String diplome) {
		Technicien unTechnicien = null;  
		String requete = "select * from technicien where  nom= '"+nom+"' and prenom='"+prenom+"' and diplome='"+diplome+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				 unTechnicien = new Technicien (
						unResultat.getInt("id_tech"), 
						unResultat.getString("nom"), 
						unResultat.getString("prenom"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("diplome")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unTechnicien;
	}

	public static Clients selectWhereClient (String nom, String prenom, String adresse)
	{
		Clients unClient = null;  
		String requete = "select * from client where  nom= '"+nom+"' and prenom='"+prenom+"' and adresse='"+adresse+"';";
		System.out.println(requete);
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				 unClient = new Clients (
						    unResultat.getInt("id_client"), 
							unResultat.getString("nom"), 
							unResultat.getString("prenom"),
							unResultat.getString("adresse"), 
							unResultat.getString("cp"), 
							unResultat.getString("tel")
							);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unClient; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void insertLocation (Location unLocation)
	{
		String requete ="insert into location values (null, '"
				+ unLocation.getDesignation()+"','" + unLocation.getDateloc()+"','"
				+ unLocation.getHeureloc()+"','" + unLocation.getId_client1()+"','" + unLocation.getId_materiels()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
	}
	public static ArrayList<Location> selectAllLocations ()
	{
		ArrayList<Location> lesLocations = new ArrayList<Location>(); 
		String requete = "select * from location ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete);
			//extraction des pilotes : fetchAll en PHP 
			while (desResultats.next()) //tant qu'il un resultat suivant
			{
				Location unLocation = new Location (
						desResultats.getInt("idloc"), 
						desResultats.getString("designation"), 
						desResultats.getString("dateloc"),
						desResultats.getString("heureloc"),
						desResultats.getInt("id_client1"), 
						desResultats.getInt("id_materiels") 
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesLocations.add(unLocation); 
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return lesLocations; 
	}
	
	public static void deleteLocation (int idloc)
	{
		String requete ="delete from location where idloc = " + idloc; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
	}

	public static ArrayList<CML> selectAllCML ()
	{
		ArrayList<CML> lesCMLs = new ArrayList<CML>(); 
		String requete = "select * from CML ; "; //la sélection se fait sur la vue.
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete);
			//extraction des pilotes : fetchAll en PHP 
			while (desResultats.next()) //tant qu'il un resultat suivant
			{
				CML unCML = new CML (
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("materiels"),
						desResultats.getString("location"),
						desResultats.getString("dateloc"), 
						desResultats.getString("heureloc") 
						);
				//on ajoute le PAV dans la liste des PAV 
				lesCMLs.add(unCML); 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return lesCMLs; 
	}

	
	
	
	
	
	public static int countLocations() {
		int nblocations=0;
		String requete ="select count(*) as nb from location ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat= unStat.executeQuery(requete);
			if (unResultat.next())
			{
				nblocations = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		
		return nblocations;
	}

	
	
	
	
	
	
	
	
	public static ArrayList<Clients> selectLikeClients(String mot) {

		 

		ArrayList<Clients> lesClients = new ArrayList<Clients>();

		 String requete = "select * from client where "

		 + " nom like '%"+mot+"%' or "

		 + " prenom like '%"+mot+"%' or "

		 + " adresse like '%"+mot+"%' or "

			+ " cp like '%"+mot+"%' or "
		 
		 + " tel like '%"+mot+"%' ; ";

		try {

		uneBdd.seConnecter();

		Statement unStat = uneBdd.getMaConnexion().createStatement();

		ResultSet desResultats = unStat.executeQuery(requete);

		 //extraction des pilotes : fetchAll en PHP

		 while (desResultats.next()) //tant qu'il un resultat suivant

		{

			 Clients unClient = new Clients (
						desResultats.getInt("id_client"), 
						desResultats.getString("nom"), 
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"), 
						desResultats.getString("tel") 
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesClients.add(unClient); 

		}

		 

		unStat.close();

		uneBdd.seDeconnecter();

		}

		catch (SQLException exp) {

		System.out.println("Erreur de requete :"+requete);

		}

		 return lesClients; 

		 

		}
	
	
	
	
	
	
	
	
	public static ArrayList<Materiels> selectLikeMateriels(String mot) {

		 

		ArrayList<Materiels> lesMateriels = new ArrayList<Materiels>();

		 String requete = "select * from materiels where "

		 + " nom like '%"+mot+"%' or "

		 + " marque like '%"+mot+"%' or "

		 + " poids like '%"+mot+"%' or "

			+ " capacite like '%"+mot+"%' or "
		 
		 + " taille like '%"+mot+"%' ; ";

		try {

		uneBdd.seConnecter();

		Statement unStat = uneBdd.getMaConnexion().createStatement();

		ResultSet desResultats = unStat.executeQuery(requete);

		 //extraction des pilotes : fetchAll en PHP

		 while (desResultats.next()) //tant qu'il un resultat suivant

		{

			 Materiels unMateriel = new Materiels (
						desResultats.getInt("id_materiels"), 
						desResultats.getString("nom"), 
						desResultats.getString("marque"),
						desResultats.getString("poids"),
						desResultats.getString("capacite"), 
						desResultats.getString("taille") 
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesMateriels.add(unMateriel); 

		}

		 

		unStat.close();

		uneBdd.seDeconnecter();

		}

		catch (SQLException exp) {

		System.out.println("Erreur de requete :"+requete);

		}

		 return lesMateriels; 

		 

		}
	
	
	
	
	
	
	
	
	public static ArrayList<Users> selectLikeUsers(String mot) {

		 

		ArrayList<Users> lesUsers = new ArrayList<Users>();

		 String requete = "select * from user where "

		 + " nom like '%"+mot+"%' or "

		 + " prenom like '%"+mot+"%' or "

		 + " email like '%"+mot+"%' or "

			+ " mdp like '%"+mot+"%' or "
		 
		 + " role like '%"+mot+"%' ; ";

		try {

		uneBdd.seConnecter();

		Statement unStat = uneBdd.getMaConnexion().createStatement();

		ResultSet desResultats = unStat.executeQuery(requete);

		 //extraction des pilotes : fetchAll en PHP

		 while (desResultats.next()) //tant qu'il un resultat suivant

		{

			 Users unUser = new Users (
						desResultats.getInt("iduser"), 
						desResultats.getString("nom"), 
						desResultats.getString("prenom"),
						desResultats.getString("email"),
						desResultats.getString("mdp"), 
						desResultats.getString("role") 
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesUsers.add(unUser); 

		}

		 

		unStat.close();

		uneBdd.seDeconnecter();

		}

		catch (SQLException exp) {

		System.out.println("Erreur de requete :"+requete);

		}

		 return lesUsers; 

		 

		}
	
	
	
	
	
	
	
	public static ArrayList<Technicien> selectLikeTechniciens(String mot) {

		 

		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();

		 String requete = "select * from technicien where "

		 + " nom like '%"+mot+"%' or "

		 + " prenom like '%"+mot+"%' or "

		 + " adresse like '%"+mot+"%' or "

			+ " cp like '%"+mot+"%' or "
		 
		 + " diplome like '%"+mot+"%' ; ";

		try {

		uneBdd.seConnecter();

		Statement unStat = uneBdd.getMaConnexion().createStatement();

		ResultSet desResultats = unStat.executeQuery(requete);

		 //extraction des pilotes : fetchAll en PHP

		 while (desResultats.next()) //tant qu'il un resultat suivant

		{

			 Technicien unTechnicien = new Technicien (
						desResultats.getInt("id_tech"), 
						desResultats.getString("nom"), 
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"), 
						desResultats.getString("dimplome") 
						);
				//on ajoute le pilote dans la liste des pilotes 
				lesTechniciens.add(unTechnicien); 

		}

		 

		unStat.close();

		uneBdd.seDeconnecter();

		}

		catch (SQLException exp) {

		System.out.println("Erreur de requete :"+requete);

		}

		 return lesTechniciens; 

		 

		}
	
	
	
	
	
	
	
	
	
	
}//fin de la classe
