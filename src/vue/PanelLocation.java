package vue;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Materiels;
import controleur.Clients;
import controleur.Location;
import Modele.Modele;

public class PanelLocation extends PanelDeBase implements ActionListener{
	
	private JPanel panelTable = new JPanel();
	
	
	private JPanel panelForm = new JPanel();
	private JTextField txtDesignation = new JTextField();
	private JTextField txtDateLoc = new JTextField();
	private JTextField txtHeureLoc = new JTextField();
	private JTextField txtquantite = new JTextField();
	
	private JComboBox<String> cbxId_client1 = new JComboBox<String>();
	
	private JComboBox<String> cbxId_materiels = new JComboBox<String>();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	public PanelLocation() {
		super(new Color(111, 157, 173));
		
		this.panelForm.setBackground(new Color(133, 181, 237));
		this.panelForm.setBounds(20,20,400,300);
		this.panelForm.setLayout(new GridLayout(7,2));
		this.panelForm.add(new JLabel("   Designation"));
		this.panelForm.add(this.txtDesignation);
		this.panelForm.add(new JLabel(" Quantité"));
		this.panelForm.add(this.txtquantite);
		
		
		this.panelForm.add(new JLabel("   Date de Location"));
		this.panelForm.add(this.txtDateLoc);
		this.panelForm.add(new JLabel("   Heure de Location"));
		this.panelForm.add(this.txtHeureLoc);
		this.panelForm.add(new JLabel("   Client"));
		this.panelForm.add(this.cbxId_client1);
		
		this.panelForm.add(new JLabel("   Materiel"));
		this.panelForm.add(this.cbxId_materiels);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		btEnregistrer.setBackground(new Color(243, 197, 54));
		btAnnuler.setBackground(new Color(243, 197, 54)); 
		//remplir les cbx id
		this.remplirCBX();
		
		this.add(this.panelForm);
		//rendre les bouttons cliquale
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
	
	
	
	
		 ImageIcon leLogo = new ImageIcon("src/images/loc.png");
	        JLabel lbLogo = new JLabel(leLogo);
	        lbLogo.setBounds(450, 110, 500, 400);
	        this.add(lbLogo);
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	public void remplirCBX()
	{
		ArrayList<Clients> lesClients = Modele.selectAllClients();
		for(Clients unClient : lesClients)
		{
			this.cbxId_client1.addItem(unClient.getId_client()+"-"+unClient.getNom());
			
		}
		ArrayList<Materiels> lesMateriels = Modele.selectAllMateriels();
		for (Materiels unMateriel : lesMateriels)
		{
			this.cbxId_materiels.addItem(unMateriel.getId_materiels()+"-"+unMateriel.getMarque());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
if(e.getSource()== this.btAnnuler)
{
	this.txtDesignation.setText("");
	this.txtDateLoc.setText("");
	this.txtHeureLoc.setText("");
}
else if(e.getSource()== this.btEnregistrer) {
	String designation = this.txtDesignation.getText();
	String dateLoc = this.txtDateLoc.getText();
	String heureLoc = this.txtHeureLoc.getText();
	String tab[] = this.cbxId_client1.getSelectedItem().toString().split("-");
	int id_client1 = Integer.parseInt(tab[0]);
	
	tab = this.cbxId_materiels.getSelectedItem().toString().split("-");
	int id_materiels = Integer.parseInt(tab[0]);
	
	
	//instacier la classe Vol
	Location unLocation = new Location(designation, dateLoc,heureLoc,id_client1, id_materiels);
	Modele.insertLocation(unLocation);
	JOptionPane.showMessageDialog(this,  "Insertion reussie");
	
	
	this.txtDesignation.setText("");
	this.txtDateLoc.setText("");
	this.txtHeureLoc.setText("");
	
	
	
	
}
	}

}