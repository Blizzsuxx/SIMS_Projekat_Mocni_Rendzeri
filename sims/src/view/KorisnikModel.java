package view;

import java.util.List;

import model.Administrator;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Uloga;
import model.Urednik;

public class KorisnikModel extends ApstraktniModel {
	private static final long serialVersionUID = 1L;

	private List<Korisnik> korisnici;
	private Uloga indikator;
	
	public KorisnikModel(String[] columnNames, List<Korisnik> korisnici, Uloga indikator) {
		super(columnNames);
		this.korisnici = korisnici;
		this.indikator = indikator;
		
	}

	@Override
	public int getRowCount() {
		return this.korisnici.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		Korisnik obj = korisnici.get(row);
		if (col == 0) {
			temp = obj.getNalog().getKorisnickoIme();
		} else if (col == 1) {
			temp = obj.getIme();
		} else if (col == 2) {
			temp = obj.getPrezime();
		} else if (col == 3) {
			temp = obj.geteMail();
		} else if (col == 4) {
			temp = obj.getPol();
		} else if (col == 5) {
			temp = obj.getDatumRodjenja();
		}
		if (indikator == Uloga.KORISNIK) {
			if (col == 6) {
				temp = ((KorisnikAplikacije)obj).getPratite().size();
			} else if (col == 7) {
				temp = ((KorisnikAplikacije)obj).getOnajKogaPrati().size();
			}
		} else if (indikator == Uloga.ADMIN) {
			if (col == 6) {
				temp = ((Administrator)obj).getZakazaneRecenzije().size();
			} else if (col == 7) {
				temp = ((Administrator)obj).getRecenzijeZaIzmene().size();
			}
		} else if (indikator == Uloga.UREDNIK) {
			if (col == 6) {
				temp = ((Urednik)obj).getIstorijaRecenzija().size();
			}
		}
		return temp;
	}

}
