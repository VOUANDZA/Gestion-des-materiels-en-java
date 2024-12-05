package vue;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


import controleur.Affichage;
import controleur.Affichage;
import controleur.User;
import Modele.Modele;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {
    
    JPanel panelConnexion = new JPanel();
    private JButton btSeSconnecter = new JButton("Se connecter");
    private JButton btAnnuler = new JButton("Annuler");
    private JTextField txtEmail = new JTextField("");
    private JPasswordField txtMdp = new JPasswordField("");
    
    
    public VueConnexion () {
        this.setTitle("PPE Client lourd");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(111, 157, 173));
        this.setBounds(300, 300, 600, 250);
        this.setLayout(null);
        
        // Construction du panel connexion
        this.panelConnexion.setLayout(new GridLayout(3, 2));
        this.panelConnexion.setBounds(300, 40, 260, 150);
        this.panelConnexion.setBackground(new Color(111, 157, 173));
        this.panelConnexion.add(new JLabel("Email : "));
        this.panelConnexion.add(this.txtEmail);
        
        this.panelConnexion.add(new JLabel("Mot de passe : "));
        this.panelConnexion.add(this.txtMdp);
        
        this.panelConnexion.add(this.btAnnuler);
        this.panelConnexion.add(this.btSeSconnecter);
        
        this.add(this.panelConnexion);
        
        // Installation du logo
        ImageIcon leLogo = new ImageIcon("src/images/logo.png");
        JLabel lbLogo = new JLabel(leLogo);
        lbLogo.setBounds(20, 40, 250, 150);
        this.add(lbLogo);
        
        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btSeSconnecter.addActionListener(this);
        
        // Rendre les txt écoutables
        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);
        
        this.setVisible(true);
        btAnnuler.setBackground(new Color(243, 197, 54));
        btSeSconnecter.setBackground(new Color(243, 197, 54));
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        } else if (e.getSource() == this.btSeSconnecter) {
            traitement();
        }
    }
    
    public void traitement () {
        String email = this.txtEmail.getText();
        String mdp = new String (this.txtMdp.getPassword());
      //hashage mdp
        //mdp = PPE_Maamar.crypterMdp(mdp);
        
        // Vérification en BDD de l'user
        User unUser = Affichage.selectWhereUser(email, mdp);
        
        if (unUser == null) {
            JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants !");
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Bienvenue M. " + unUser.getNom()
                                                + "\n Vous avez le rôle : " + unUser.getRole());
            // Appel de la Vue Générale
            //instancier la vue generale
            Affichage.instancierVueGenerale(unUser);
            
            
            //cacher la vue connexion
            Affichage.rendreVisibleVueConnexion(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            traitement ();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 
    }

}