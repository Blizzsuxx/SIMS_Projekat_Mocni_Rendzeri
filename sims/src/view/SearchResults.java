package view;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.MuzickoDelo;

public class SearchResults extends MojDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private void initGui(ArrayList<Slikovit> prikaz) {
		for(Slikovit sadrzaj : prikaz) {
			ImageLabel slika = null;
			if(sadrzaj instanceof MuzickoDelo) {
				slika = new MuzickoDeloLabel(sadrzaj);
			} else {
				slika = new ImageLabel(sadrzaj);
			}
			this.add(slika, "wrap 20");
		}
	}

	public SearchResults(JFrame parent, int dimension1, int dimension2, ArrayList<Slikovit> prikaz) {
		super(parent, "Rezultat pretrage", dimension1, dimension2);
		initGui(prikaz);
		// TODO Auto-generated constructor stub
	}

	public SearchResults(JFrame parent, ArrayList<Slikovit> prikaz) {
		super(parent, "Rezultat pretrage");
		initGui(prikaz);
		// TODO Auto-generated constructor stub
	}

	public SearchResults(int dimension1, int dimension2, ArrayList<Slikovit> prikaz) {
		super("Rezultat pretrage", dimension1, dimension2);
		initGui(prikaz);
		// TODO Auto-generated constructor stub
	}

}
