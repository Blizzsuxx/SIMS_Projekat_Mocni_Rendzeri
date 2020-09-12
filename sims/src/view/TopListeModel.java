package view;

import java.util.List;

import model.TopLista;

public class TopListeModel extends ApstraktniModel {
	private static final long serialVersionUID = 1L;

	private List<TopLista> topListe;
	
	public TopListeModel(String[] columnNames, List<TopLista> topListe) {
		super(columnNames);
		this.topListe = topListe;
	}

	@Override
	public int getRowCount() {
		return topListe.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		TopLista tp = this.topListe.get(row);
		if (col == 0) {
			temp = tp.getNaziv();
		} else if (col == 1) {
			temp = tp.getKorisnik().getNalog().getKorisnickoIme();
		} else if (col == 2) {
			temp = tp.getMuzickiSadrzaj().size();
		} else if (col == 3) {
			temp = tp.isStatus();
		}
		return temp;
	}

}
