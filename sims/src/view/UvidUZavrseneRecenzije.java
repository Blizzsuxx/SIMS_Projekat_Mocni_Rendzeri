package view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import controler.ZakazanaRecenzijaMenadzer;
import model.Sesija;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UvidUZavrseneRecenzije extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable zavrseneRecenzije;
	private JTextField txtSearch;
	public Sesija sesija;
	
	public UvidUZavrseneRecenzije(Sesija sesija) throws Exception {
		this.sesija = sesija;
		setTitle("Uvid u zavrsene recenzije");
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
		btnFilter.setBounds(508, 251, 89, 23);
		getContentPane().add(btnFilter);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 252, 488, 20);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		ucitajZavrseneRecenzije();
	}
	
	private void ucitajZavrseneRecenzije() throws Exception {
		ZakazanaRecenzijaMenadzer zrm = sesija.getZakazanaRecenzijaMenadzer();
		TableModelWrapper tmw = zrm.getTabelaZavrsenihRecenzija(true);
		zavrseneRecenzije.setModel(tmw);
	}
}
