package view;

import java.util.List;

import javax.swing.JFrame;

import model.Izvodjac;

public class IzvodjaciProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	List<Izvodjac> izvodjaci;
	
	public IzvodjaciProzor(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

	public IzvodjaciProzor(JFrame parent, String ime, int dim1, int dim2, 
			List<Izvodjac> izvodjaci) {
		super(ime, dim1, dim2);
		this.izvodjaci = izvodjaci;
		
		this.initGUI();
	}
	
	private void initGUI() {
		
	}
}
