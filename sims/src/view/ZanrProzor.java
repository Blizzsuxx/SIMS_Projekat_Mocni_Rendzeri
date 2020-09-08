package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class ZanrProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	private List<Zanr> zanrovi;
	private String[] imenaKolona = {"Naziv Zanra", "Status"};
	
	private JButton btnAdd,btnEdit;
	private JFrame parent;
	private JTable table;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemDelete;
	
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
	
		table = new JTable(new ZanrModel(imenaKolona, zanrovi));
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		popupMenu = new JPopupMenu();
        menuItemDelete = new JMenuItem("Obrisi");
        popupMenu.add(menuItemDelete);
        
        table.setComponentPopupMenu(popupMenu);
        table.addMouseListener(new MouseAdapter() {
        	 @Override
        	    public void mousePressed(MouseEvent event) {
        	        Point point = event.getPoint();
        	        int currentRow = table.rowAtPoint(point);
        	        table.setRowSelectionInterval(currentRow, currentRow);
        	    }
        });
        
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
	}
	
	private void actionGUI() {
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String naziv =JOptionPane.showInputDialog(null,"Unesi novi zanr:");
				if (!naziv.isEmpty() && ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(naziv) == null) {
					Zanr z = new Zanr(naziv, true);
					zanrovi.add(z);
					((AdminHomepage)parent).getSesija().getZanroviMenadzer().getSviZanrovi().add(z);
				}
				ZanrProzor.this.refreshData();
			}
			
		});
		
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(ZanrProzor.this, "Morate selektovati zanr.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String nazivZanra = table.getModel().getValueAt(rIndex, 0).toString();
					Zanr z = ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(nazivZanra);
					String nazivNovi =JOptionPane.showInputDialog(null,"Unesi novi naziv zanra:");
					if (!nazivNovi.isEmpty() && ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(nazivNovi) == null) {
						z.setNazivZanra(nazivNovi);
					}
				}
				ZanrProzor.this.refreshData();
				
			}
			
		});
		
		menuItemDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rIndex = table.getSelectedRow();
				String nazivZanra = table.getModel().getValueAt(rIndex, 0).toString();
				Zanr z = ((AdminHomepage)parent).getSesija().getZanroviMenadzer().trazi(nazivZanra);
				zanrovi.remove(z);
				z.setStatus(false);
				ZanrProzor.this.refreshData();
				
			}
			
		});
	}
	
	private void refreshData() {
		ZanrModel zm = (ZanrModel)table.getModel();
		zm.fireTableDataChanged();
	}
}
