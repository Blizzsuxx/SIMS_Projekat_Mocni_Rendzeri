package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;

public class AdvancedSearchDialog extends MojDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private void initGui() {
		// TODO Auto-generated method stub
		
		JXSearchField search = new JXSearchField();
		search.setSearchMode(SearchMode.REGULAR);
		search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////////
				// TODO ODRADI SEARCH OVDE //
				/////////////////////////////
				// Seach se poziva preko klika na lupu ili preko pritiska na enter
				// to je vec namesteno, jos samo da se search mehanika implementira
				searchTriggered(arg0.getActionCommand());
			}

		});
		
		this.add(search, "north");
		
		
		
		
	}

	private void searchTriggered(String actionCommand) {
		// TODO Auto-generated method stub
		
	}

	public AdvancedSearchDialog(JFrame parent, int dimension1, int dimension2) {
		super(parent, "Napredna pretraga", dimension1, dimension2);
		initGui();
		// TODO Auto-generated constructor stub
	}


	public AdvancedSearchDialog(JFrame parent) {
		super(parent, "Napredna pretraga");
		initGui();
		// TODO Auto-generated constructor stub
	}

	public AdvancedSearchDialog(int dimension1, int dimension2) {
		this(null, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

}
