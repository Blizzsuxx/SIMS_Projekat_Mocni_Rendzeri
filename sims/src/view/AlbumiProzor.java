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
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

import model.Album;
import net.miginfocom.swing.MigLayout;

public class AlbumiProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	private List<Album> albumi;
	private String[] imeKolona = {"Naslov", "Opis", "Datum registacije", "Izvodjac", "Dozvola"};
	
	private JXTable table;
	private JButton dozvolaBtn;
	private JFrame parent;
	
	private boolean indikator;
	private String ime;
	
	public AlbumiProzor(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

	public AlbumiProzor(JFrame parent, String ime, int dim1, int dim2, 
			List<Album> albumi) {
		super(ime, dim1, dim2);
		this.parent = parent;
		this.ime = ime;
		
		this.albumi = albumi;
		this.setLayout(new BorderLayout());
		
		JPanel base = new JPanel(new MigLayout());
		dozvolaBtn = new JButton("Odobravanje Zahteva");
		base.add(dozvolaBtn);
		this.add(base, BorderLayout.NORTH);
		dozvolaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(AlbumiProzor.this, "Morate selektovati album.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} 
				else {
					if (JOptionPane.showConfirmDialog(null, "Jeste sigurni da zelite odobriti album?", 
							"Odobravanje zahteva albuma",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String naslov = table.getModel().getValueAt(rIndex, 0).toString();
						if (((AdminHomepage)parent).getSesija().getMuzickiSadrzajMenadzer().dozvolaAlbuma(naslov)){
							JOptionPane.showMessageDialog(null,"Uspesno promenjen status albuma."); 
						} else {
							JOptionPane.showMessageDialog(null,"Radnja nije izvrsena."); 
						}
						AlbumiProzor.this.refreshData();
					}
				}
				
				
			}
			
		});
		
		if (ime.equals("Neprihvaceni Albumi") && parent instanceof AdminHomepage) {
			dozvolaBtn.setVisible(true);
			indikator = true;
		}	
		else {
			dozvolaBtn.setVisible(false);
		}
		
		if (albumi.isEmpty()) {
			DefaultTableModel model = new DefaultTableModel(0, imeKolona.length) ;
			model.setColumnIdentifiers(imeKolona);
			table = new JXTable(model);
		}
		else	
			table = new JXTable(new AlbumModel(imeKolona, albumi, indikator));
		
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		setVisible(true);
	
	}
	
	private void refreshData() {
		AlbumModel am = (AlbumModel)table.getModel();
		am.fireTableDataChanged();
	}
}
