package view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTextField;

import controler.ZakazanaRecenzijaMenadzer;
import model.Sesija;

public class UvidUZavrseneRecenzije extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JTable zavrseneRecenzije;
	private JXTextField txtSearch;

	private Sesija sesija;

	
	public UvidUZavrseneRecenzije(Sesija sesija, String title, int dim1, int dim2)  {
		super(title, dim1, dim2);
		this.sesija = sesija;
		setTitle(title);
		setResizable(false);
		
		zavrseneRecenzije = new JTable();
		zavrseneRecenzije.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		zavrseneRecenzije.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(zavrseneRecenzije);
		getContentPane().add(scrollPaneGrid, "dock center");
		
		txtSearch = new JXTextField("pretraga");
		getContentPane().add(txtSearch, "dock south");
		try {
			ucitajZavrseneRecenzije();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		TableRowSorter<TableModelWrapper> sorter = new TableRowSorter<TableModelWrapper>((TableModelWrapper)zavrseneRecenzije.getModel());
		this.zavrseneRecenzije.setRowSorter(sorter);
		
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				changedUpdate(e); 
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				String sSerch = txtSearch.getText().trim();
				if (sSerch.isEmpty()) {
					sorter.setRowFilter(null);
				}
				else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
				}
				
			}
			
		});
		
		
		setVisible(true);
	}
	
	private void ucitajZavrseneRecenzije() throws Exception {
		ZakazanaRecenzijaMenadzer zrm = sesija.getZakazanaRecenzijaMenadzer();
		TableModelWrapper tmw = zrm.getTabelaZavrsenihRecenzija(true);
		zavrseneRecenzije.setModel(tmw);
	}
}
