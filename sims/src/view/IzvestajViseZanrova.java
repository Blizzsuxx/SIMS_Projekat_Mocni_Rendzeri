package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTable;

import model.IzvestajSvihZanrova;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public class IzvestajViseZanrova extends JFrame {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JXTable table;
	@SuppressWarnings("unused")
	private Sesija sesija;
	private JButton btnBack;
	ArrayList<IzvestajSvihZanrova> lista;
	public IzvestajViseZanrova(Sesija s) {
		this.sesija=s;//potrebna da kazem lista zanrova sa nekim podacima kao, broj dela, prosecna ocena zanra preko dela, nesto tako?
		s.namestiIzvestaj();
	    lista=s.getIzvestajSvihZanrova().getSviZanrovi();
		setSize(700, 700);
		setResizable(false);
		initGui();
		initActions();
		
	}
	private void initActions() {
        btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent.setVisible(true);
				IzvestajViseZanrova.this.dispose();
				
			}

			
		});
		
	}
	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]"); 
		setLayout(mig);
		
		table = new JXTable(new ViseZanrovaModel(lista));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp = new JScrollPane(table);
		//this.
		add(sp);
		
		TableRowSorter<TableModel> tableSorter=new TableRowSorter<TableModel>();
		tableSorter.setModel(table.getModel());
		table.setRowSorter(tableSorter);
		
		JPanel pSerch=new JPanel(new FlowLayout(FlowLayout.LEFT));
        pSerch.add(new JLabel("Pretraga:"));
		JTextField tfSerch=new JTextField(20);
		pSerch.add(tfSerch);
		add(pSerch, BorderLayout.SOUTH);
		tfSerch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				changedUpdate(e); 
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				String sSerch=tfSerch.getText().trim();
				if (sSerch.isEmpty()) {
					tableSorter.setRowFilter(null);
				}else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
				}
				
			}
			
		});
		
	}

}
