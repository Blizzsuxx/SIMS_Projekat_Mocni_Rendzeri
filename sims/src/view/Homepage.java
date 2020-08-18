package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Sesija;

public abstract class Homepage extends JFrame {
	
	private Sesija sesija;
	public Homepage(Sesija sesija) {
		this.setSize(450, 450);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		// Za centriranje dijaloga
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
		this.sesija = sesija;
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
