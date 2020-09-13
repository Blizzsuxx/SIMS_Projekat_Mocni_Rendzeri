package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Album;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class AlbumInfo extends MojDialog {
	private static final long serialVersionUID = 1L;

	private Album album;
	private JPanel osnova, zaSliku;
	private JComboBox<String> comboZanrova;
	private JButton nazad;
	
	private ImageIcon sadrzajS = new ImageIcon("slike/music.png");
	private ImageIcon sadrzajI = new ImageIcon(sadrzajS.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private JButton btnSadrzaj = new JButton(sadrzajI);
	
	public AlbumInfo(JFrame parent, String ime, int dimension1, int dimension2, Album album) {
		super(parent, ime, dimension1, dimension2);
		this.album = album;
		
		this.setLayout(new BorderLayout());
		
		this.initGUI();
		this.actionGUI();
		this.pack();
	}

	private void initGUI() {
		zaSliku = new JPanel();
		ImageIcon image = new ImageIcon("slike/album.jpg");
		Image scaleImage = image.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
		ImageIcon newIcon = new ImageIcon(scaleImage);
		JLabel label = new JLabel(newIcon);
		zaSliku.add(label);
		this.add(zaSliku, BorderLayout.NORTH);
		
		osnova = new JPanel(new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]10[]10[]10[]"));
		osnova.add(new JLabel("Naslov:")); //1
		osnova.add(new JLabel(album.getNaslov()));
		osnova.add(new JLabel("Opis:")); // 2
		osnova.add(new JLabel(album.getOpis()));
		osnova.add(new JLabel("Datum Izdavanja:")); // 3
		osnova.add(new JLabel(album.getDatumIzadavanja().toString()));
		osnova.add(new JLabel("Izvodjac:")); // 4
		osnova.add(new JLabel(album.getIzvodjac().getUmetnickoIme()));
		osnova.add(new JLabel("Urednik:")); // 5
		osnova.add(new JLabel(album.getUrednik().getNalog().getKorisnickoIme()));
		osnova.add(new JLabel("Zanrovi:")); // 6
		List<String> temp = new ArrayList<>();
		for (Zanr z: album.getZanrovi())
			temp.add(z.getNazivZanra());
		comboZanrova = new JComboBox<String>(new Vector<String>(temp));
		osnova.add(comboZanrova);
		osnova.add(new JLabel("Muzicka Dela:")); // 7
		osnova.add(btnSadrzaj);
		osnova.add(new JLabel()); // 8
		nazad = new JButton("Nazad");
		osnova.add(nazad);
		this.add(osnova, BorderLayout.CENTER);
	}
	
	private void actionGUI() {
		nazad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AlbumInfo.this.dispose();
				
			}
			
		});
		
		btnSadrzaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MojDialog md = new MojDialog("Sadrzaj Albuma", 500, 400);
				@SuppressWarnings("unchecked")
				SearchResults sr = new SearchResults( (List<Slikovit>)(List<?>)album.getListaPesama());
				JScrollPane sp = new JScrollPane(sr);
				md.setContentPane(sp);
				
				md.setVisible(true);
				
			}
			
		});
	}
	
}
