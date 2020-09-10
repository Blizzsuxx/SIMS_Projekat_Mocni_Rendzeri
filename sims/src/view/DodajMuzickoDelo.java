package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controler.IzvodjacMenadzer;
import controler.ZanroviMenadzer;
import model.Izvodjac;
import model.Sesija;
import model.Zanr;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.SwingConstants;

public class DodajMuzickoDelo extends MojDialog {
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	public Izvodjac izv;
	public JTextField naziv, opis;
	public String naslov, opisDela, datumIzdavanja;
	public JButton btnNazad, btnNapravi;
	public int br; //ako se ovo poziva iz pravljenja albuma onda je 0, tj ima Izvodjaca, ako je 1 nema i ide combo
	private JXTable zanrovi;
	private JLabel lblZanrovi;
	private JDatePickerImpl dtDop;
	private SpringLayout sl_dtDop;
	private JLabel lblIzvodjac;
	private String title;
	@SuppressWarnings("rawtypes")
	private JComboBox izvodjaci;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DodajMuzickoDelo(Sesija sesija, String title, int dim1, int dim2, Izvodjac izv, int br) {
		super(title, dim1, dim2);
		setResizable(false);
		this.izv = izv;
		this.sesija = sesija;
		this.br = br;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(title);
		
		MigLayout mig =  new MigLayout("wrap 2", "[grow]10[grow]", "[]10[]10[21.00]10[67.00,grow][][][][][]");
		getContentPane().setLayout(mig);
		getContentPane().add(new JLabel("Naziv: "), "cell 0 0");
		naziv = new JTextField(50);
		getContentPane().add(naziv, "cell 1 0");
		
		getContentPane().add(new JLabel("Opis: "), "cell 0 1");
		opis = new JTextField(50);
		getContentPane().add(opis, "cell 1 1");
	
		getContentPane().add(new JLabel("Datum izdavanja: "), "cell 0 2");
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dtDop = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtDop = new SpringLayout();
		sl_dtDop.putConstraint(SpringLayout.NORTH, dtDop.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDop);
		sl_dtDop.putConstraint(SpringLayout.WEST, dtDop.getJFormattedTextField(), 33, SpringLayout.WEST, dtDop);
		sl_dtDop.putConstraint(SpringLayout.EAST, dtDop.getJFormattedTextField(), 211, SpringLayout.WEST, dtDop);
		sl_dtDop = (SpringLayout) dtDop.getLayout();
		getContentPane().add(dtDop, "cell 1 2,grow");
		
		ZanroviMenadzer zm = (sesija.getZanroviMenadzer());
		TableModelWrapper tmw = zm.getTabelaZanrova();
		
		lblZanrovi = new JLabel("Zanrovi:");
		getContentPane().add(lblZanrovi, "cell 0 3");
		
		zanrovi = new JXTable();
		zanrovi.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		getContentPane().add(zanrovi, "cell 1 3,grow");
		zanrovi.setModel(tmw);
		
		lblIzvodjac = new JLabel("Izvodjac:");
		getContentPane().add(lblIzvodjac, "cell 0 4");
		
		izvodjaci = new JComboBox();
		if (br == 1) {
			IzvodjacMenadzer im = sesija.getIzvodjacMenadzer();
			for (Izvodjac i : im.getSvi()) {
				izvodjaci.addItem(i.getUmetnickoIme());
			}
			izvodjaci.setSelectedIndex(0);
		}
		
		getContentPane().add(izvodjaci, "cell 1 4,growx");
		
		btnNapravi = new JButton("Dodaj delo");
		getContentPane().add(btnNapravi, "cell 0 8");
		btnNapravi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dodaj();
				}
				catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNazad = new JButton("Nazad");
		btnNazad.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(btnNazad, "cell 1 8,alignx right");
		btnNazad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodajMuzickoDelo.this.dispose();
			}
		});
		
		setVisible(true);	
	}
	
	private void dodaj() throws ParseException {
		datumIzdavanja = dtDop.getJFormattedTextField().getText();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy.");
		String datumIzdavanja2 = sdf2.format(sdf1.parse(datumIzdavanja));
		naslov = naziv.getText();
		opisDela = opis.getText();
		if (br == 1) {
			izv = (sesija.getIzvodjac((String) izvodjaci.getSelectedItem()));
		}
		ZanroviMenadzer zm = sesija.getZanroviMenadzer();
		ArrayList<Zanr> listaZanrova = new ArrayList<Zanr>();
		int[] redovi = zanrovi.getSelectedRows();
		for (int i = 0; i < redovi.length; i++) {
			for (Zanr z : zm.getSviZanrovi()) {
				if (zanrovi.getValueAt(redovi[i], 0).equals(z.getNazivZanra())) {
					listaZanrova.add(z);
				}
			}
		}
		boolean validno = sesija.napraviDelo(datumIzdavanja2, naslov, opisDela, izv, listaZanrova);
		if (!validno) {
			JOptionPane.showMessageDialog(DodajMuzickoDelo.this, "Datum nije ispravan.");
		}
	}
}
