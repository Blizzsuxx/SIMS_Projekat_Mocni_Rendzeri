package view;

import java.util.List;

import model.Album;
import model.MuzickiSadrzaj;
import model.MuzickoDelo;
import model.TipMuzickogSadrzaja;

public class MuzickiSadrzajModel extends ApstraktniModel {
	private static final long serialVersionUID = 1L;

	private List<MuzickiSadrzaj> muzickiSadrzaj;
	private TipMuzickogSadrzaja indikator;
	
	public MuzickiSadrzajModel(String[] columnNames, List<MuzickiSadrzaj> muzickiSadrzaj, 
			TipMuzickogSadrzaja indikator) {
		super(columnNames);
		this.muzickiSadrzaj = muzickiSadrzaj;
		this.indikator = indikator;
	}

	@Override
	public int getRowCount() {
		return this.muzickiSadrzaj.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		MuzickiSadrzaj ms = muzickiSadrzaj.get(row);
		if (col == 0) {
			temp = ms.getNaslov();
		} else if (col == 1) {
			temp = ms.getOpis();
		} else if (col == 2) {
			temp = ms.getDatumIzadavanja();
		} else if (col == 3) {
			temp = ms.getIzvodjac().getUmetnickoIme();
		} else if (col == 4) {
			temp = ms.getUrednik().getNalog().getKorisnickoIme();
		}
		if (indikator == TipMuzickogSadrzaja.ALBUM) {
			if (col == 5)
				temp = ((Album)ms).isOdobreno();
		} else if (indikator == TipMuzickogSadrzaja.MUZICKO_DELO) {
			if (col == 5) {
				temp = ((MuzickoDelo)ms).getProsecnaOcenaKorisnika();
			} else if (col == 6) {
				temp = ((MuzickoDelo)ms).getProsecnaOcenaUrednika();
			}
		}
		return temp;
	}

}
