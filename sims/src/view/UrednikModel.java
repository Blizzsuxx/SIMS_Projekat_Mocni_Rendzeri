package view;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import model.PodaciUrednikaZaIzvestaj;
import model.Urednik;

public class UrednikModel extends AbstractTableModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String[] naslovi = { "Ime urednika", "Broj recenzija", "Broj zadatih uradenih recenzija",
			"Broj recenzija za izmenu" };
	private ArrayList<Urednik> urednici;
	private ArrayList<PodaciUrednikaZaIzvestaj> izvestaj;
	
	public UrednikModel(Collection<Urednik> urednici) {
		this.urednici=(ArrayList<Urednik>) urednici;
		this.izvestaj=null;
	}
	
	public UrednikModel(ArrayList<PodaciUrednikaZaIzvestaj> izvestaj) {
		super();
		this.izvestaj = izvestaj;
		this.urednici=null;
	}


	@Override
	public int getRowCount() {
		
		return urednici.size();
	}

	@Override
	public int getColumnCount() {
		
		return naslovi.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(izvestaj==null) {
			
		
    Urednik s=((ArrayList<Urednik>)urednici).get(rowIndex); 
		
		switch (columnIndex) {
		case 0:
			return s.getIme()+" "+s.getPrezime();
		case 1:
			return s.getIstorijaRecenzija().size()+"";
		case 2:
			return s.getZakazaneRecenzije().size()+"";
		case 3:
			return s.getRecezijaZaIzmenu().size()+"";
		default:
			break;
		}
		return null;}
		else {
			PodaciUrednikaZaIzvestaj p=izvestaj.get(rowIndex);
			switch(columnIndex) {
			case 0:
				return p.getIme();
			case 1:
				return p.getBrojRecenzija();
			case 2:
				return p.getBrojZadatihRecenzija();
			case 3:
				return p.getBrojZaIzmenu();
			default:
				break;
			}return null;
			
		}
		
	}

}
