package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Clients;
import controleur.Materiels;
import controleur.Tableau;
import controleur.User;
import Modele.Modele;

public class PanelClient extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();

	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	
	

	private JTextField txtNom= new JTextField();
	private JTextField txtPrenom= new JTextField();
	private JTextField txtAdresse= new JTextField();
	private JTextField txtCp= new JTextField();
	private JTextField txtTel= new JTextField();

	private JTable uneTable = null;    

	private static Tableau unTableau = null;
	private JTextField txtMot = new JTextField();
    private JButton btRechercher = new JButton("Rechercher");

	public PanelClient(User unUser) {

		super(new Color(111, 157, 173));
		
		this.panelForm.setLayout(new GridLayout(6,2));

		this.panelForm.add(new JLabel("  Nom du client"));
		this.panelForm.add(this.txtNom);

		this.panelForm.add(new JLabel("  Prénom du client"));
		this.panelForm.add(this.txtPrenom);

		this.panelForm.add(new JLabel("  Adresse du client"));
		this.panelForm.add(this.txtAdresse);

		this.panelForm.add(new JLabel("  code postal du cleint"));
		this.panelForm.add(this.txtCp);

		this.panelForm.add(new JLabel("  Telephone du client"));
		this.panelForm.add(this.txtTel);

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		btEnregistrer.setBackground(new Color(243, 197, 54));
		btAnnuler.setBackground(new Color(243, 197, 54));

		this.panelForm.setBackground(new Color(133, 181, 237));

		this.panelForm.setBounds(20, 50, 300, 250);
		this.add(this.panelForm);

		// Construction du panel de table
		this.panelTable.setBounds(330, 20, 650, 320);
		this.panelTable.setBackground(new Color(111, 157, 173));
		this.panelTable.setLayout(null);
		String entets[] = { "ID_client", "Nom", "Prenom", "Adresse", "Cp", "Tel" };
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau (entets, donnees);// appel du constructeur tableau
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 30,650,280);
		this.panelTable.add(uneScroll);
		this.txtMot.setBounds(50, 1, 120, 20);
        this.panelTable.add(this.txtMot);
        this.btRechercher.setBounds(190, 0, 120, 20);
        this.panelTable.add(this.btRechercher);
        btRechercher.setBackground(new Color(243, 197, 54));
		this.add(this.panelTable);

		// Gestion de la JTable avec MouseListener
		this.uneTable.addMouseListener(new MouseListener() {
	
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unUser.getRole().equals("admin")) {
					int nbclic = e.getClickCount();
					if (nbclic == 2) {
						int numLigne = uneTable.getSelectedRow();
						int retour = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ce client?", "Supression du client", JOptionPane.YES_NO_OPTION);
						if(retour ==0) {
							// Supression de l'utilisateur
							int id_client = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
							Modele.deleteClient(id_client);
							// Actualiser l'affichage
							unTableau.suprimmerLigne(numLigne);
						}
					} else if (nbclic == 1) {
						int numLigne = uneTable.getSelectedRow();

						String nom = unTableau.getValueAt(numLigne,1).toString();
						String prenom = unTableau.getValueAt(numLigne,2).toString();
						String adresse = unTableau.getValueAt(numLigne,3).toString();
						String cp = unTableau.getValueAt(numLigne,4).toString();
						String tel = unTableau.getValueAt(numLigne,5).toString();

						txtNom.setText(nom);
						txtPrenom.setText(prenom);
						txtAdresse.setText(adresse);
						txtCp.setText(cp);
						txtTel.setText(tel);

						btEnregistrer.setText("Modifier");
					}
				}
				
			} 

		});



		// Rendre les boutons ecoutables
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
	}

	public Object[][] getLesDonnees(String mot) {
        ArrayList<Clients> lesClients = null;
        if (mot.equals("")) {
        	lesClients = Modele.selectAllClients();
        } else {
        	lesClients = Modele.selectLikeClients(mot);
        }
		Object [][] matrice = new Object [lesClients.size()][6];
		int i=0;
		for(Clients unClient : lesClients) {
			matrice[i][0] = unClient.getId_client();
			matrice[i][1] = unClient.getNom();
			matrice[i][2] = unClient.getPrenom();
			matrice[i][3] = unClient.getAdresse();
			matrice[i][4] = unClient.getCp();
			matrice[i][5] = unClient.getTel();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtAdresse.setText("");
		this.txtCp.setText("");
		this.txtTel.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}

	public Clients saisirClients() {
		Clients unClient = null;
		
		String nom =this.txtNom.getText();
		String prenom =this.txtPrenom.getText();
		String adresse =this.txtAdresse.getText();
		String cp =this.txtCp.getText();
		String tel =this.txtTel.getText();

		if (nom.equals("")) {
			this.txtNom.setBackground(Color.red);
		} else {
			this.txtNom.setBackground(Color.white);
		}
		
		if (prenom.equals("")) {
			this.txtPrenom.setBackground(Color.red);
		} else {
			this.txtPrenom.setBackground(Color.white);
		}
		
		if (adresse.equals("")) {
			this.txtAdresse.setBackground(Color.red);
		} else {
			this.txtAdresse.setBackground(Color.white);
		}

		if (cp.equals("")) {
			this.txtCp.setBackground(Color.red);
		} else {
			this.txtCp.setBackground(Color.white);
		}
		
		if (tel.equals("")) {
			this.txtTel.setBackground(Color.red);
		} else {
			this.txtTel.setBackground(Color.white);
		}
	

		if (!nom.equals("") && !prenom.equals("") && !adresse.equals("") && !cp.equals("") && !tel.equals("")) {
			unClient = new Clients (nom, prenom, adresse, cp,tel);
		} else {
			return null;
		}
		return unClient;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			Clients unClient = this.saisirClients();
			if(unClient != null) {
				
			
			Modele.insertClient(unClient);

			// Mettre a jour l'affichage
			unClient = Modele.selectWhereClient(unClient.getNom(), unClient.getPrenom(),unClient.getAdresse());
		
			// Mettre a jour l'affichage
			Object ligne[]= {unClient.getId_client(), unClient.getNom(), unClient.getPrenom(),unClient.getAdresse(),unClient.getCp(),unClient.getTel()};
			unTableau.ajouterLigne(ligne);
			JOptionPane.showMessageDialog(this, "Insertion Réussie");
			this.viderChamps();
			} else{
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			}
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Clients unClient = this.saisirClients();
			if(unClient != null) {
				JOptionPane.showMessageDialog(this, "Modification effectuee");
			    int numLigne = this.uneTable.getSelectedRow();
			    int id_client = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			    unClient.setId_client(id_client);
			    Modele.updateClient(unClient);
			    Object ligne[] = {unClient.getId_client(), unClient.getNom(), unClient.getPrenom(), unClient.getAdresse(), unClient.getCp(), unClient.getTel()};
			    unTableau.modifierLigne(numLigne, ligne);
			    this.viderChamps();
			    this.btEnregistrer.setText("Enregistrer");
			} else{
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			}
		    
		}
		
		else if (e.getSource() == this.btRechercher) {
            String mot = this.txtMot.getText();
            //recuperer la matrice des donnees pour actualiser l'affichage
            Object matrice[][] = this.getLesDonnees(mot);
            unTableau.setDonnees(matrice);


        }
	}
	

}