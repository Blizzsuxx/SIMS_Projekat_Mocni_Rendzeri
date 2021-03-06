package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;

import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class ZanrProzor extends MojDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	private List<Zanr> zanrovi;
	private String[] imenaKolona = {"Naziv Zanra", "Status"};
	
	private JButton btnAdd,btnEdit;
	private JFrame parent;
	private JXTable table;
	private TablePopupMenu popupMenu;
	
	public ZanrProzor(JFrame parent, String ime, int dimension1, int dimension2, List<Zanr> zanrovi) {
		super(parent, ime, dimension1, dimension2);
		this.zanrovi = zanrovi;
		this.parent = parent;
		
		this.setLayout(new BorderLayout());
		
		this.initGUI();
		this.actionGUI();
	}

	private void initGUI() {
		JPanel base = new JPanel(new MigLayout());
		this.add(base, BorderLayout.NORTH);
		
		ImageIcon addI = new ImageIcon("slike/add.gif");
		btnAdd = new JButton(addI);
		ImageIcon editI = new ImageIcon("slike/edit.gif");
		btnEdit = new JButton(editI);
		base.add(btnAdd);
		base.add(btnEdit);
	
		table = new JXTable(new ZanrModel(imenaKolona, zanrovi));
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		popupMenu = new TablePopupMenu();
        table.setComponentPopupMenu(popupMenu);
        table.addMouseListener(new TableMouseListener(table));
        
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
	}
	
	private void actionGUI() {
		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		popupMenu.menuItemAdd.addActionListener(this);
		popupMenu.menuItemEdit.addActionListener(this);
		popupMenu.menuItemDelete.addActionListener(this);
	}
	
	private void dodaj() {
		String naziv =JOptionPane.showInputDialog(null,"Unesi novi zanr:");
		if (naziv != null && !naziv.isEmpty() && ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(naziv) == null) {
			Zanr z = new Zanr(naziv, true);
			zanrovi.add(z);
			((AdminHomepage)parent).getSesija().getZanroviMenadzer().getSviZanrovi().add(z);
		}
		ZanrProzor.this.refreshData();
	}
	
	private void edituj() {
		int rIndex = table.getSelectedRow();
		if (rIndex < 0) {
			JOptionPane.showMessageDialog(ZanrProzor.this, "Morate selektovati zanr.",
					 "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String nazivZanra = table.getModel().getValueAt(rIndex, 0).toString();
			Zanr z = ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(nazivZanra);
			String nazivNovi =JOptionPane.showInputDialog(null,"Unesi novi naziv zanra:");
			if (nazivNovi != null && !nazivNovi.isEmpty() && ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(nazivNovi) == null) {
				z.setNazivZanra(nazivNovi);
			}
		}
		ZanrProzor.this.refreshData();
	}
	
	private void izbrisi() {
		int rIndex = table.getSelectedRow();
		String nazivZanra = table.getModel().getValueAt(rIndex, 0).toString();
		Zanr z = ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(nazivZanra);
		zanrovi.remove(z);
		z.setStatus(false);
		ZanrProzor.this.refreshData();
	}
	
	private void refreshData() {
		ZanrModel zm = (ZanrModel)table.getModel();
		zm.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd || e.getSource() == popupMenu.menuItemAdd) 
			dodaj();
		else if (e.getSource() == btnEdit || e.getSource() == popupMenu.menuItemEdit)
			edituj();
		else
			izbrisi();
	}
}
