package view;

import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import model.Grupa;

public class GrupeModel extends AbstractTableModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String[] naslovi = { "Izvodjac", "Delo", "Datum izdavanja dela", "Zanr" };

	public GrupeModel(Collection<Grupa> grupe) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		
		return naslovi.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
