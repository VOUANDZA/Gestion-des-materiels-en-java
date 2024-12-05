package controleur;

public class Location {
	private int idloc; 
	private String designation; 
	private String dateloc, heureloc; 
	private int id_client1, id_client2, id_materiels ;
	
	public Location(int idloc, String designation, String dateloc, String heureloc, int id_client1,
			int id_materiels) {
		this.idloc = idloc;
		this.designation = designation;
		this.dateloc = dateloc;
		this.heureloc = heureloc;
		this.id_client1 = id_client1;
		this.id_materiels = id_materiels;
	}
	
	public Location(String designation, String dateloc, String heureloc, int id_client1,
			int id_materiels) {
		this.designation = designation;
		this.dateloc = dateloc;
		this.heureloc = heureloc;
		this.id_client1 = id_client1;
		this.id_materiels = id_materiels;
	}

	public int getIdloc() {
		return idloc;
	}

	public void setIdloc(int idloc) {
		this.idloc = idloc;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public int getId_client1() {
		return id_client1;
	}

	public void setId_client1(int id_client1) {
		this.id_client1 = id_client1;
	}

	

	public int getId_materiels() {
		return id_materiels;
	}

	public void setId_materiels(int id_materiels) {
		this.id_materiels = id_materiels;
	}
	
}