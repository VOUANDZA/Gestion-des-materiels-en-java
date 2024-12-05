package controleur;

public class CML {
	private String nom, prenom, materiels, location, dateloc, heureloc;

	public CML(String nom, String prenom, String materiels, String location, String dateloc, String heureloc) {
		this.nom = nom;
		this.prenom = prenom;
		this.materiels = materiels;
		this.location = location;
		this.dateloc = dateloc;
		this.heureloc = heureloc;
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

	public void setMarque(String marque) {
		this.prenom = marque;
	}

	public String getMateriels() {
		return materiels;
	}

	public void setMateriel(String materiel) {
		this.materiels = materiel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDateloc() {
		return dateloc;
	}

	public void setDateloc(String dateloc) {
		this.dateloc = dateloc;
	}

	public String getHeureloc() {
		return heureloc;
	}

	public void setHeureloc(String heureloc) {
		this.heureloc = heureloc;
	}

	
}	