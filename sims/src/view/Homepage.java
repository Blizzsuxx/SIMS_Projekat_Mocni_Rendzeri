package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;

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
		initGui();
		this.sesija = sesija;
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
		

		
		JXSearchField search = new JXSearchField();
		search.setSearchMode(SearchMode.REGULAR);
		search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchTriggered(arg0.getActionCommand());
			}

		});
		
		this.add(search, "north");
		
		JButton advancedSearchButton = new JButton("Napredna pretraga");
		this.add(advancedSearchButton, "north");
		advancedSearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				advancedSearchButtonTriggered();
			}
		});

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

	private void advancedSearchButtonTriggered() {
		// TODO Auto-generated method stub
		AdvancedSearchDialog dialog = new AdvancedSearchDialog(this);
		dialog.setVisible(true);
	}



	public Sesija getSesija() {
		return sesija;
	}

	private void menuOdjavaTriggered() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	private void menuProfilTriggered() {
		//Kod za pregled profila
		
		//DUMMY - Hardcoded KorisnikAplikacije
		if(sesija.getTrenutniKorisnik() == null)
		{
			JOptionPane.showMessageDialog(this, "Da bi ste pregledali svoj profil morate imati nalog.", "Profil", JOptionPane.INFORMATION_MESSAGE);
		}
		else 
		{
			DijalogRadSaNalogom radSaNalogom = new DijalogRadSaNalogom(this, Constants.DUMMY, "Rad sa nalogom");
			radSaNalogom.setVisible(true);
		}	
	}
	
	
	private void searchTriggered(String textZaSearch) {
		SearchResults rezultati = new SearchResults(sesija.getMuzickoDeloMenadzer().trazi(textZaSearch));
		MojDialog dialog = new MojDialog(this, "Rezultati pretrage");
		dialog.setContentPane(rezultati);
		dialog.setVisible(true);
	}
}
