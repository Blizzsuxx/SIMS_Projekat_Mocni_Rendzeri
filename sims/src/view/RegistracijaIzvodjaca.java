package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import org.jdatepicker.impl.*;

import model.Grupa;
import model.Pojedinacanizvodjac;
import model.Pol;
import model.Sesija;
import model.Zanr;

import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class RegistracijaIzvodjaca extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUmetnickoIme;
	private JTextField txtIme;
	private JTextField txtPrezime;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbZanr;
	private JPanel pnlGrupa;
	private JDatePickerImpl dtDor;
	private JDatePickerImpl dtDof;
	private JSpinner spnBrojClanova;
	private SpringLayout sl_dtDob;
	private SpringLayout sl_dtDod;
	private SpringLayout sl_dtDof;
	private SpringLayout sl_dtDor;
	public Sesija sesija;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistracijaIzvodjaca(Sesija sesija) throws Exception {
		setResizable(false);
		this.sesija = sesija;
		setTitle("Registracija izvodjaca");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Umetnicko ime:");
		lblNewLabel.setBounds(10, 11, 95, 14);
		getContentPane().add(lblNewLabel);
		
		txtUmetnickoIme = new JTextField();
		txtUmetnickoIme.setBounds(103, 8, 218, 20);
		getContentPane().add(txtUmetnickoIme);
		txtUmetnickoIme.setColumns(10);
		
		
		JPanel pnlPojedinacniIzvodjac = new JPanel();
		JRadioButton rbPojedinacniIzvodjac = new JRadioButton("Pojedinacni izvodjac");
		rbPojedinacniIzvodjac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlPojedinacniIzvodjac.setVisible(true);
				pnlGrupa.setVisible(false);
				
			}
		});
		rbPojedinacniIzvodjac.setSelected(true);
		rbPojedinacniIzvodjac.setBounds(10, 48, 151, 23);
		getContentPane().add(rbPojedinacniIzvodjac);
		
		JRadioButton rbGrupa = new JRadioButton("Grupa");
		rbGrupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlGrupa.setVisible(true);
				pnlPojedinacniIzvodjac.setVisible(false);
			}
		});
		rbGrupa.setBounds(163, 48, 109, 23);
		getContentPane().add(rbGrupa);
		
		pnlPojedinacniIzvodjac.setBounds(10, 78, 306, 234);
		getContentPane().add(pnlPojedinacniIzvodjac);
		pnlPojedinacniIzvodjac.setLayout(null);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(10, 11, 27, 14);
		pnlPojedinacniIzvodjac.add(lblIme);
		
		txtIme = new JTextField();
		txtIme.setBounds(106, 8, 189, 20);
		pnlPojedinacniIzvodjac.add(txtIme);
		txtIme.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(10, 42, 48, 14);
		pnlPojedinacniIzvodjac.add(lblPrezime);
		
		txtPrezime = new JTextField();
		txtPrezime.setBounds(106, 39, 189, 20);
		pnlPojedinacniIzvodjac.add(txtPrezime);
		txtPrezime.setColumns(10);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl dtDob = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtDob.putConstraint(SpringLayout.NORTH, dtDob.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDob);
		sl_dtDob.putConstraint(SpringLayout.WEST, dtDob.getJFormattedTextField(), 33, SpringLayout.WEST, dtDob);
		sl_dtDob.putConstraint(SpringLayout.EAST, dtDob.getJFormattedTextField(), 211, SpringLayout.WEST, dtDob);
		sl_dtDob = (SpringLayout) dtDob.getLayout();
		dtDob.setBounds(106, 75, 189, 25);
		pnlPojedinacniIzvodjac.add(dtDob);
		
		JLabel lblNewLabel_1 = new JLabel("Datum rodjenja");
		lblNewLabel_1.setBounds(10, 81, 75, 14);
		pnlPojedinacniIzvodjac.add(lblNewLabel_1);
		
		JDatePanelImpl date2Panel = new JDatePanelImpl(model, p);
		JDatePickerImpl dtDod = new JDatePickerImpl(date2Panel, new DataLabelFormatter());
		sl_dtDod.putConstraint(SpringLayout.NORTH, dtDod.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDod);
		sl_dtDod.putConstraint(SpringLayout.WEST, dtDod.getJFormattedTextField(), 33, SpringLayout.WEST, dtDod);
		sl_dtDod.putConstraint(SpringLayout.EAST, dtDod.getJFormattedTextField(), 211, SpringLayout.WEST, dtDod);
		sl_dtDod = (SpringLayout) dtDod.getLayout();
		dtDod.setBounds(106, 115, 189, 25);
		pnlPojedinacniIzvodjac.add(dtDod);
		
		JLabel lblNewLabel_2 = new JLabel("Datum smrti");
		lblNewLabel_2.setBounds(10, 122, 75, 14);
		pnlPojedinacniIzvodjac.add(lblNewLabel_2);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(10, 189, 48, 14);
		pnlPojedinacniIzvodjac.add(lblOpis);
		
		JLabel lblPol = new JLabel("Pol");
		lblPol.setBounds(10, 160, 48, 14);
		pnlPojedinacniIzvodjac.add(lblPol);
		
		JRadioButton rbMuski = new JRadioButton("Muski");
		rbMuski.setSelected(true);
		rbMuski.setBounds(101, 156, 61, 23);
		pnlPojedinacniIzvodjac.add(rbMuski);
		
		JRadioButton rbtZenski = new JRadioButton("Zenski");
		rbtZenski.setBounds(178, 156, 67, 23);
		pnlPojedinacniIzvodjac.add(rbtZenski);
		
		JTextArea txtOpis = new JTextArea();
		txtOpis.setColumns(30);
		txtOpis.setWrapStyleWord(true);
		txtOpis.setRows(4);
		txtOpis.setLineWrap(true);
		txtOpis.setBounds(106, 184, 190, 39);
		pnlPojedinacniIzvodjac.add(txtOpis);
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		txtOpis.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		JDatePanelImpl date3Panel = new JDatePanelImpl(model, p);
		
		JDatePanelImpl date4Panel = new JDatePanelImpl(model, p);
		
		JButton btnRegistruj = new JButton("Registruj");
		btnRegistruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUmetnickoIme.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Umetnicko ime je obavezno polje.");
					return;
				}
				if (rbPojedinacniIzvodjac.isSelected())
				{
					if (txtIme.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Ime je obavezno polje.");
						return;
					}
					if (txtPrezime.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Prezime je obavezno polje.");
						return;
					}
					try {
						registrujIzvodjaca(txtUmetnickoIme.getText(), new Zanr((String)cmbZanr.getSelectedItem(), true), txtIme.getText(), txtPrezime.getText(), 
								dtDob.getJFormattedTextField().getText(), 
								dtDod.getJFormattedTextField().getText(), 
								rbMuski.isSelected() ? Pol.muski.name() : Pol.zenski.name(), txtOpis.getText());
					} 
					catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else
				{
					if ((Integer)spnBrojClanova.getValue() < 2)
					{
						JOptionPane.showMessageDialog(null, "Broj clanova mora biti veci od jedan.");
						return;
					}
					try {
						registrujGrupu(txtUmetnickoIme.getText(), new Zanr((String)cmbZanr.getSelectedItem(),true), 
								((Integer)spnBrojClanova.getValue()), dtDof.getJFormattedTextField().getText(), 
								dtDor.getJFormattedTextField().getText());
					} 
					catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnRegistruj.setBounds(10, 332, 89, 23);
		getContentPane().add(btnRegistruj);
		pnlGrupa = new JPanel();
		pnlGrupa.setBounds(321, 78, 306, 234);
		getContentPane().add(pnlGrupa);
		pnlGrupa.setLayout(null);
		
		JLabel lblBrojClanova = new JLabel("Broj clanova");
		lblBrojClanova.setBounds(10, 11, 64, 14);
		pnlGrupa.add(lblBrojClanova);
		
		spnBrojClanova = new JSpinner();
		spnBrojClanova.setModel(new SpinnerNumberModel(2, 2, 200, 1));
		spnBrojClanova.setBounds(112, 8, 51, 20);
		pnlGrupa.add(spnBrojClanova);
		
		JLabel lblDatumOsnivanja = new JLabel("Datum osnivanja");
		lblDatumOsnivanja.setBounds(10, 45, 94, 14);
		pnlGrupa.add(lblDatumOsnivanja);
		dtDof = new JDatePickerImpl(date3Panel, new DataLabelFormatter());
		sl_dtDof.putConstraint(SpringLayout.NORTH, dtDof.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDof);
		sl_dtDof.putConstraint(SpringLayout.WEST, dtDof.getJFormattedTextField(), 33, SpringLayout.WEST, dtDof);
		sl_dtDof.putConstraint(SpringLayout.EAST, dtDof.getJFormattedTextField(), 211, SpringLayout.WEST, dtDof);
		sl_dtDof = (SpringLayout) dtDof.getLayout();
		dtDof.setBounds(112, 38, 189, 25);
		pnlGrupa.add(dtDof);
		dtDor = new JDatePickerImpl(date4Panel, new DataLabelFormatter());
		sl_dtDor.putConstraint(SpringLayout.NORTH, dtDor.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDor);
		sl_dtDor.putConstraint(SpringLayout.WEST, dtDor.getJFormattedTextField(), 33, SpringLayout.WEST, dtDor);
		sl_dtDor.putConstraint(SpringLayout.EAST, dtDor.getJFormattedTextField(), 211, SpringLayout.WEST, dtDor);
		sl_dtDor = (SpringLayout) dtDor.getLayout();
		dtDor.setBounds(112, 78, 189, 25);
		pnlGrupa.add(dtDor);
		
		JLabel lblDatumRaspada = new JLabel("Datum raspada");
		lblDatumRaspada.setBounds(10, 86, 86, 14);
		pnlGrupa.add(lblDatumRaspada);
		
		JLabel lblZanr = new JLabel("Zanr:");
		lblZanr.setBounds(338, 11, 48, 14);
		getContentPane().add(lblZanr);
		
		cmbZanr = new JComboBox();
		cmbZanr.setBounds(374, 7, 184, 22);
		getContentPane().add(cmbZanr);
		for (Zanr z : sesija.getZanrovi())
		{
			cmbZanr.addItem(z.getNazivZanra());
		}
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				pnlGrupa.setBounds(pnlPojedinacniIzvodjac.getBounds().x, pnlPojedinacniIzvodjac.getBounds().y, pnlGrupa.getBounds().width, pnlGrupa.getBounds().height);
			}
		});
		
	} 
	
	private void registrujIzvodjaca(String umetnickoIme, Zanr zanr, String ime, String prezime, String dob, String dod, String pol, String opis) throws ParseException
	{
		Pol p = Pol.valueOf(pol);
		Pojedinacanizvodjac pi = new Pojedinacanizvodjac(umetnickoIme, zanr, true, ime, prezime, new SimpleDateFormat("dd.MM.yyyy").parse(dob), 
				new SimpleDateFormat("dd.MM.yyyy").parse(dod), opis, p);
			if (!sesija.addUmetnici(pi))
				JOptionPane.showMessageDialog(null, "Izvodjac vec postoji.");
	}
	
	private void registrujGrupu(String umetnickoIme, Zanr zanr, int brojClanova, String dof, String dor) throws ParseException
	{
		Grupa g = new Grupa(umetnickoIme, zanr, true,  brojClanova, new SimpleDateFormat("dd.MM.yyyy").parse(dof), 
				new SimpleDateFormat("dd.MM.yyyy").parse(dor));
		if (!sesija.addGrupe(g))
			JOptionPane.showMessageDialog(null, "Grupa vec postoji");
	}
}
