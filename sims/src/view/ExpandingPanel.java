package view;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import org.jdesktop.swingx.JXCollapsiblePane;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class ExpandingPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JXCollapsiblePane content;
    private BasicArrowButton expandingButton;
    private JLabel label;


    private void initGui(int basicArrowButtonDirection, JXCollapsiblePane.Direction collapsablePaneDirection){
        

        BasicArrowButton expandingButton = new BasicArrowButton(basicArrowButtonDirection);
        
        expandingButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		// TODO Auto-generated method stub
        		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		// TODO Auto-generated method stub
        		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
		});

        CC componentConstraints = new CC();
        switch(basicArrowButtonDirection) {
        case BasicArrowButton.SOUTH:
        	componentConstraints.dockNorth();
        	break;
        case BasicArrowButton.NORTH:
        	componentConstraints.dockSouth();
        	break;
        case BasicArrowButton.EAST:
        	componentConstraints.dockWest();
        	break;
        case BasicArrowButton.WEST:
        	componentConstraints.dockEast();
        	break;
        }

        this.add(expandingButton, componentConstraints);
        JXCollapsiblePane content = new JXCollapsiblePane(collapsablePaneDirection);

        CC componentConstraints2 = new CC();
        componentConstraints2.alignX("center").spanX();
        componentConstraints2.grow();

        this.add(content, componentConstraints2);

        expandingButton.addActionListener(content.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));

        
        this.content = content;
        this.expandingButton = expandingButton;
        this.content.setCollapsed(true);


    }

    private void initGui(){
        initGui(BasicArrowButton.SOUTH, JXCollapsiblePane.Direction.DOWN);
    }


    public ExpandingPanel(String labela){
        super(new MigLayout("fillx"));
        
        CC componentConstraints = new CC();
        componentConstraints.alignX("center").spanX();
        componentConstraints.gapLeft("200");
        componentConstraints.dockNorth();

        JLabel label = new JLabel(labela);
        this.add(label, componentConstraints);
        this.label = label;
        

        initGui();
    }

    public ExpandingPanel() {
        super(new MigLayout("fillx"));

        initGui();

    }



    /**
     * @return the label
     */
    public JLabel getLabel() {
        return label;
    }

    public JPanel getContent() {
        return this.content;
    }

    public void setContent(JXCollapsiblePane pane){
        this.expandingButton.removeActionListener(content.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
        CC componentConstraints2 = new CC();
        componentConstraints2.alignX("center").spanX();
        componentConstraints2.grow();
        this.remove(this.content);
        this.add(pane, componentConstraints2);
        this.content = pane;
        this.expandingButton.addActionListener(content.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
        this.content.setCollapsed(true);
    }

    public BasicArrowButton getExpandingButton() {
        return this.expandingButton;
    }
    
    
    
}