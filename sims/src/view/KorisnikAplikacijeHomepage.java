package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controler.Constants;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public class KorisnikAplikacijeHomepage extends Homepage {

	private List<MuzickoDeloLabel> trending;
	private List<MuzickoDeloLabel> pratite;
	private List<MuzickoDeloLabel> preporucujemo;
	public KorisnikAplikacijeHomepage(Sesija sesija) {
		super(sesija);
		trending = new ArrayList<>();
		pratite = new ArrayList<>();
		preporucujemo = new ArrayList<>();

		JLabel trendingl = new JLabel("U trendu");
		trendingl.setForeground(Color.WHITE);
		this.add(trendingl, "wrap, gaptop 20, align center");
		initRow(trending, 10);

		JLabel pratitel = new JLabel("Pratite");
		pratitel.setForeground(Color.WHITE);
		this.add(pratitel, "wrap, gaptop 20, align center");
		initRow(pratite, 5);

		JLabel preporucujemol = new JLabel("Preporucujemo");
		preporucujemol.setForeground(Color.WHITE);
		this.add(preporucujemol, "wrap, gaptop 20, align center");
		initRow(preporucujemo, 5);
		
	}

	private void initRow(List<MuzickoDeloLabel> lista, int numberOfElements){
		

		JPanel pane = new JPanel(new MigLayout());
		pane.setBackground(Color.BLACK);
		for(int i = 0; i < numberOfElements; ++i){
			MuzickoDeloLabel labela = new MuzickoDeloLabel(Constants.BARBIE_GIRL);
			pane.add(labela, "gapleft 10");
			lista.add(labela);
		}
		JScrollPane scroll = new JScrollPane(pane);
		scroll.getHorizontalScrollBar().setBackground(Color.BLACK);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.add(scroll, "wrap");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920247537466287175L;
	
	

}
