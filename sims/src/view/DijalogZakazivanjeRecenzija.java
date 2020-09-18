package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTextArea;

import controler.Constants;
import controler.KorisniciMenadzer;
import model.Korisnik;
import model.MuzickiSadrzaj;
import model.MuzickoDelo;
import model.Recenzija;
import model.Sesija;
import model.TipMuzickogSadrzaja;
import model.Urednik;
import model.ZakazanaRecenzija;
import net.miginfocom.swing.MigLayout;

public class DijalogZakazivanjeRecenzija extends MojDialog {

	private static final long serialVersionUID = 1L;
	
	private Sesija sesija;
	private List<MuzickiSadrzaj> muzickiSadrzaj;
	private String[] imenaKolona;
	private TipMuzickogSadrzaja indikator;
	
	//private JFrame parent;
	private JXTable table;
	private JXDatePicker datePicker;
	private JButton dugmeZakazi;
	private JXTextArea poljeOpis;
	private JComboBox cmbUrednici;

	public DijalogZakazivanjeRecenzija(JFrame parent, String naziv) {
		super(parent, naziv);
	}

	public DijalogZakazivanjeRecenzija(String ime, int dimension1, int dimension2) {
		super(ime, dimension1, dimension2);
	}
	
	public DijalogZakazivanjeRecenzija(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
	}
	
	public DijalogZakazivanjeRecenzija(JFrame parent, String ime, int dimension1, int dimension2, Sesija sesija,
			List<MuzickiSadrzaj> muzickiSadrzaj, String[] imenaKolona, TipMuzickogSadrzaja indikator)
	{
		super(parent, ime, dimension1, dimension2);
		//this.parent = parent;
		this.sesija = sesija;
		this.muzickiSadrzaj = muzickiSadrzaj;
		this.imenaKolona = imenaKolona;
		this.indikator = indikator;
		
		this.setLayout(new BorderLayout());
		
		initGui();
		setListeners();
	}


	@SuppressWarnings("rawtypes")
	private void initGui() {
		table = new JXTable(new MuzickiSadrzajModel(imenaKolona, muzickiSadrzaj, indikator));
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		
		JPanel panelIspod = new JPanel(new MigLayout());
		JPanel panelSkrozIspod = new JPanel(new MigLayout());
		
		poljeOpis = new JXTextArea("Opis zadatka/napomene");
		poljeOpis.setColumns(70);
		panelIspod.add(poljeOpis, "wrap");
		
		
		datePicker = new JXDatePicker();
		datePicker.setFormats(Constants.FORMAT_ZA_DATUM);
		panelSkrozIspod.add(new JLabel("Rok za kreiranje"));
		panelSkrozIspod.add(datePicker);
		
		cmbUrednici = new JComboBox();
		cmbUrednici.setMaximumRowCount(20);
		ucitajUrednike();
		panelSkrozIspod.add(new JLabel("Urednik"));
		panelSkrozIspod.add(cmbUrednici, "wrap");
	
		dugmeZakazi = new JButton("Zakazi recenziju");
		panelSkrozIspod.add(dugmeZakazi);
		
		panelIspod.add(panelSkrozIspod);
		this.add(panelIspod, BorderLayout.SOUTH);
	}
	
	private void setListeners() {
		dugmeZakazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) 
				{
					JOptionPane.showMessageDialog(DijalogZakazivanjeRecenzija.this, "Izaberite muzicko delo", "Muzicko delo", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(datePicker.getEditor().getText().equals(""))
				{
					JOptionPane.showMessageDialog(DijalogZakazivanjeRecenzija.this, "Izaberite rok za izradu", "Rok", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				MuzickoDelo delo = (MuzickoDelo)muzickiSadrzaj.get(table.getSelectedRow());
				ZakazanaRecenzija zakazanaRecenzija = new ZakazanaRecenzija(new Date(), poljeOpis.getText(), false, datePicker.getDate(),
						new Recenzija(null, "", delo), (Urednik)sesija.getKorisnici().trazi(cmbUrednici.getSelectedItem().toString()));
				sesija.getZakazanaRecenzijaMenadzer().getSve().add(zakazanaRecenzija);
				poljeOpis.setText("");
				datePicker.getEditor().setText("");
				JOptionPane.showMessageDialog(DijalogZakazivanjeRecenzija.this, "Uspesno ste zakazali recenziju", "Recenzija", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		
	}
	
	@SuppressWarnings("unchecked")
	private void ucitajUrednike() {
		KorisniciMenadzer km = sesija.getKorisnici();
		HashMap<String,Korisnik> korisnici = km.getKorisnici();
		Iterator<Entry<String, Korisnik>> it = korisnici.entrySet().iterator();
		while (it.hasNext()) 
		{
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Korisnik k = (Korisnik)pair.getValue();
			if (k.getClass() == Urednik.class) {
				cmbUrednici.addItem(k.getNalog().getKorisnickoIme());
			}
	    }
	}

}
