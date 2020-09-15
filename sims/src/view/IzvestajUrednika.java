package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Recenzija;
import model.Sesija;
import model.Urednik;

public class IzvestajUrednika extends MojDialog { // izvestaj o odabranom uredniku
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Urednik urednik;
	private String title;
	private JTextField tfImeUrednika;
	private JTextField tfUkupno;
	private JTextField tfZadate;
	private JTextField tfZaIzmenu;
	private JTable table;
	private JTextField tfSerch;

	public IzvestajUrednika(Sesija s, Urednik u, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.title = title;
		this.sesija = s;
		this.urednik = u;
		setTitle(title);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblImeUrednika = new JLabel("Ime urednika");
		lblImeUrednika.setBounds(10, 15, 80, 14);
		getContentPane().add(lblImeUrednika);
		
		tfImeUrednika = new JTextField(urednik.getIme()+" "+urednik.getPrezime());
		tfImeUrednika.setEditable(false);
		tfImeUrednika.setBounds(138, 8, 435, 29);
		getContentPane().add(tfImeUrednika);
		tfImeUrednika.setColumns(10);
		
		JLabel lblUkupanBrojRecenzija = new JLabel("Ukupan broj recenzija");
		lblUkupanBrojRecenzija.setBounds(10, 58, 123, 14);
		getContentPane().add(lblUkupanBrojRecenzija);
		
		tfUkupno = new JTextField(urednik.getIstorijaRecenzija().size()+"");
		tfUkupno.setEditable(false);
		tfUkupno.setColumns(10);
		tfUkupno.setBounds(138, 48, 219, 29);
		getContentPane().add(tfUkupno);
		
		JLabel lblBrojZadatihRecenzija = new JLabel("Broj zadatih recenzija");
		lblBrojZadatihRecenzija.setBounds(10, 95, 123, 14);
		getContentPane().add(lblBrojZadatihRecenzija);
		
		tfZadate = new JTextField(urednik.getZakazaneRecenzije().size()+"");
		tfZadate.setEditable(false);
		tfZadate.setColumns(10);
		tfZadate.setBounds(138, 88, 219, 29);
		getContentPane().add(tfZadate);
		
		JLabel lblBrojRecenzijaZa = new JLabel("Broj recenzija za izmenu");
		lblBrojRecenzijaZa.setBounds(10, 137, 129, 14);
		getContentPane().add(lblBrojRecenzijaZa);
		
		tfZaIzmenu = new JTextField(urednik.getRecezijaZaIzmenu().size()+"");
		tfZaIzmenu.setEditable(false);
		tfZaIzmenu.setColumns(10);
		tfZaIzmenu.setBounds(138, 128, 219, 29);
		getContentPane().add(tfZaIzmenu);
		
		table = new JTable(new RecenzijeOdUrednikaModel(this.urednik.getIstorijaRecenzija()));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(null);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(table);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(10, 168, 563, 141);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		table.setFillsViewportHeight(true);
		
		JButton btnPogledajJednog = new JButton("Pogledaj jedan");
		btnPogledajJednog.setBounds(462, 320, 111, 29);
		getContentPane().add(btnPogledajJednog);
		
		JLabel lblPretraga = new JLabel("Pretraga");
		lblPretraga.setBounds(10, 320, 55, 29);
		getContentPane().add(lblPretraga);
		
		tfSerch = new JTextField();
		tfSerch.setColumns(10);
		tfSerch.setBounds(66, 320, 382, 29);
		getContentPane().add(tfSerch);
		
		TableRowSorter<TableModel> tableSorter=new TableRowSorter<TableModel>();
		tableSorter.setModel(table.getModel());
		table.setRowSorter(tableSorter);
		
		tfSerch.getDocument().addDocumentListener(new DocumentListener() {

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
				
				String sSerch = tfSerch.getText().trim();
				if (sSerch.isEmpty()) {
					tableSorter.setRowFilter(null);
				}
				else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
				}
			}
			
		});
		
		btnPogledajJednog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!table.getSelectionModel().isSelectionEmpty()) {
						Recenzija recenzija = null;
						for (Recenzija r : sesija.getUtisakMenadzer().getRecenzije()) {
							if (r.getNaslov().equals((String)table.getValueAt(table.getSelectedRow(), 1))
									&& r.getDelo().getNaslov().equals((String)table.getValueAt(table.getSelectedRow(), 2))) {
								recenzija = r;
								break;
							}
						}
						if (recenzija != null)
							new IzvestajRecenzije(IzvestajUrednika.this.sesija, recenzija, recenzija.getNaslov(), 661, 578);
					}
				} 
				catch (Exception e1) {
					System.out.println("Greska kod ucitavanja izvestaja za recenziju");
				}
			}
		});
		
		
	}
}