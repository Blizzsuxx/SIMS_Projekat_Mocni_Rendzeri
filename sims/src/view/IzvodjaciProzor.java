package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.Izvodjac;
import net.miginfocom.swing.MigLayout;

public class IzvodjaciProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	private List<Izvodjac> izvodjaci;
	private String[] imeKolona = {"Umjetnicko Ime", "Ime Zanra", "Broj Izdatih Albuma", "Broj Muzickih Djela", "Dozvola"};
	
	private JTable table;
	// dugme koje prikaze detaljan prikaz informacija o izabranom izvodjacu
	private JButton info, dozvolaBtn;
	private JFrame parent;
	
	public IzvodjaciProzor(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

	public IzvodjaciProzor(JFrame parent, String ime, int dim1, int dim2, 
			List<Izvodjac> izvodjaci) {
		super(ime, dim1, dim2);
		this.parent = parent;
		
		this.izvodjaci = izvodjaci;
		this.setLayout(new BorderLayout());
		
		this.initGUI();
		this.initAction();
		
		if (ime.equals("Neprihvaceni Izvodjaci") && parent instanceof AdminHomepage) 
			dozvolaBtn.setVisible(true);
		else 
			dozvolaBtn.setVisible(false);
	}
	
	private void initGUI() {
		info = new JButton("Informacije");
		JPanel base = new JPanel(new MigLayout());
		base.add(info);
		dozvolaBtn = new JButton("Odobravanje Zahtjeva");
		base.add(dozvolaBtn);
		this.add(base, BorderLayout.NORTH);
		
		table = new JTable(new IzvodjaciModel(imeKolona, izvodjaci));
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
	}
	
	private void initAction() {
		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(IzvodjaciProzor.this, "Morate selektovati nekog od izvodjaca.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
				}
				
			}
		});
		
		dozvolaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(IzvodjaciProzor.this, "Morate selektovati nekog od izvodjaca.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (JOptionPane.showConfirmDialog(null, "Jeste sigurni da zelite odobriti izvodjaca?", 
							"Odobravanje zahtjeva izvodjaca",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String korisnickoIme = table.getModel().getValueAt(rIndex, 0).toString();
						if (((AdminHomepage)parent).getSesija().getIzvodjacMenadzer().dozvolaIzvodjaca(korisnickoIme)){
							JOptionPane.showMessageDialog(null,"Uspjesno promjenjen status izvodjac."); 
						} else {
							JOptionPane.showMessageDialog(null,"Radnja nije izvrsena."); 
						}
						IzvodjaciProzor.this.refreshData();
					}
				}
				
				
			}
			
		});
	}
	
	private void refreshData() {
		IzvodjaciModel im = (IzvodjaciModel)table.getModel();
		im.fireTableDataChanged();
	}
}
