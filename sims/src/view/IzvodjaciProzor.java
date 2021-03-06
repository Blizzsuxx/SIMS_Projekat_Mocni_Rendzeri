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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

import model.Grupa;
import model.Izvodjac;
import model.Pojedinacanizvodjac;
import net.miginfocom.swing.MigLayout;

public class IzvodjaciProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	private List<Izvodjac> izvodjaci;
	private String[] imeKolona = {"Umjetnicko Ime", "Ime Zanra", "Broj Izdatih Albuma", "Broj Muzickih Djela", "Dozvola", "Status"};
	
	private JXTable table;
	// dugme koje prikaze detaljan prikaz informacija o izabranom izvodjacu
	private JButton info, dozvolaBtn;
	private JFrame parent;
	
	private boolean indikator; // koji proslijedjujemo tabeli na osnovu kojeg znamo da li editovanje odobrenosti moguce
	private String ime;
	
	public IzvodjaciProzor(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

	public IzvodjaciProzor(JFrame parent, String ime, int dim1, int dim2, 
			List<Izvodjac> izvodjaci) {
		super(ime, dim1, dim2+100);
		this.parent = parent;
		this.ime = ime;
		
		this.izvodjaci = izvodjaci;
		this.setLayout(new BorderLayout());
		
		this.initGUI();
		this.initAction();
	
	}
	
	private void initGUI() {
		info = new JButton("Informacije");
		JPanel base = new JPanel(new MigLayout());
		base.add(info);
		dozvolaBtn = new JButton("Odobravanje Zahtjeva");
		base.add(dozvolaBtn);
		this.add(base, BorderLayout.NORTH);
		
		if (ime.equals("Neprihvaceni Izvodjaci") && parent instanceof AdminHomepage) {
			dozvolaBtn.setVisible(true);
			indikator = true;
		}	else {
			dozvolaBtn.setVisible(false);
		}
		
		if (izvodjaci.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel(0, imeKolona.length) ;
			model.setColumnIdentifiers(imeKolona);
			table = new JXTable(model);
		}
		else	
			table = new JXTable(new IzvodjaciModel(imeKolona, izvodjaci, indikator));
		
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
					Izvodjac izvodjac = izvodjaci.get(rIndex);
					IzvodjacView prikazIzvodjaca = null;
					if(izvodjac instanceof Grupa) {
						prikazIzvodjaca = new GrupaView(null, (Grupa) izvodjac);
					} else {
						prikazIzvodjaca = new PojedinacanIzvodjacView(null, (Pojedinacanizvodjac) izvodjac);
					}
					prikazIzvodjaca.setVisible(true);
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
