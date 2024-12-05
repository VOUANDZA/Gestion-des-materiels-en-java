package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.CML;
//import controleur.PAV;
import controleur.Tableau;
import Modele.Modele;

public class PanelBord extends PanelDeBase {
	private JPanel unPanel = new JPanel();
	public PanelBord() {
		
		super(new Color(111, 157, 173));

		this.unPanel.setBackground(new Color(111, 157, 173));
		this.unPanel.setBounds(180,30,650,1200);
		this.unPanel.setLayout(new GridLayout(3,1));
		
		String entetes [] = {
				"Nom","Prenom","Materiel","Location","Date","Heure"
		};
		Object matrice [][]= this.getDonnees();
		Tableau unTableau = new Tableau (entetes ,matrice);
		JTable uneTable = new JTable(unTableau);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i=0; i< uneTable.getColumnCount(); i++) {
        uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
		JScrollPane uneScroll = new JScrollPane(uneTable);
		uneScroll.setBounds(70,40,700,300);
		this.unPanel.add(uneScroll);
		
		this.add(this.unPanel);
	}
	public Object [][] getDonnees(){
		ArrayList<CML> lesCMLs =  Modele.selectAllCML();
		Object matrice [][] = new Object [lesCMLs.size()][6];
		int i = 0;
		for (CML unCML : lesCMLs) {
			matrice[i][0] = unCML.getNom();
			matrice[i][1] = unCML.getPrenom();
			matrice[i][2] = unCML.getMateriels();
			matrice[i][3] = unCML.getLocation();
			matrice[i][4] = unCML.getDateloc();
			matrice[i][5] = unCML.getHeureloc();
			i++;
		}
		return matrice;
		
	}
	
}