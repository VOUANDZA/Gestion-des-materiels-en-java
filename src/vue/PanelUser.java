package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.User;
import controleur.Users;
import controleur.Materiels;
import controleur.Tableau;
import Modele.Modele;

public class PanelUser extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();

	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");
	
	

	private JTextField txtNom= new JTextField();
	private JTextField txtPrenom= new JTextField();
	private JTextField txtEmail= new JTextField();
	private JTextField txtMdp= new JTextField();
	private JTextField txtRole= new JTextField();

	private JTable uneTable = null;

	private static Tableau unTableau = null;
	private JTextField txtMot = new JTextField();
    private JButton btRechercher = new JButton("Rechercher");

	public PanelUser(User unUser) {

		super(new Color(111, 157, 173));
		
		this.panelForm.setLayout(new GridLayout(6,2));

		this.panelForm.add(new JLabel("  Nom utilisateur"));
		this.panelForm.add(this.txtNom);

		this.panelForm.add(new JLabel("  Prénom utilisateur"));
		this.panelForm.add(this.txtPrenom);

		this.panelForm.add(new JLabel("  email utilisateur"));
		this.panelForm.add(this.txtEmail);

		this.panelForm.add(new JLabel("  mot de passe utilisateur"));
		this.panelForm.add(this.txtMdp);

		this.panelForm.add(new JLabel("  role utilisateur"));
		this.panelForm.add(this.txtRole);

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
		String entets[] = { "ID user", "Nom", "Prenom", "Email", "Mdp", "Role" };
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
						int retour = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer cet utilisateur?", "Supression de l'utilisateur", JOptionPane.YES_NO_OPTION);
						if(retour ==0) {
							// Supression de l'utilisateur
							int iduser = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
							Modele.deleteUser(iduser);
							// Actualiser l'affichage
							unTableau.suprimmerLigne(numLigne);
						}
					} else if (nbclic == 1) {
						int numLigne = uneTable.getSelectedRow();

						String nom = unTableau.getValueAt(numLigne,1).toString();
						String prenom = unTableau.getValueAt(numLigne,2).toString();
						String email = unTableau.getValueAt(numLigne,3).toString();
						String mdp = unTableau.getValueAt(numLigne,4).toString();
						String role = unTableau.getValueAt(numLigne,5).toString();

						txtNom.setText(nom);
						txtPrenom.setText(prenom);
						txtEmail.setText(email);
						txtMdp.setText(mdp);
						txtRole.setText(role);

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
        ArrayList<Users> lesUsers = null;
        if (mot.equals("")) {
        	lesUsers = Modele.selectAllUsers();
        } else {
        	lesUsers = Modele.selectLikeUsers(mot);
        }
		Object [][] matrice = new Object [lesUsers.size()][6];
		int i=0;
		for(Users unUser : lesUsers) {
			matrice[i][0] = unUser.getIduser();
			matrice[i][1] = unUser.getNom();
			matrice[i][2] = unUser.getPrenom();
			matrice[i][3] = unUser.getEmail();
			matrice[i][4] = unUser.getMdp();
			matrice[i][5] = unUser.getRole();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtMdp.setText("");
		this.txtRole.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}

	public Users saisirUsers() {
		Users unUser = null;
		
		String nom =this.txtNom.getText();
		String prenom =this.txtPrenom.getText();
		String email =this.txtEmail.getText();
		String mdp =this.txtMdp.getText();
		String role =this.txtRole.getText();

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
		
		if (email.equals("")) {
			this.txtEmail.setBackground(Color.red);
		} else {
			this.txtEmail.setBackground(Color.white);
		}

		if (mdp.equals("")) {
			this.txtMdp.setBackground(Color.red);
		} else {
			this.txtMdp.setBackground(Color.white);
		}
		
		if (role.equals("")) {
			this.txtRole.setBackground(Color.red);
		} else {
			this.txtRole.setBackground(Color.white);
		}
	

		if (!nom.equals("") && !prenom.equals("") && !email.equals("") && !mdp.equals("") && !role.equals("")) {
			unUser = new Users (nom, prenom, email, mdp,role);
		} else {
			return null;
		}
		return unUser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			Users unUser = this.saisirUsers();
			if(unUser != null) {
				
			
			Modele.insertUser(unUser);

			// Mettre a jour l'affichage
			unUser = Modele.selectWhereUser(unUser.getNom(), unUser.getPrenom(),unUser.getRole());

			// Mettre a jour l'affichage
			Object ligne[]= {unUser.getIduser(), unUser.getNom(), unUser.getPrenom(),unUser.getEmail(),unUser.getMdp(),unUser.getRole()};
			unTableau.ajouterLigne(ligne);
			JOptionPane.showMessageDialog(this, "Insertion Réussie");
			this.viderChamps();
			} else{
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			}
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Users unUser = this.saisirUsers();
			if(unUser != null) {
				JOptionPane.showMessageDialog(this, "Modification effectuee");
			    int numLigne = this.uneTable.getSelectedRow();
			    int idUser = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			    unUser.setIduser(idUser);
			    Modele.updateUser(unUser);
			    Object ligne[] = {unUser.getIduser(), unUser.getNom(), unUser.getPrenom(), unUser.getEmail(), unUser.getMdp(), unUser.getRole()};
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