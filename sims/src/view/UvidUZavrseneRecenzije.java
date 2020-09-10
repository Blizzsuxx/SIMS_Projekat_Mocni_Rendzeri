package view;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import controler.ZakazanaRecenzijaMenadzer;
import model.Sesija;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UvidUZavrseneRecenzije extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JTable zavrseneRecenzije;
	private JTextField txtSearch;
	private JButton btnRefresh;
	public Sesija sesija;
	private String title;
	
	public UvidUZavrseneRecenzije(Sesija sesija, String title, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		this.sesija = sesija;
		this.title = title;
		setTitle(title);
		setResizable(false);
		getContentPane().setLayout(null);
		
		zavrseneRecenzije = new JTable();
		zavrseneRecenzije.setBounds(10, 11, 587, 224);
		getContentPane().add(zavrseneRecenzije);
		
		JButton btnFilter = new JButton("Filtriraj");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableRowSorter<TableModelWrapper> sorter = new TableRowSorter<TableModelWrapper>((TableModelWrapper)zavrseneRecenzije.getModel()); 
			    sorter.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
			    zavrseneRecenzije.setRowSorter(sorter);
			}
		});
		btnFilter.setBounds(508, 251, 89, 32);
		getContentPane().add(btnFilter);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 252, 488, 31);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		ucitajZavrseneRecenzije();
		
		setVisible(true);
	}
	
	private void ucitajZavrseneRecenzije() throws Exception {
		ZakazanaRecenzijaMenadzer zrm = sesija.getZakazanaRecenzijaMenadzer();
		TableModelWrapper tmw = zrm.getTabelaZavrsenihRecenzija(true);
		zavrseneRecenzije.setModel(tmw);
	}
}
