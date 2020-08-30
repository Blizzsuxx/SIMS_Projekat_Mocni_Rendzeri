package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.MuzickoDjelo;

public class MuzickaDelaModel extends AbstractTableModel {
	String[] naslovi= {"Naziv", "Datum izdavanja", "Prosecna ocena urednika", "Prosecna ocena korisnika", "Dodaj"};
	ArrayList<MuzickoDjelo> muzickaDela;
	int br;
	
	public MuzickaDelaModel(ArrayList<MuzickoDjelo> muzickaDela, int br) {
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
		MuzickoDjelo m=muzickaDela.get(rowIndex);
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
     public Class getColumnClass(int column) {
		 switch (column) {
         case 0:
             return String.class;
         case 1:
             return String.class;
         case 2:
             return String.class;
         case 3:
             return String.class;
         case 4: 
        	 return Boolean.class;
         default:
             return Boolean.class;
     }
     }
	 
	 public MuzickoDjelo getDelo(int row) {
		 return muzickaDela.get(row);
		 
	 }
}
