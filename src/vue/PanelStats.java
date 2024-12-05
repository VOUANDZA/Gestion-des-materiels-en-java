package vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Tableau;
import Modele.Modele;

public class PanelStats extends PanelDeBase {
	private JPanel unPanel = new JPanel();
	public PanelStats() {
		super(new Color(111, 157, 173));
		
		this.unPanel.setBackground(new Color(111, 157, 173));
		this.unPanel.setBounds(150,50,700,200);
		this.unPanel.setLayout(new GridLayout(3,1));
		int nbClients = Modele.countClients();
		int nbMateriels = Modele.countMateriels();
		int nbLocations  = Modele.countLocations();
		
		
		
		 ImageIcon leLogo = new ImageIcon("src/images/stats.png");
	        JLabel lbLogo = new JLabel(leLogo);
	        lbLogo.setBounds(250, 100, 500, 400);
	        this.add(lbLogo);
		
		
		
		String entetes [] = {
				"Nombere de clients","Nombre de materiels","Nombre de locations"
		};
		Object matrice [][]= {{nbClients,nbMateriels,nbLocations}};
		Tableau unTableau = new Tableau (entetes ,matrice);
		JTable uneTable = new JTable(unTableau);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0; i< uneTable.getColumnCount(); i++) {
        uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
		JScrollPane uneScroll = new JScrollPane(uneTable);
		uneScroll.setBounds(40,40,250,100);
		this.unPanel.add(uneScroll);
		
		this.add(this.unPanel);
	
		
	}

}
