package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;

import controler.Constants;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public abstract class Homepage extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;

	public Homepage(Sesija sesija) {
		this.getContentPane().setLayout(new MigLayout());
		initGui();
		this.sesija = sesija;
	}



	private void initGui(){
		this.getContentPane().setBackground(Color.BLACK);
		this.setSize(550, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);

		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);

		JMenu menu = new JMenu("Meni");
		menu.setBackground(Color.BLACK);
		menu.setForeground(Color.WHITE);
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(Color.BLACK);
		menubar.setForeground(Color.WHITE);
		JMenuItem profil = new JMenuItem("Profil");
		profil.setBackground(Color.BLACK);
		profil.setForeground(Color.WHITE);
		JMenuItem odjava = new JMenuItem("Odjava");
		odjava.setBackground(Color.BLACK);
		odjava.setForeground(Color.WHITE);
		menu.add(profil);
		menu.add(odjava);
		menubar.add(menu);
		

		
		JXSearchField search = new JXSearchField();
		search.setSearchMode(SearchMode.REGULAR);
		search.setCaretColor(Color.WHITE);
		search.setBackground(Color.BLACK);
		search.setForeground(Color.WHITE);
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

		profil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuProfilTriggered();
			}
		});

		odjava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuOdjavaTriggered();
			}
		});

		this.setJMenuBar(menubar);
	}

	public Sesija getSesija() {
		return sesija;
	}

	private void menuOdjavaTriggered() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	private void menuProfilTriggered() {
		// TODO Ovde pisi kod za pregled profila
		// Koristi DiajlogKorisnickihInformacija ako mozes nekako
		// Ili Editor builder
		
		//DUMMY - Hardcoded KorisnikAplikacije
		DijalogKorisnickihInformacija dki = new DijalogKorisnickihInformacija(this, Constants.DUMMY);
		dki.setVisible(true);
		
	}
	
	
	private void searchTriggered(String textZaSearch) {
		SearchResults rezultati = new SearchResults(this, sesija.getMuzickoDeloMenadzer().trazi(textZaSearch));
		rezultati.setVisible(true);
	}
}
