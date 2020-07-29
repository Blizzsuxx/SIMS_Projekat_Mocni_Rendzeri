package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.JTextComponent;

import model.Par;
import net.miginfocom.swing.MigLayout;
import view.EditorPanel;

public class EditorBuilder {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object objekatZaIzmeniti;
	private Collection<Par<String, Boolean>> redovi;
	private Collection<String> atributi;
	public EditorBuilder(Collection<Par<String, Boolean>> redovi, Object objekatZaIzmeniti, Collection<String> atributi) {

		this.objekatZaIzmeniti = objekatZaIzmeniti;
		this.atributi = atributi;
		this.redovi = redovi;
	}
	public EditorBuilder(Object objekatZaIzmeniti) {
		this.objekatZaIzmeniti = objekatZaIzmeniti;
	}
	
	public void dodajTextField(String nazivReda, boolean editabilan, String mapiranNaAtribut) {
		Par<String, Boolean> red = new Par<String, Boolean>(nazivReda, editabilan);
		dodajTextField(red, mapiranNaAtribut);
	}
	
	public void dodajTextField(Par<String, Boolean> red, String mapiranNaAtribut) {
		redovi.add(red);
		atributi.add(mapiranNaAtribut);
	}
	
	public JPanel napraviPanel(EditorPanel panel) {
		panel.setRedovi(redovi);
		Class klasa = objekatZaIzmeniti.getClass();
		
		Iterator<JTextField> iter1 = panel.getTekstFields().iterator();
		Iterator<String> iter2 = atributi.iterator();
		
		while(iter1.hasNext() && iter2.hasNext()) {
			JTextField tekst = iter1.next();
			String imeAtriuta = iter2.next();
			try {
				tekst.setText((String) klasa.getMethod("get" + imeAtriuta).invoke(objekatZaIzmeniti));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				continue;
			}
		}
		
		
		panel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				Iterator<JTextField> iter1 = panel.getTekstFields().iterator();
				Iterator<String> iter2 = atributi.iterator();
				
				while(iter1.hasNext() && iter2.hasNext()) {
					JTextField field = iter1.next();
					String imeAtriuta = iter2.next();
						try {
							String tekst = field.getText();
							klasa.getMethod("set" + imeAtriuta, String.class).invoke(objekatZaIzmeniti, tekst);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| NoSuchMethodException | SecurityException e1) {
							return;
						}
					
				}
				
			}
		});
		return panel;
	}
	
	public JPanel napraviPanel()  {
		EditorPanel panel = new EditorPanel(redovi);
		return napraviPanel(panel);
	}

}
