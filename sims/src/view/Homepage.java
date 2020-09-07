package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controler.Constants;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public abstract class Homepage extends JFrame {
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	protected JMenu menu;
	protected JMenuBar menubar;
	
	public Homepage(Sesija sesija) {
		this.getContentPane().setLayout(new MigLayout());
		this.sesija = sesija;
		initGui();
	}



	private void initGui(){
		this.setSize(550, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);

		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);

		menu = new JMenu("Meni");
		menubar = new JMenuBar();
		JMenuItem profil = new JMenuItem("Profil");
		JMenuItem odjava = new JMenuItem("Odjava");
		menu.add(profil);
		menu.add(odjava);
		menubar.add(menu);
		

		
		SearchField search = new SearchField(sesija, this);
		
		
		
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
		
		//DUMMY - Hardcoded KorisnikAplikacije
		DijalogRadSaNalogom radSaNalogom = new DijalogRadSaNalogom(this, Constants.DUMMY, "Rad sa nalogom");
		radSaNalogom.setVisible(true);
	}

}
