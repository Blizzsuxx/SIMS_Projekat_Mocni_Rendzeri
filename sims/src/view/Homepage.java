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
		JMenuBar menubar = new JMenuBar();
		JMenuItem profil = new JMenuItem("Profil");
		JMenuItem odjava = new JMenuItem("Odjava");
		menu.add(profil);
		menu.add(odjava);
		menubar.add(menu);
		

		
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
				System.out.println(arg0.getActionCommand());
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
		
		
	}
}
