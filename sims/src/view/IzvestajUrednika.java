package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTable;

import model.Sesija;
import model.Urednik;
import net.miginfocom.swing.MigLayout;

public class IzvestajUrednika extends JFrame { // izvestaj o odabranom uredniku
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Urednik urednik;
	private JButton btnBack;
	private JXTable table;
	private JTextField tfImeUrednika, tfUkupno, tfZadate, tfZaIzmenu;

	public IzvestajUrednika(Sesija s, Urednik u) {
		this.setSesija(s);
		this.urednik = u;
		setSize(400, 400);
		setResizable(false);
		initGui();
		initActions();
	}

	/**
	 * @return the tfZaIzmenu
	 */
	public JTextField getTfZaIzmenu() {
		return tfZaIzmenu;
	}

	/**
	 * @param tfZaIzmenu the tfZaIzmenu to set
	 */
	public void setTfZaIzmenu(JTextField tfZaIzmenu) {
		this.tfZaIzmenu = tfZaIzmenu;
	}

	/**
	 * @return the sesija
	 */
	public Sesija getSesija() {
		return sesija;
	}

	/**
	 * @param sesija the sesija to set
	 */
	public void setSesija(Sesija sesija) {
		this.sesija = sesija;
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]20[]");
		setLayout(mig);
		
		add(new JLabel("Urednik: "));
		tfImeUrednika = new JTextField(20);
		add(tfImeUrednika);
		tfImeUrednika.setText(urednik.getIme()+" "+urednik.getPrezime());
		
		add(new JLabel("Ukupan broj recenzija"));
		tfUkupno = new JTextField(20);
		add(tfUkupno);
		tfUkupno.setText(urednik.getIstorijaRecenzija().size()+"");
		
		add(new JLabel("Broj zadatih recenzija"));
		tfZadate = new JTextField(20);
		add(tfZadate);
		tfZadate.setText(urednik.getZakazaneRecenzije().size()+"");
		
		add(new JLabel("Broj recenzija za izmenu"));
		tfZadate = new JTextField(20);
		add(tfZadate);
		tfZadate.setText(urednik.getZakazaneRecenzije().size()+"");
		add(btnBack);
		btnBack.setText("Nazad");
		
		table = new JXTable(new RecenzijeOdUrednikaModel(this.urednik.getIstorijaRecenzija()));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp = new JScrollPane(table);
		//this.
		add(sp);//, BorderLayout.CENTER);
		
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
				}else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
				}
				
			}
			
		});
		
		
	}
	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent.setVisible(true);
				IzvestajUrednika.this.dispose();
				
			}

			
		});}

}
