package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTable;

import model.IzvestajSvihZanrova;
import model.MuzickoDelo;
import model.Sesija;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class IzvestajZanra extends MojDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Zanr zanr;
	private JTable table;
	private String title;
	private JTextField tfUkupnoKom, tfUkupno, tfProsecnaOcena;
	private IzvestajSvihZanrova jedanZanr;
	private ArrayList<MuzickoDelo> dela;

	public IzvestajZanra(Sesija s, String title, Zanr z, int dim1, int dim2) {
		super(title, dim1+100, dim2+100);
		this.setSesija(s);
		this.setZanr(z);
		this.dela=s.filtrirajDela(z);
		this.jedanZanr = s.pronadjiPodatkejednogZanra(z.getNazivZanra());
		setTitle(title);
		setResizable(false);
		initGui();
	}


	/**
	 * @return the zanr
	 */
	public Zanr getZanr() {
		return zanr;
	}

	/**
	 * @param zanr the zanr to set
	 */
	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
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
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]20[]10[]");
		setLayout(mig);
		add(new JLabel("Ukupno recenzija zanra: "));
		tfUkupno = new JTextField(10);
		add(tfUkupno);
		tfUkupno.setText(jedanZanr.getBrojRecenzija()+"");
		tfUkupno.setEditable(false);
		
		add(new JLabel("Ukupno komentara: "));
		tfUkupnoKom = new JTextField(10);
		add(tfUkupnoKom);
		tfUkupnoKom.setText(jedanZanr.getBrojKOmentara()+"");
		tfUkupnoKom.setEditable(false);
		
		add(new JLabel("Broj dela zanra: "));
		tfProsecnaOcena = new JTextField(10);
		add(tfProsecnaOcena);
		tfProsecnaOcena.setText(jedanZanr.getBrojMuzdela()+" ");
		tfProsecnaOcena.setEditable(false);
		
		table =  new JTable(new TabelaSaDelima(dela));
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		
		TableRowSorter<TableModel> tableSorter=new TableRowSorter<TableModel>();
		tableSorter.setModel(table.getModel());
		table.setRowSorter(tableSorter);
		
		setVisible(true);
		
	}
}
