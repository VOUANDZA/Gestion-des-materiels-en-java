package controleur;

public class Technicien {

	private int id_tech;
	private String nom,prenom,adresse,cp,diplome;
	public Technicien(int id_tech, String nom, String prenom, String adresse, String cp, String diplome) {
		this.id_tech = id_tech;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.diplome = diplome;
		
	}
	
	public Technicien(String nom, String prenom, String adresse, String cp, String diplome) {
		this.id_tech = 0;
		this.nom = nom;
		this.prenom = prenom; 
		this.adresse = adresse;
		this.cp = cp;
		this.diplome = diplome;
		
	}

	public int getId_tech() {
		return id_tech;
	}

	public void setId_tech(int id_tech) {
		this.id_tech = id_tech;
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

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	
	
}
