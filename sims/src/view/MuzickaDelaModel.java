package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.MuzickoDelo;

public class MuzickaDelaModel extends AbstractTableModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String[] naslovi = { "Naziv", "Datum izdavanja", "Prosecna ocena urednika", "Prosecna ocena korisnika", "Dodaj" };
	ArrayList<MuzickoDelo> muzickaDela;
	int br;
	
	public MuzickaDelaModel(ArrayList<MuzickoDelo> muzickaDela, int br) {
		super();
		this.muzickaDela = muzickaDela;
		this.br=br;
	}

	@Override
	public int getRowCount() {
		
		return muzickaDela.size();
	}

	@Override
	public int getColumnCount() {
		
		return naslovi.length-br;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MuzickoDelo m=muzickaDela.get(rowIndex);
		switch (columnIndex) {
		case 0: return m.getNaziv();
		case 1: return m.getDatumIzdavanja();
		case 2: return m.getProsecnaOcenaUrednika()+"";
		case 3: return m.getProsecnaOcenaKorisnika()+"";
		case 4: return false;
		default:
			break;
		}
		return null;
	}
	 @Override
     public Class getColumnClass(int columnIndex) {
		 if(columnIndex==4) {
        return Boolean.class;}
		 else {
        	return String.class;
        }
     }

}
