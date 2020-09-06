package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;

public class AdvancedSearchDialog extends MojDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private void initGui() {
		// TODO Auto-generated method stub
		this.setLayout(new BorderLayout());
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
		
		this.add(search, BorderLayout.NORTH);
		
		final JPopupMenu menu = new JPopupMenu();
		menu.add(new JCheckBoxMenuItem("Other Court"));
		menu.add(new JCheckBoxMenuItem("Tribunal Court"));
		menu.add(new JCheckBoxMenuItem("High Court"));
		menu.add(new JCheckBoxMenuItem("Supreme Court"));

		final JButton button = new JButton();
		button.setAction(new AbstractAction("Court") {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        menu.show(button, 0, button.getHeight());
		    }
		});
		
		add(button);
		
		
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
