package view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.SpringLayout;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXTable;

import controler.RecenzijeZaIzmenuMenadzer;
import controler.UtisakMenadzer;
import controler.ZakazanaRecenzijaMenadzer;
import model.Recenzija;
import model.RecezijaZaIzmenu;
import model.Sesija;
import model.ZakazanaRecenzija;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;

public class DodavanjeZakRecIRecZaIzemnu extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JXTable recenzije;
	private JDatePickerImpl dtDor;
	private SpringLayout sl_dtDor;
	private JDatePickerImpl dtDod;
	private SpringLayout sl_dtDod;
	private JTextField txtOpis;
	private JPanel pnlZakRec;
	private JPanel pnlRecZaIzmenu;
	private JTextField txtPoruka;
	private Sesija sesija;
	private String title;
	private JRadioButton rbIzmena;
	private JRadioButton rbBrisanje;
	private JRadioButton rbZahtevZaIzmenu;
	private JRadioButton rbZakaziRecenziju;
	
	public DodavanjeZakRecIRecZaIzemnu(Sesija sesija,  String title, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		this.sesija = sesija;
		this.title = title;
		setTitle(title);
		setResizable(false);
		getContentPane().setLayout(null);
		
		recenzije = new JXTable();
		recenzije.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recenzije.setBorder(null);
		recenzije.getTableHeader().setReorderingAllowed(false);
		recenzije.getTableHeader().setResizingAllowed(false);
		recenzije.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(recenzije);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(10, 11, 389, 188);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		recenzije.setFillsViewportHeight(true);
		
		rbZakaziRecenziju = new JRadioButton("Zakazi recenziju");
		rbZakaziRecenziju.setSelected(true);
		rbZakaziRecenziju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlZakRec.setVisible(true);
				pnlRecZaIzmenu.setVisible(false);
				rbZahtevZaIzmenu.setSelected(false);
			}
		});
		rbZakaziRecenziju.setBounds(10, 224, 145, 23);
		getContentPane().add(rbZakaziRecenziju);
		
		rbZahtevZaIzmenu = new JRadioButton("Zahtev za izmenu");
		rbZahtevZaIzmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlRecZaIzmenu.setVisible(true);
				pnlZakRec.setVisible(false);
				rbZakaziRecenziju.setSelected(false);
			}
		});
		rbZahtevZaIzmenu.setBounds(272, 224, 127, 23);
		getContentPane().add(rbZahtevZaIzmenu);
		
		pnlZakRec = new JPanel();
		pnlZakRec.setBounds(10, 254, 389, 209);
		getContentPane().add(pnlZakRec);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dtDor = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtDor = new SpringLayout();
		pnlZakRec.setLayout(null);
		sl_dtDor.putConstraint(SpringLayout.NORTH, dtDor.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDor);
		sl_dtDor.putConstraint(SpringLayout.WEST, dtDor.getJFormattedTextField(), 33, SpringLayout.WEST, dtDor);
		sl_dtDor.putConstraint(SpringLayout.EAST, dtDor.getJFormattedTextField(), 211, SpringLayout.WEST, dtDor);
		sl_dtDor = (SpringLayout) dtDor.getLayout();
		dtDor.setBounds(151, 14, 228, 23);
		pnlZakRec.add(dtDor);
		
		JLabel lblDatumZakazivanja = new JLabel("Datum zakazivanja");
		lblDatumZakazivanja.setBounds(10, 20, 131, 14);
		pnlZakRec.add(lblDatumZakazivanja);
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
		dtDod = new JDatePickerImpl(datePanel2, new DataLabelFormatter());
		sl_dtDod = new SpringLayout();
		pnlZakRec.setLayout(null);
		sl_dtDod.putConstraint(SpringLayout.NORTH, dtDod.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDod);
		sl_dtDod.putConstraint(SpringLayout.WEST, dtDod.getJFormattedTextField(), 33, SpringLayout.WEST, dtDod);
		sl_dtDod.putConstraint(SpringLayout.EAST, dtDod.getJFormattedTextField(), 211, SpringLayout.WEST, dtDod);
		sl_dtDod = (SpringLayout) dtDod.getLayout();
		dtDod.setBounds(151, 48, 228, 23);
		pnlZakRec.add(dtDod);
		
		JLabel lblRok = new JLabel("Rok");
		lblRok.setBounds(10, 55, 48, 14);
		pnlZakRec.add(lblRok);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(10, 101, 48, 14);
		pnlZakRec.add(lblOpis);
		
		txtOpis = new JTextField();
		txtOpis.setBounds(151, 93, 228, 31);
		pnlZakRec.add(txtOpis);
		txtOpis.setColumns(10);
		
		JButton btnZakazi = new JButton("Zakazi");
		btnZakazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					zakazi();
				} 
				catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnZakazi.setBounds(290, 175, 89, 23);
		pnlZakRec.add(btnZakazi);
		
		pnlRecZaIzmenu = new JPanel();
		pnlRecZaIzmenu.setBounds(419, 254, 389, 209);
		getContentPane().add(pnlRecZaIzmenu);
		pnlRecZaIzmenu.setLayout(null);
		
		rbIzmena = new JRadioButton("Izmena");
		rbIzmena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbBrisanje.setSelected(false);
			}
		});
		rbIzmena.setSelected(true);
		rbIzmena.setBounds(6, 7, 71, 23);
		pnlRecZaIzmenu.add(rbIzmena);
		
		rbBrisanje = new JRadioButton("Brisanje");
		rbBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbIzmena.setSelected(false);
			}
		});
		rbBrisanje.setBounds(134, 7, 71, 23);
		pnlRecZaIzmenu.add(rbBrisanje);
		
		JLabel lblPoruka = new JLabel("Poruka");
		lblPoruka.setBounds(6, 53, 48, 14);
		pnlRecZaIzmenu.add(lblPoruka);
		
		JButton btnPosaljiZahtev = new JButton("Posalji zahtev");
		btnPosaljiZahtev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zahtevajIzmenu();
			}
		});
		btnPosaljiZahtev.setBounds(251, 175, 128, 23);
		pnlRecZaIzmenu.add(btnPosaljiZahtev);
		
		txtPoruka = new JTextField();
		txtPoruka.setBounds(79, 45, 300, 31);
		pnlRecZaIzmenu.add(txtPoruka);
		txtPoruka.setColumns(10);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				pnlRecZaIzmenu.setBounds(pnlZakRec.getBounds().x, pnlZakRec.getBounds().y, pnlRecZaIzmenu.getBounds().width, pnlRecZaIzmenu.getBounds().height);
			}
		});
		
		ucitajRecenzije();
		
		pnlRecZaIzmenu.setVisible(false);
		setVisible(true);
	}
	
	private void ucitajRecenzije() throws Exception {
		UtisakMenadzer um = sesija.getUtisakMenadzer();
		TableModelWrapper tmw = um.getTabelaRecenzija();
		recenzije.setModel(tmw);
	}
	
	private void zakazi() throws ParseException {
		String msg = validirajZakazanuRecenziju();
		if (!msg.equals("")) {
			JOptionPane.showMessageDialog(null, msg);
			return;
		}
		int selektovaniRed = recenzije.getSelectedRow();
		String sRecenzija = (String)recenzije.getValueAt(selektovaniRed, 0);
		Recenzija recenzija = null;
		for (Recenzija r : sesija.getRecenzije()) {
			if (r.getNaslov().equals(sRecenzija)) {
				recenzija = r;
				break;
			}
		}
		String txtDatumZakazivanja = dtDor.getJFormattedTextField().getText();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy.");
		String txtDatumZakazivanja2 = sdf2.format(sdf1.parse(txtDatumZakazivanja));
		String txtRok = dtDod.getJFormattedTextField().getText();
		String txtRok2 = sdf2.format(sdf1.parse(txtRok));
		String opis = txtOpis.getText();
		ZakazanaRecenzija zakazanaRecenzija = new ZakazanaRecenzija(new SimpleDateFormat("dd.MM.yyyy.").parse(txtDatumZakazivanja2), 
				opis, false, new SimpleDateFormat("dd.MM.yyyy.").parse(txtRok2), recenzija, recenzija.getUrednik());
		ZakazanaRecenzijaMenadzer zrm = sesija.getZakazanaRecenzijaMenadzer();
		zrm.getSve().add(zakazanaRecenzija);
		sesija.setZakazanaRecenzijaMenadzer(zrm);
	}
	
	private String validirajZakazanuRecenziju() {
		if (recenzije.getSelectionModel().isSelectionEmpty()) {
			return "Morate odabrati recenziju.";			
		}
		if (dtDor.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati datum zakazivanja";
		}
		if (dtDod.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati rok";
		}
		return "";
	}
	
	private void zahtevajIzmenu() {
		int selektovaniRed = recenzije.getSelectedRow();
		if (recenzije.getSelectionModel().isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Morate odabrati recenziju.");
			return;			
		}
		String sRecenzija = (String)recenzije.getValueAt(selektovaniRed, 0);
		Recenzija recenzija = null;
		for (Recenzija r : sesija.getRecenzije()) {
			if (r.getNaslov().equals(sRecenzija)) {
				recenzija = r;
				break;
			}
		}
		boolean brisanje = false;
		boolean menjanje = false;
		if (rbIzmena.isSelected()) {
			menjanje = true;
		}
		else {
			brisanje = true;
		}
		String poruka = txtPoruka.getText();
		RecezijaZaIzmenu recenzijaZaIzmenu = new RecezijaZaIzmenu(menjanje, brisanje, recenzija, false, poruka);
		RecenzijeZaIzmenuMenadzer rzim = sesija.getRecenzijeZaIzmenuMenadzer();
		rzim.getSveizmene().add(recenzijaZaIzmenu);
		sesija.setRecenzijeZaIzmenuMenadzer(rzim);
	}
}
