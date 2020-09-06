package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.MuzickoDelo;
import net.miginfocom.swing.MigLayout;

public class SearchResults extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JPanel initGui(ArrayList<Slikovit> prikaz) {
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
			} else {
				slika = new ImageLabel(sadrzaj);
			}
			
			content.add(slika, "wrap 20");
		}
		return content;
	}
	
	
	public SearchResults(ArrayList<Slikovit> prikaz) {
		super(initGui(prikaz));
	}

	

}
