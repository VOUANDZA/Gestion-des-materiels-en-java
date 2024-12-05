package controleur;

public class Clients {
  private int id_client;
  private String nom,prenom,adresse,cp,tel;
public Clients(int id_client, String nom, String prenom, String adresse, String cp, String tel) {
	this.id_client = id_client;
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.cp = cp;
	this.tel = tel;
}
public Clients(String nom, String prenom, String adresse, String cp, String tel) {
	this.id_client = 0;
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.cp = cp;
	this.tel = tel; 
}




 
public int getId_client() {
	return id_client;
}
public void setId_client(int id_client) {
	this.id_client = id_client;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public String getCp() {
	return cp;
}
public void setCp(String cp) {
	this.cp = cp;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
  
  


}
