package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class ManfighterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField input;
	private JTextArea output;

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getInput() {
		return input;
	}

	public JTextArea getOutput() {
		return output;
	}

	public ManfighterGUI(ActionListener a) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(3);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);
		output = new JTextArea(15, 80);
		output.setWrapStyleWord(true);
		output.setEditable(false);
		JScrollPane scrollMain = new JScrollPane(output);
		scrollMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		DefaultCaret caretMain = (DefaultCaret) output.getCaret();
		caretMain.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panel.add(scrollMain, BorderLayout.CENTER);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		input = new JTextField(10);
		JButton enter = new JButton("Enter");
		enter.setActionCommand("Enter");
		enter.addActionListener(a);
		input.setActionCommand("Enter");
		input.addActionListener(a);
		inputpanel.add(input);
		inputpanel.add(enter);
		panel.add(inputpanel, BorderLayout.SOUTH);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.pack();

		GraphicsConfiguration gc = frame.getGraphicsConfiguration();  
		Rectangle bounds = gc.getBounds();
		frame.setLocation((bounds.width/2) - (frame.getWidth()/2), (bounds.height/2));

		frame.setVisible(true);
		frame.setResizable(false);
		input.requestFocus();
	}
	
	public void createDialogs(JTextArea pL, JTextArea cL, JTextArea eL, JDialog pD, JDialog cD, JDialog eD) {
		GraphicsConfiguration gc = frame.getGraphicsConfiguration();  
		Rectangle bounds = gc.getBounds();
		Dimension defaultSize = new Dimension(300, 220);
		Point pDim, eDim, cDim; //locations
		
		if(pD.isVisible())
			pDim = new Point((int)pD.getLocation().getX(), (int)pD.getLocation().getY());
		else
			pDim = new Point((bounds.width/3) - frame.getWidth()/3, (int)((bounds.height / 2) - defaultSize.getHeight()));
		if(cD.isVisible())
			cDim = new Point((int)cD.getLocation().getX(), (int)cD.getLocation().getY());
		else
			cDim = new Point((int)((bounds.width/2)-(defaultSize.getWidth()/2)), (int)((bounds.height/2)-(defaultSize.getHeight())));
		if(eD.isVisible())
			eDim = new Point((int)eD.getLocation().getX(), (int)eD.getLocation().getY());
		else
			eDim = new Point((2*bounds.width/3), (int)((bounds.height / 2) - defaultSize.getHeight()));
		
		pD.setVisible(false);
		eD.setVisible(false);
		eD.setVisible(false);
		
		//pL = new JTextArea();
		pL.setEditable(false);
		JPanel pane1 = new JPanel(new BorderLayout());
		pane1.add(pL, BorderLayout.NORTH);
		pane1.setOpaque(true);
		pD.setContentPane(pane1);
		pD.setSize(defaultSize);
		pD.setLocation(pDim.x, pDim.y);
		pD.setResizable(false);
		pD.setAlwaysOnTop(true);
		pD.setVisible(true);
		
		//cL = new JTextArea();
		cL.setEditable(false);
		JPanel pane3 = new JPanel(new BorderLayout());
		pane3.add(cL, BorderLayout.NORTH);
		pane3.setOpaque(true);
		cD.setContentPane(pane3);
		cD.setSize(defaultSize);
		cD.setLocation(cDim.x, cDim.y);
		cD.setResizable(false);
		cD.setAlwaysOnTop(true);
		cD.setVisible(true);
		
		//eL = new JTextArea();
		eL.setEditable(false);
		JPanel pane2 = new JPanel(new BorderLayout());
		pane2.add(eL, BorderLayout.NORTH);
		pane2.setOpaque(true);
		eD.setContentPane(pane2);
		eD.setSize(defaultSize);
		eD.setLocation(eDim.x, eDim.y);
		eD.setResizable(false);
		eD.setAlwaysOnTop(true);
		eD.setVisible(true);
		
	}
}
