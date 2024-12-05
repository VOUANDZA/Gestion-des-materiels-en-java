package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Affichage;
import controleur.User;
import controleur.Users;

public class VueGenerale extends JFrame implements ActionListener {
    
    private JButton btQuitter = new JButton("Déconnexion");
    private JButton btProfil = new JButton("Mon Profil");
    private JButton btUSers = new JButton("users");
    private JButton btMateriels = new JButton("materiels");
    private JButton btClient = new JButton("Client");
    private JButton btTechnicien = new JButton("technicien");
    private JButton btLocation = new JButton("Location");
    private JButton btStats = new JButton("Statistiques");
    private JButton btBoard = new JButton("Tableau de bord");
    
    
    /********* les panels*************/
    private JPanel panelMenu = new JPanel();
    private JPanel panelProfil = new JPanel();
    
    private static PanelUser unPanelUser;
    private static PanelMateriel unPanelMateriel= new PanelMateriel();
    private static PanelTechnicien unPanelTechnicien;
    private static PanelClient unPanelClient;
    private static PanelLocation unPanelLocation= new PanelLocation();
    private static PanelBord unPanelBord= new PanelBord();
    private static PanelStats unPanelStats= new PanelStats();
    
    public VueGenerale (User unUser) {
    	this.unPanelUser = new PanelUser(unUser);
    	this.unPanelTechnicien = new PanelTechnicien(unUser);
    	this.unPanelClient = new PanelClient(unUser);
    	
        this.setTitle("LOCATION ADMINISTRATION");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(111, 157, 173));
        this.setBounds(100, 100, 1200, 700);
        this.setLayout(null);
        
        
        // Construction du panel menu
        this.panelMenu.setLayout(new GridLayout(1, 7));
        this.panelMenu.setBounds(40, 0, 1150, 40);
        this.panelMenu.setBackground(new Color(111, 157, 173));
        this.panelMenu.add(btProfil);
        this.panelMenu.add(btUSers);
        this.panelMenu.add(btMateriels);
        this.panelMenu.add(btTechnicien);
        this.panelMenu.add(btClient);
        this.panelMenu.add(btLocation);
        this.panelMenu.add(btStats);
        this.panelMenu.add(btBoard);
        this.panelMenu.add(btQuitter);
        this.add(this.panelMenu);
        //insertion des panels d'administration: pilote / avion/ vol
        this.add(unPanelUser);
        this.add(unPanelMateriel);
        this.add(unPanelTechnicien);
        this.add(unPanelClient);
        this.add(unPanelLocation);
        this.add(unPanelBord); 
        this.add(unPanelStats);
        
        
        
        
        //rendre les boutton cliquable
        this.btProfil.addActionListener(this);
        this.btUSers.addActionListener(this);
        this.btMateriels.addActionListener(this);
        this.btTechnicien.addActionListener(this);
        this.btClient.addActionListener(this);
        this.btLocation.addActionListener(this);
        this.btStats.addActionListener(this);
        this.btBoard.addActionListener(this);
        this.btQuitter.addActionListener(this);
        
        
        
        btQuitter.setBackground(new Color(199, 209, 200));
        btProfil.setBackground(new Color(243, 197, 54));
        btUSers.setBackground(new Color(243, 197, 54));
        btMateriels.setBackground(new Color(243, 197, 54));
        btTechnicien.setBackground(new Color(243, 197, 54));
        btClient.setBackground(new Color(243, 197, 54));
        btLocation.setBackground(new Color(243, 197, 54));
        btStats.setBackground(new Color(243, 197, 54));
        btBoard.setBackground(new Color(243, 197, 54));
        
        
        
     // Construction du panel PROFIL
        ImageIcon leLogo = new ImageIcon("src/images/user.png");
        JLabel lbLogo = new JLabel(leLogo);
        lbLogo.setBounds(450, 110, 500, 400);
        this.add(lbLogo);
        this.panelProfil.setLayout(new GridLayout(5, 2));
        this.panelProfil.setBounds(100, 100, 1000, 500);
        this.panelProfil.setBackground(new Color(135, 195, 201));
        this.panelProfil.setVisible(false);
		this.panelProfil.add(new JLabel("         Nom de l'user : "+unUser.getNom()));
		this.panelProfil.add(new JLabel("         Prenom de l'user : "+unUser.getPrenom()));
		this.panelProfil.add(new JLabel("         Email de l'user : "+unUser.getEmail()));
		this.panelProfil.add(new JLabel("         Role de l'user : "+unUser.getRole()));
		
		 

		
        this.add(this.panelProfil);
        
        
        
        this.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btQuitter)
		{
			Affichage.fermerVueGenerale();
			Affichage.rendreVisibleVueConnexion(true);
		} else if(e.getSource() == this.btProfil) {
			this.panelProfil.setVisible(true);
			unPanelUser.setVisible(false);
			unPanelMateriel.setVisible(false);
			unPanelTechnicien.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelLocation.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelStats.setVisible(false);
			
		} else if(e.getSource() == this.btUSers) {
			this.panelProfil.setVisible(false);
			unPanelUser.setVisible(true);
			unPanelMateriel.setVisible(false);
			unPanelTechnicien.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelLocation.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelStats.setVisible(false);
			
		}else if(e.getSource() == this.btMateriels) {
			this.panelProfil.setVisible(false);
			unPanelUser.setVisible(false);
			unPanelMateriel.setVisible(true);
			unPanelTechnicien.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelLocation.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelStats.setVisible(false);
			
		}else if(e.getSource() == this.btTechnicien) {
			this.panelProfil.setVisible(false);
			unPanelUser.setVisible(false);
			unPanelMateriel.setVisible(false);
			unPanelTechnicien.setVisible(true);
			unPanelClient.setVisible(false);
			unPanelLocation.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelStats.setVisible(false);
			
		}
		else if(e.getSource() == this.btClient) {
			this.panelProfil.setVisible(false);
			unPanelUser.setVisible(false);
			unPanelMateriel.setVisible(false);
			unPanelTechnicien.setVisible(false);
			unPanelClient.setVisible(true);
			unPanelLocation.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelStats.setVisible(false);
			
		}
		else if(e.getSource() == this.btLocation) {
			this.panelProfil.setVisible(false);
			unPanelUser.setVisible(false);
			unPanelMateriel.setVisible(false);
			unPanelTechnicien.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelLocation.setVisible(true);
			unPanelBord.setVisible(false);
			unPanelStats.setVisible(false);
			
		}
		else if(e.getSource() == this.btBoard) {
			this.panelProfil.setVisible(false);
			unPanelUser.setVisible(false);
			unPanelMateriel.setVisible(false);
			unPanelTechnicien.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelLocation.setVisible(false);
			unPanelBord.setVisible(true);
			unPanelStats.setVisible(false);
			
		}
		else if(e.getSource() == this.btStats) {
			this.panelProfil.setVisible(false);
			unPanelUser.setVisible(false);
			unPanelMateriel.setVisible(false);
			unPanelTechnicien.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelLocation.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelStats.setVisible(true);
			
		}
		
	}

}