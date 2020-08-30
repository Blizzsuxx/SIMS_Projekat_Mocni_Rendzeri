package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.MuzickoDelo;

public class MuzickaDelaModel extends AbstractTableModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String[] naslovi = { "Naziv", "Datum izdavanja", "Prosecna ocena urednika", "Prosecna ocena korisnika" };
	ArrayList<MuzickoDelo> muzickaDela;
	
	
	public MuzickaDelaModel(ArrayList<MuzickoDelo> muzickaDela) {
		super();
		this.muzickaDela = muzickaDela;
	}

	@Override
	public int getRowCount() {
		
		return muzickaDela.size();
	}

	@Override
	public int getColumnCount() {
		
		return naslovi.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MuzickoDelo m=muzickaDela.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return m.getNaziv();
		case 1: return m.getDatumIzdavanja();
		case 2: return m.getProsecnaOcenaUrednika()+"";
		case 3: return m.getProsecnaOcenaKorisnika()+"";
		default:
			break;
		}
		return null;
	}

}
