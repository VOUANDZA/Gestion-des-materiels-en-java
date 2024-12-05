package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Clients;
import controleur.Materiels;
import controleur.Tableau;
import Modele.Modele;

public class PanelMateriel extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();

	private JButton btEnregistrer= new JButton("Enregistrer");
	private JButton btAnnuler= new JButton("Annuler");

	private JTextField txtNom= new JTextField();
	private JTextField txtMarque= new JTextField();
	private JTextField txtPoids= new JTextField();
	private JTextField txtCapacite= new JTextField();
	private JTextField txtTaille= new JTextField();

	private JTable uneTable = null;

	private static Tableau unTableau = null;
	
	 private JTextField txtMot = new JTextField();
	    private JButton btRechercher = new JButton("Rechercher");


	public PanelMateriel() {

		super(new Color(111, 157, 173));
		
		this.panelForm.setLayout(new GridLayout(6,2));

		this.panelForm.add(new JLabel("   Nom du materiel"));
		this.panelForm.add(this.txtNom);

		this.panelForm.add(new JLabel("   marque du materiel"));
		this.panelForm.add(this.txtMarque);

		this.panelForm.add(new JLabel("   poids du materiel"));
		this.panelForm.add(this.txtPoids);

		this.panelForm.add(new JLabel("   capacite du materiel"));
		this.panelForm.add(this.txtCapacite);

		this.panelForm.add(new JLabel("   taille du materiel"));
		this.panelForm.add(this.txtTaille);

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		btEnregistrer.setBackground(new Color(243, 197, 54));
		btAnnuler.setBackground(new Color(243, 197, 54));

		this.panelForm.setBackground(new Color(133, 181, 237));

		this.panelForm.setBounds(20, 50, 300, 250);
		this.add(this.panelForm);

		// Construction du panel de table
		this.panelTable.setBounds(330, 20, 700, 320);
		this.panelTable.setBackground(new Color(111, 157, 173));
		this.panelTable.setLayout(null);
		String entets[] = { "ID materiels", "Nom", "Marque", "Poids", "Capacite", "Taille" };
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
				int nbclic = e.getClickCount();
				if (nbclic == 2) {
					int numLigne = uneTable.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ce materiel?", "Supression du materiel", JOptionPane.YES_NO_OPTION);
					if(retour ==0) {
						// Supression du materiel
						int id_materiels = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteMateriel(id_materiels);
						// Actualiser l'affichage
						unTableau.suprimmerLigne(numLigne);
					}
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();

					String nom = unTableau.getValueAt(numLigne,1).toString();
					String marque = unTableau.getValueAt(numLigne,2).toString();
					String poids = unTableau.getValueAt(numLigne,3).toString();
					String capacite = unTableau.getValueAt(numLigne,4).toString();
					String taille = unTableau.getValueAt(numLigne,5).toString();

					txtNom.setText(nom);
					txtMarque.setText(marque);
					txtPoids.setText(poids);
					txtCapacite.setText(capacite);
					txtTaille.setText(taille);

					btEnregistrer.setText("Modifier");
				}
			}

		});



		// Rendre les boutons ecoutables
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btRechercher.addActionListener(this);
	}
	
	
	public Object[][] getLesDonnees(String mot) {
        ArrayList<Materiels> lesMateriels = null;
        if (mot.equals("")) {
            lesMateriels = Modele.selectAllMateriels();
        } else {
        	lesMateriels = Modele.selectLikeMateriels(mot);
        }

		Object [][] matrice = new Object [lesMateriels.size()][6];
		int i=0;
		for(Materiels unMateriel : lesMateriels) {
			matrice[i][0] = unMateriel.getId_materiels();
			matrice[i][1] = unMateriel.getNom();
			matrice[i][2] = unMateriel.getMarque();
			matrice[i][3] = unMateriel.getPoids();
			matrice[i][4] = unMateriel.getCapacite();
			matrice[i][5] = unMateriel.getTaille()+"";
			i++;
		}
		return matrice;
	}
	
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtMarque.setText("");
		this.txtPoids.setText("");
		this.txtCapacite.setText("");
		this.txtTaille.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}

	public Materiels saisirMateriel() {
		Materiels unMateriel = null;
		
		
		String nom =this.txtNom.getText();
		String marque =this.txtMarque.getText();
		String poids =this.txtPoids.getText();
		String capacite =this.txtCapacite.getText();
		String taille =this.txtTaille.getText();
		
	

		/*
		 * if (nom.equals("")) { this.txtNom.setBackground(Color.red);
		 * JOptionPane.showMessageDialog(this, "Erreur de saisie");
		 * 
		 * } else { this.txtNom.setBackground(Color.white); }
		 * 
		 * if (marque.equals("")) { this.txtMarque.setBackground(Color.red); } else {
		 * this.txtMarque.setBackground(Color.white); }
		 * 
		 * if (poids.equals("")) { this.txtPoids.setBackground(Color.red); } else {
		 * this.txtPoids.setBackground(Color.white); }
		 * 
		 * if (capacite.equals("")) { this.txtCapacite.setBackground(Color.red); } else
		 * { this.txtCapacite.setBackground(Color.white); }
		 * 
		 * if (taille.equals("")) { this.txtTaille.setBackground(Color.red); } else {
		 * this.txtTaille.setBackground(Color.white); }
		 */

		if (!nom.equals("") && !marque.equals("") && !poids.equals("") && !capacite.equals("") && !taille.equals("")) {
			unMateriel = new Materiels (nom, marque, poids, capacite,taille);
			
		} else {
			return null;
		}
		return unMateriel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource()==this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			if (txtNom.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Erreur de nom");
			}
			else if (txtMarque.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Erreur de marque");
			}
			else
			{
				Materiels unMateriel = this.saisirMateriel();
				Modele.insertMateriel(unMateriel);
				JOptionPane.showMessageDialog(this, "Erreur de saisie");

				// Mettre a jour l'affichage
				unMateriel = Modele.selectWhereMateriel(unMateriel.getNom(), unMateriel.getMarque(),unMateriel.getTaille());

				// Mettre a jour l'affichage
				Object ligne[]= {unMateriel.getId_materiels(), unMateriel.getNom(), unMateriel.getMarque(),unMateriel.getPoids(),unMateriel.getCapacite(),unMateriel.getTaille()};
				unTableau.ajouterLigne(ligne);
				JOptionPane.showMessageDialog(this, "Insertion R�ussie");
				this.viderChamps();}
			}
			
		
		
		else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			
			    
			Materiels unMateriel = this.saisirMateriel();
			
		    JOptionPane.showMessageDialog(this, "Modification effectuee");
		    int numLigne = this.uneTable.getSelectedRow();
		    int id_materiels = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		    unMateriel.setId_materiels(id_materiels);
		    Modele.updateMateriel(unMateriel);
		
		    Object ligne[] = {unMateriel.getId_materiels(), unMateriel.getNom(), unMateriel.getMarque(), unMateriel.getPoids(), unMateriel.getCapacite(), unMateriel.getTaille()};
		    unTableau.modifierLigne(numLigne, ligne);
		    this.viderChamps();
		    this.btEnregistrer.setText("Enregistrer");
		}
		
		
		
		
		 else if (e.getSource() == this.btRechercher) {
	            String mot = this.txtMot.getText();
	            //recuperer la matrice des donnees pour actualiser l'affichage
	            Object matrice[][] = this.getLesDonnees(mot);
	            unTableau.setDonnees(matrice);


	        }
	}

}