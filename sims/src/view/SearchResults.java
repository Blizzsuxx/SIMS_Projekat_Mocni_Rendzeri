package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Album;
import model.Izvodjac;
import model.MuzickoDelo;
import model.Urednik;
import net.miginfocom.swing.MigLayout;

public class SearchResults extends JScrollPane {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	
	public SearchResults(Collection<Slikovit> prikaz) {
		super(initGui(prikaz));
	}

	public static JPanel initGui(Collection<Slikovit> prikaz) {
		
		JPanel content = new JPanel(new MigLayout());
		for(Slikovit sadrzaj : prikaz) {
			JPanel slika = null;
			if(sadrzaj instanceof MuzickoDelo) {
				slika = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JPanel deoSaSlikom = new MuzickoDeloLabel(sadrzaj);
				slika.add(deoSaSlikom);
				JPanel deoSaOpisom = new JPanel(new BorderLayout());
				StarRater rater = new StarRater(5, 3, 3);
				rater.setEnabled(false);
				deoSaOpisom.add(rater, BorderLayout.NORTH);
				JTextArea opis = new JTextArea(((MuzickoDelo) sadrzaj).getOpis());
				opis.setEditable(false);
				deoSaOpisom.add(opis, BorderLayout.CENTER);
				slika.add(deoSaOpisom);
			} else if (sadrzaj instanceof Album) {
				slika = new ImageLabel(sadrzaj){
					@Override
					protected void clickedEvent() {
						// TODO Auto-generated method stub
						AlbumInfo ai =  new AlbumInfo(null, "Album", 100, 100, (Album)sadrzaj);
		   				ai.setVisible(true);
					}
				};
			} else if(sadrzaj instanceof Izvodjac){
				slika = new IzvodjacLabel(sadrzaj);
			} else if(sadrzaj instanceof Urednik){
				slika = new ImageLabel(sadrzaj){
					@Override
					protected void clickedEvent() {
						// TODO Auto-generated method stub
						DijalogRadSaNalogom gledajUrednika = new DijalogRadSaNalogom(null, (Urednik) sadrzaj, sadrzaj.Ime(), true);
						gledajUrednika.setVisible(true);
					}
				};
			} else {
				slika = new ImageLabel(sadrzaj);
			}
			
			content.add(slika, "wrap 20");
		}
		return content;
	}
	
	
	
	

}
