package view;

import java.util.List;

import model.Izvodjac;

public class IzvodjaciModel extends ApstraktniModel {
	private static final long serialVersionUID = 1L;

	private List<Izvodjac> izvodjaci;
	
	public IzvodjaciModel(String[] columnNames, List<Izvodjac> izvodjaci) {
		super(columnNames);
		this.izvodjaci = izvodjaci;
	}

	@Override
	public int getRowCount() {
		return this.izvodjaci.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		Izvodjac i = izvodjaci.get(row);
		if (col == 0) {
			temp = i.getUmetnickoIme();
		} else if (col == 1) {
			temp = i.getZanr().getNazivZanra();
		} else if (col == 2) {
			temp = i.getIzdatiAlbumi().size();
		} else if (col == 3) {
			temp = i.getMuzickaDela().size();
		} else if (col == 4) {
			temp = i.isOdobrenost();
		}
		return temp;
	}

}
