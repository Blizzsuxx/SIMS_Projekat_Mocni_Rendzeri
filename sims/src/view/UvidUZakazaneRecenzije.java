package view;

import javax.swing.JFrame;
import javax.swing.JTable;

import controler.KorisniciMenadzer;
import controler.UtisakMenadzer;
import controler.ZakazanaRecenzijaMenadzer;
import model.Recenzija;
import model.Sesija;
import model.Urednik;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UvidUZakazaneRecenzije extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable zakazaneRecenzije;
	private Sesija sesija;
	
	public UvidUZakazaneRecenzije(Sesija sesija) throws Exception {
		this.sesija = sesija;
		setTitle("Uvid u zakazane recenzije");
		setResizable(false);
		getContentPane().setLayout(null);
		
		zakazaneRecenzije = new JTable();
		zakazaneRecenzije.setBounds(10, 11, 427, 289);
		getContentPane().add(zakazaneRecenzije);
		
		JButton btnIzmeni = new JButton("Izmeni");
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!zakazaneRecenzije.getSelectionModel().isSelectionEmpty())
					izmeni();
			}
		});
		btnIzmeni.setBounds(348, 347, 89, 23);
		getContentPane().add(btnIzmeni);
		
		ucitajZakazeneRecenzije();
	}
	
	private void ucitajZakazeneRecenzije() throws Exception {
		ZakazanaRecenzijaMenadzer zrm = sesija.getZakazanaRecenzijaMenadzer();
		TableModelWrapper tmw = zrm.getTabelaZakazanihRecenzijaZaUrednika((Urednik)Sesija.getTrenutniKorisnik());
		zakazaneRecenzije.setModel(tmw);
	}
	
	private void izmeni() {
		String naslov = (String)zakazaneRecenzije.getValueAt(zakazaneRecenzije.getSelectedRow(), 0);
		UtisakMenadzer um = sesija.getUtisakMenadzer();
		Recenzija recenzija = null;
		for (Recenzija r : um.getRecenzije()) {
			if (r.getNaslov().equals(naslov)) {
				recenzija = r;
				break;
			}
		}
		if (recenzija != null) {
			new DodavanjeRecenzije(sesija, recenzija);
			Urednik urednik = (Urednik)Sesija.getTrenutniKorisnik();
			KorisniciMenadzer km = sesija.getKorisnici();
			km.getKorisnici().replace(urednik.getNalog().getKorisnickoIme(), urednik);
			sesija.setKorisnici(km);
		}
	}
}
