package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;

import model.MuzickiSadrzaj;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class TopListeProzor extends MojDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel base, base2, base3;
	private JTextField nazivTf;
	private JButton kreiraj;
	
	private ImageIcon searchI = new ImageIcon("slike/search.jpg");
	private ImageIcon scaledS = new ImageIcon(searchI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon refreshI = new ImageIcon("slike/refresh.jpg");
	private ImageIcon scaledR = new ImageIcon(refreshI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	
	private ImageIcon addI = new ImageIcon("slike/add.gif");
	private JButton btnAdd = new JButton(addI);
	private ComboZanr cz1;
	private List<Zanr> izabraniZanrovi1;
	private JCheckBox albumCB1, muzickoCB1;
	private JButton btnSearch1 = new JButton(scaledS);
	private JButton btnRefresh1 = new JButton(scaledR);
	private JXTable table1;
	
	private ImageIcon deleteI = new ImageIcon("slike/remove.gif");
	private JButton btnDelete = new JButton(deleteI);
	private ComboZanr cz2;
	private List<Zanr> izabraniZanrovi2;
	private JCheckBox albumCB2, muzickoCB2;
	private JButton btnSearch2 = new JButton(scaledS);
	private JButton btnRefresh2 = new JButton(scaledR);
	private JTable table2;
	
	private String[] imenaKolona;
	private List<MuzickiSadrzaj> muzickiSadrzaj;
	private List<Zanr> zanrovi;
	
	public TopListeProzor(JFrame parent, String naziv, int dim1, int dim2,
			List<MuzickiSadrzaj> muzickiSadrzaj, String[] imenaKolona) {
		super(parent, naziv, dim1, dim2);
		
		this.imenaKolona = imenaKolona;
		this.muzickiSadrzaj = muzickiSadrzaj;
		this.zanrovi = ((Homepage)parent).getSesija().getZanroviMenadzer().vratiAktivneZanrove();
		
		this.setLayout(new BorderLayout());
		
		
		this.initGUI();
		this.actionGUI();
		this.pack();
	}

	private void initGUI() {
		base = new JPanel(new MigLayout("wrap 3", "[]10[]10[]", "[]"));
		JLabel nazivLb = new JLabel("Unesi naziv:");
		base.add(nazivLb);
		nazivTf = new JTextField(10);
		base.add(nazivTf);
		kreiraj = new JButton("Kreiraj");
		base.add(kreiraj);
		this.add(base, BorderLayout.NORTH);
		
		base2 = new JPanel(new BorderLayout());
		JPanel dugmici1 = new JPanel(new MigLayout("wrap 6", "[]10[]10[]10[]10[]10[]", "[]"));
		dugmici1.add(btnAdd);
		cz1 = new ComboZanr(); // treba napuniti kombo boxove
		cz1.kreirajSadrzaj(zanrovi);
		dugmici1.add(cz1);
		albumCB1 = new JCheckBox("Album"); muzickoCB1 = new JCheckBox("Muzicko Delo");
		dugmici1.add(albumCB1);
		dugmici1.add(muzickoCB1);
		dugmici1.add(btnSearch1);
		dugmici1.add(btnRefresh1);
		base2.add(dugmici1, BorderLayout.NORTH);
		table1 = new JXTable(new MuzickiSadrzajModel(imenaKolona, muzickiSadrzaj, null));
		table1.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp1 = new JScrollPane(table1);
		base2.add(sp1, BorderLayout.CENTER);
		this.add(base2, BorderLayout.WEST);
		
		base3 = new JPanel(new BorderLayout());
		JPanel dugmici2 = new JPanel(new MigLayout("wrap 6", "[]10[]10[]10[]10[]10[]", "[]"));
		dugmici2.add(btnDelete);
		cz2 = new ComboZanr(); // treba napuniti kombo boxove
		cz2.kreirajSadrzaj(zanrovi);
		dugmici2.add(cz2);
		albumCB2 = new JCheckBox("Album"); muzickoCB2 = new JCheckBox("Muzicko Delo");
		dugmici2.add(albumCB2);
		dugmici2.add(muzickoCB2);
		dugmici2.add(btnSearch2);
		dugmici2.add(btnRefresh2);
		base3.add(dugmici2, BorderLayout.NORTH); // na ovo se vratiti 
		table2 = new JTable(new MuzickiSadrzajModel(imenaKolona, new ArrayList<>(), null));
		table2.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp2 = new JScrollPane(table2);
		base3.add(sp2, BorderLayout.CENTER);
		this.add(base3, BorderLayout.EAST);
		
	}
	
	private void actionGUI() {
		kreiraj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
