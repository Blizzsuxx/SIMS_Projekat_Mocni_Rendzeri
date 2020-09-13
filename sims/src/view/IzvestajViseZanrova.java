package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTable;

import model.IzvestajSvihZanrova;
import model.Sesija;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class IzvestajViseZanrova extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JXTable table;
	@SuppressWarnings("unused")
	private Sesija sesija;
	ArrayList<IzvestajSvihZanrova> lista;
	private String title;
	private JButton btnPogledajJednog;
	
	public IzvestajViseZanrova(Sesija s, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.sesija = s;//potrebna da kazem lista zanrova sa nekim podacima kao, broj dela, prosecna ocena zanra preko dela, nesto tako?
		this.title = title;
		s.namestiIzvestaj();
	    lista = s.getIzvestajSvihZanrova().getSviZanrovi();
		setTitle(title);
		setResizable(false);
		initGui();	
	}
	
	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]"); 
		setLayout(mig);
		
		table = new JXTable(new ViseZanrovaModel(lista));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp = new JScrollPane(table);
		add(sp);
		
		TableRowSorter<TableModel> tableSorter=new TableRowSorter<TableModel>();
		tableSorter.setModel(table.getModel());
		table.setRowSorter(tableSorter);
		
		JPanel pSerch=new JPanel(new FlowLayout(FlowLayout.LEFT));
        pSerch.add(new JLabel("Pretraga:"));
		JTextField tfSerch=new JTextField(20);
		pSerch.add(tfSerch);
		add(pSerch, BorderLayout.SOUTH);
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
				
				String sSerch=tfSerch.getText().trim();
				if (sSerch.isEmpty()) {
					tableSorter.setRowFilter(null);
				}
				else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
				}
				
			}
			
		});
		
		btnPogledajJednog = new JButton("Pogledaj jedan zanr");
		getContentPane().add(btnPogledajJednog, "cell 0 2");
		
		btnPogledajJednog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!table.getSelectionModel().isSelectionEmpty()) {
						Zanr zanr = null;
						for (Zanr z : sesija.getZanroviMenadzer().getSviZanrovi()) {
							if (z.getNazivZanra().equals((String)table.getValueAt(table.getSelectedRow(), 0))) {
								zanr = z;
								break;
							}
						}
						if (zanr != null)
							new IzvestajZanra(IzvestajViseZanrova.this.sesija, zanr.getNazivZanra(), zanr, 300, 300);
					}
				} 
				catch (Exception e1) {
					System.out.println("Greska kod ucitavanja izvestaja za jednog zanra");
				}
			}
		});
	}
}
